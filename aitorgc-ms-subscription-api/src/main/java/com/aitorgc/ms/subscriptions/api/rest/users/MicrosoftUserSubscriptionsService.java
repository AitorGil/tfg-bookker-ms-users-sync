package com.aitorgc.ms.subscriptions.api.rest.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.aitorgc.ms.subscriptions.api.internalapis.organizations.MicrosoftAuth;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.MicrosoftGroups;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.MicrosoftUserSubscription;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.MicrosoftUsers;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.Modules;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.OfficeLocation;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.OrganizationsClient;
import com.aitorgc.ms.subscriptions.api.internalapis.users.CreateUserRequest;
import com.aitorgc.ms.subscriptions.api.internalapis.users.CreateUserResponse;
import com.aitorgc.ms.subscriptions.api.internalapis.users.FindUserRequest;
import com.aitorgc.ms.subscriptions.api.internalapis.users.Group;
import com.aitorgc.ms.subscriptions.api.internalapis.users.UpdateUserRequest;
import com.aitorgc.ms.subscriptions.api.internalapis.users.UserRule;
import com.aitorgc.ms.subscriptions.api.internalapis.users.UserStatus;
import com.aitorgc.ms.subscriptions.api.internalapis.users.UsersClient;
import com.aitorgc.ms.subscriptions.api.msgraph.ApplicationCredentials;
import com.aitorgc.ms.subscriptions.api.msgraph.MSGraphService;
import com.aitorgc.ms.subscriptions.api.rest.error.OrganizationApiCommunicationException;
import com.aitorgc.ms.subscriptions.api.rest.error.UserApiCommunicationException;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.models.ChangeNotification;
import com.microsoft.graph.models.ChangeNotificationCollection;
import com.microsoft.graph.models.User;
import com.microsoft.graph.serializer.DefaultSerializer;

import feign.FeignException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MicrosoftUserSubscriptionsService {

	private static final String MICROSOFT_USER_FETCHED_LOG = "Microsoft user fetched: Id: {} UserPrincipalName: {} Mail: {}";
	private static final String USER_MAIL_IS_REQUIRED_LOG = "User {} with Microsoft Id {} can't be created or updated because it does not have mail";
	private static final String USER_WITH_ID_NOT_FOUND_IN_MICROSOFT_LOG = "User with id {} not found in Microsoft";
	private static final String USER_CREATED_SUCCESSFULLY_LOG = "User with id {} created successfully. Data: Microsoft id {}, email {}, upn {}";
	private static final String USER_OFFICE_LOCATION_IS_NOT_VALID_LOG = "Office location '{}' of the user with Microsoft id {}, is not valid";
	private static final String SUBSCRIPTION_WITH_CLIENT_STATE_IS_NOT_RECOGNIZED_LOG = "Subscription with client state {} is not recognized";
	private static final String MICROSOFT_AUTH_CONFIG_NOT_FOUND_LOG = "Microsoft user authentication module not found from organization with id {}";
	private static final String MICROSOFT_USERS_CONFIG_NOT_FOUND_LOG = "Microsoft users module not found from organization with id {}";
	private static final String MICROSOFT_GROUPS_CONFIG_NOT_FOUND_LOG = "Microsoft groups module not found from organization with id {}";
	private static final String MICROSOFT_GROUPS_CONFIG_WITH_DELEGATED_PERMISSIONS_LOG = "Microsoft groups module from organization with id {} is configured with delegated permissions";
	private static final String MODULES_CONFIG_NOT_FOUND_LOG = "Modules config not found from organization with id {}";
	private static final String USER_GROUPS_NEED_TO_BE_UPDATED_LOG = "User groups need to be updated";
	private static final String EMPLOYEE_ID_NEEDS_TO_BE_UPDATED_LOG = "Employee id needs to be updated. Old: {}. New: {}";
	private static final String USER_PRINCIPAL_NAME_NEEDS_TO_BE_UPDATED_LOG = "User principal name needs to be updated. Old: {}. New: {}";
	private static final String MICROSOFT_ID_NEEDS_TO_BE_UPDATED_LOG = "Microsoft Id needs to be updated. Old: {}. New: {}";
	private static final String EMAIL_NEEDS_TO_BE_UPDATED_LOG = "Email needs to be updated. Old: {}. New: {}";
	private static final String SURNAME_NEEDS_TO_BE_UPDATED_LOG = "Surname needs to be updated. Old: {}. New: {}";
	private static final String USERNAME_NEEDS_TO_BE_UPDATED_LOG = "Username needs to be updated. Old: {}. New: {}";
	private static final String CREATE_OR_UPDATE_USER_IN_BOOKKER_LOG = "Create or update user in Bookker with Microsoft Id {}";
	private static final String DELETE_USER_IN_BOOKKER_LOG = "Delete user in Bookker with Microsoft Id {}";

	private static final String DEFAULT_MOBILE_ROLE_ID = "5220b6a2-8920-4dae-8ff0-ba96707d439d";

	private static final DefaultSerializer serializer = new DefaultSerializer(new DefaultLogger());

	private final OrganizationsClient organizationsClient;
	private final UsersClient usersClient;

	public void processSubscription(final String notificationInfo) {

		final ChangeNotificationCollection changeNotifications = serializer.deserializeObject(notificationInfo,
				ChangeNotificationCollection.class);

		changeNotifications.value.stream().forEach(this::processChangeNotification);

	}

	private void processChangeNotification(final ChangeNotification changeNotification) {
		final MicrosoftUserSubscription subscription = fetchMicrosoftUserSubscription(changeNotification.clientState);
		if (Objects.isNull(subscription)) {
			log.warn(SUBSCRIPTION_WITH_CLIENT_STATE_IS_NOT_RECOGNIZED_LOG, changeNotification.clientState);
			return;
		}

		// Verificar si la organizaci??n tiene el filtro de office location
		// activado
		final Modules modulesConfig = fetchModulesConfig(subscription.getOrganizationId());

		if (Objects.isNull(modulesConfig)) {
			log.warn(MODULES_CONFIG_NOT_FOUND_LOG, subscription.getOrganizationId());
			return;
		}

		if (modulesConfig.isEnableMicrosoftUsersSync()) {

			final MicrosoftAuth microsoftAuthConfig = fetchMicrosoftAuthConfig(subscription.getOrganizationId(), true);
			if (Objects.isNull(microsoftAuthConfig)) {
				log.warn(MICROSOFT_AUTH_CONFIG_NOT_FOUND_LOG, subscription.getOrganizationId());
				return;
			}

			final MicrosoftUsers microsoftUsersConfig = fetchMicrosoftUsersConfig(subscription.getOrganizationId());
			if (Objects.isNull(microsoftUsersConfig)) {
				log.warn(MICROSOFT_USERS_CONFIG_NOT_FOUND_LOG, subscription.getOrganizationId());
				return;
			}

			final String userId = changeNotification.resource.split("/")[1];

			final MSGraphService msGraphService = constructGraphService(microsoftUsersConfig);
			final User microsoftUser = fetchMicrosoftUser(userId, msGraphService);

			if (Objects.isNull(microsoftUser)) {
				deleteUser(userId);
			} else {
				log.info(MICROSOFT_USER_FETCHED_LOG, microsoftUser.id, microsoftUser.userPrincipalName,
						microsoftUser.mail);
				createOrUpdateUser(microsoftUser, microsoftAuthConfig, modulesConfig.isAllowMicrosoftGroupSync());
			}

		}
	}

	private void createOrUpdateUser(final User microsoftUser, final MicrosoftAuth microsoftAuthConfig,
			final boolean microsoftGroupSyncEnabled) {
		log.info(CREATE_OR_UPDATE_USER_IN_BOOKKER_LOG, microsoftUser.id);

		final OfficeLocationFilter officeLocationFilter = constructOfficeLocationFilter(microsoftAuthConfig);

		if (!userHasValidOfficeLocation(microsoftUser.officeLocation, officeLocationFilter)) {
			log.warn(USER_OFFICE_LOCATION_IS_NOT_VALID_LOG, microsoftUser.officeLocation, microsoftUser.id);
			return;
		}

		final com.aitorgc.ms.subscriptions.api.internalapis.users.User bookkerUser = fetchBookkerUser(microsoftUser);
		if (Objects.isNull(bookkerUser)) {
			// Crear usuario
			createUser(microsoftUser, microsoftAuthConfig, microsoftGroupSyncEnabled);
		} else {
			// Actualizar usuario
			updateUser(microsoftUser, bookkerUser, microsoftAuthConfig, microsoftGroupSyncEnabled);
		}

	}

	private void createUser(final User microsoftUser, final MicrosoftAuth microsoftAuthConfig,
			final boolean microsoftGroupSyncEnabled) {

		final String organizationId = microsoftAuthConfig.getOrganizationId();

		if (Strings.isBlank(microsoftUser.mail)) {
			log.warn(USER_MAIL_IS_REQUIRED_LOG, microsoftUser.id, microsoftUser.userPrincipalName);
			return;
		}

		// Crear usuario
		final CreateUserRequest.User newBookkerUser = new CreateUserRequest.User(microsoftUser.givenName,
				microsoftUser.surname, microsoftUser.mail, microsoftUser.userPrincipalName, organizationId,
				DEFAULT_MOBILE_ROLE_ID, generateRandomPassword());

		newBookkerUser.setMicrosoftId(microsoftUser.id);
		newBookkerUser.setStatus(UserStatus.ACTIVATED);

		if (microsoftAuthConfig.isEnableEmployeeId()) {
			newBookkerUser.setEmployeeId(microsoftUser.employeeId);
		}

		// Recuperar grupos por defecto de la organizaci??n
		final List<Group> defaultGroups = fetchDefaultOrganizationGroups(organizationId);
		if (!Objects.isNull(defaultGroups) && !defaultGroups.isEmpty()) {
			newBookkerUser.setGroups(defaultGroups.stream().map(Group::getId).collect(Collectors.toList()));
		}

		// Recuperar regla por defecto de la organizaci??n
		final UserRule defaultUserRule = fetchDefaultOrganizationUserRule(organizationId);
		if (!Objects.isNull(defaultUserRule)) {
			newBookkerUser.setUserRuleId(defaultUserRule.getId());
		}

		// En caso necesario, sincronizar los grupos del usuario con Microsoft
		if (microsoftGroupSyncEnabled) {
			final SyncUserGroupsResult syncUserGroupsResult = syncMicrosftGroups(
					microsoftAuthConfig.getOrganizationId(), microsoftUser, defaultGroups);
			if (syncUserGroupsResult.isHasChanged()) {
				newBookkerUser.setGroups(
						syncUserGroupsResult.getGroups().stream().map(Group::getId).collect(Collectors.toList()));
			}
		}

		try {
			CreateUserResponse response = usersClient.createUser(new CreateUserRequest(newBookkerUser));
			log.info(USER_CREATED_SUCCESSFULLY_LOG, response.getUser().getId(), response.getUser().getMicrosoftId(),
					response.getUser().getEmail(), response.getUser().getUpn());
		} catch (FeignException e) {
			throw new UserApiCommunicationException(e);
		}
	}

	private List<Group> fetchDefaultOrganizationGroups(final String organizationId) {
		try {
			return usersClient.getDefaultOrganizationGroups(organizationId).getGroups();
		} catch (FeignException e) {
			throw new UserApiCommunicationException(e);
		}
	}

	private UserRule fetchDefaultOrganizationUserRule(final String organizationId) {
		try {
			return usersClient.getDefaultOrganizationUserRule(organizationId);
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null; // No tiene regla
			}

			throw new UserApiCommunicationException(e);
		}
	}

	private void updateUser(final User microsoftUser,
			final com.aitorgc.ms.subscriptions.api.internalapis.users.User bookkerUser,
			final MicrosoftAuth microsoftAuthConfig, final boolean microsoftGroupSyncEnabled) {
		UpdateUserRequest.User updateUser = new UpdateUserRequest.User();
		boolean mustBeUpdated = false;

		if (!Objects.equals(microsoftUser.givenName, bookkerUser.getName())) {
			log.info(USERNAME_NEEDS_TO_BE_UPDATED_LOG, bookkerUser.getName(), microsoftUser.givenName);
			updateUser.setName(microsoftUser.givenName);
			mustBeUpdated = true;
		}

		if (!Objects.equals(microsoftUser.surname, bookkerUser.getSurname())) {
			log.info(SURNAME_NEEDS_TO_BE_UPDATED_LOG, bookkerUser.getSurname(), microsoftUser.surname);
			updateUser.setSurname(microsoftUser.surname);
			mustBeUpdated = true;
		}

		if (!Objects.equals(microsoftUser.mail, bookkerUser.getEmail())) {
			log.info(EMAIL_NEEDS_TO_BE_UPDATED_LOG, bookkerUser.getEmail(), microsoftUser.mail);

			if (Strings.isBlank(microsoftUser.mail)) {
				log.warn(USER_MAIL_IS_REQUIRED_LOG, microsoftUser.id, microsoftUser.userPrincipalName);
				return;
			}

			updateUser.setEmail(microsoftUser.mail);
			mustBeUpdated = true;
		}

		if (!Objects.equals(microsoftUser.id, bookkerUser.getMicrosoftId())) {
			log.info(MICROSOFT_ID_NEEDS_TO_BE_UPDATED_LOG, bookkerUser.getMicrosoftId(), microsoftUser.id);

			updateUser.setMicrosoftId(microsoftUser.id);
			mustBeUpdated = true;
		}

		if (!Objects.equals(microsoftUser.userPrincipalName, bookkerUser.getUpn())) {
			log.info(USER_PRINCIPAL_NAME_NEEDS_TO_BE_UPDATED_LOG, bookkerUser.getUpn(),
					microsoftUser.userPrincipalName);
			updateUser.setUpn(microsoftUser.userPrincipalName);
			mustBeUpdated = true;
		}

		if (microsoftAuthConfig.isEnableEmployeeId()
				&& !Objects.equals(microsoftUser.employeeId, bookkerUser.getEmployeeId())) {
			log.info(EMPLOYEE_ID_NEEDS_TO_BE_UPDATED_LOG, bookkerUser.getEmployeeId(), microsoftUser.employeeId);
			updateUser.setEmployeeId(microsoftUser.employeeId);
			mustBeUpdated = true;
		}

		// En caso necesario, sincronizar los grupos del usuario con Microsoft
		if (microsoftGroupSyncEnabled) {
			// Recuperamos los grupos del usuario en Bookker
			final List<Group> userGroups = fetchUserGroups(bookkerUser.getId());

			if (!Objects.isNull(userGroups)) {
				final SyncUserGroupsResult syncUserGroupsResult = syncMicrosftGroups(bookkerUser.getOrganizationId(),
						microsoftUser, userGroups);
				if (syncUserGroupsResult.isHasChanged()) {
					log.info(USER_GROUPS_NEED_TO_BE_UPDATED_LOG);
					updateUser.setGroups(
							syncUserGroupsResult.getGroups().stream().map(Group::getId).collect(Collectors.toList()));
					mustBeUpdated = true;
				}
			}

		}

		if (mustBeUpdated) {
			try {
				usersClient.updateUser(bookkerUser.getId(), new UpdateUserRequest(updateUser));
			} catch (FeignException e) {
				throw new UserApiCommunicationException(e);
			}
		}
	}

	private void deleteUser(final String microsoftUserId) {
		log.info(DELETE_USER_IN_BOOKKER_LOG, microsoftUserId);
		final com.aitorgc.ms.subscriptions.api.internalapis.users.User bookkerUser = fetchBookkerUser(microsoftUserId,
				null, null);

		if (!Objects.isNull(bookkerUser) && UserStatus.ACTIVATED.equals(bookkerUser.getStatus())) {
			try {
				usersClient.deleteUser(bookkerUser.getId());
			} catch (FeignException e) {
				throw new UserApiCommunicationException(e);
			}
		}
	}

	private String generateRandomPassword() {
		return UUID.randomUUID().toString();
	}

	private boolean userHasValidOfficeLocation(final String officeLocation,
			final OfficeLocationFilter officeLocationFilter) {
		if (!officeLocationFilter.isEnabled()) {
			return true; // No est?? activado el filtro
		}

		return officeLocationFilter.getOfficeLocations().stream().anyMatch(ol -> Objects.equals(ol, officeLocation));
	}

	private com.aitorgc.ms.subscriptions.api.internalapis.users.User fetchBookkerUser(final User user) {
		return fetchBookkerUser(user.id, user.userPrincipalName, user.mail);
	}

	private com.aitorgc.ms.subscriptions.api.internalapis.users.User fetchBookkerUser(final String microsoftUserId,
			final String upn, final String email) {
		try {
			return usersClient.findUser(new FindUserRequest(email, upn, microsoftUserId)).getUser();
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw e;
		}
	}

	private List<Group> fetchOrganizationSynchronizedGroups(final String organizationId) {
		try {
			return usersClient.getOrganizationGroups(organizationId, true).getGroups();
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw e;
		}
	}

	private List<Group> fetchUserGroups(final String userId) {
		try {
			return usersClient.getUserGroups(userId).getGroups();
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw e;
		}
	}

	private User fetchMicrosoftUser(final String userId, final MSGraphService msGraphService) {
		try {
			return msGraphService.getUser(userId);
		} catch (GraphServiceException e) {
			if (404 == e.getResponseCode()) {
				log.error(USER_WITH_ID_NOT_FOUND_IN_MICROSOFT_LOG, userId);
				return null;
			}

			throw e;
		}
	}

	private MSGraphService constructGraphService(final MicrosoftGroups microsoftGroupsConfig) {
		return constructGraphService(new ApplicationCredentials(microsoftGroupsConfig.getApplicationId(),
				microsoftGroupsConfig.getApplicationSecret(), microsoftGroupsConfig.getTenantId()));
	}

	private MSGraphService constructGraphService(final MicrosoftUsers microsoftUsersConfig) {
		return constructGraphService(new ApplicationCredentials(microsoftUsersConfig.getApplicationId(),
				microsoftUsersConfig.getApplicationSecret(), microsoftUsersConfig.getTenantId()));
	}

	private MSGraphService constructGraphService(final ApplicationCredentials credentials) {
		return new MSGraphService(credentials);
	}

	private OfficeLocationFilter constructOfficeLocationFilter(final MicrosoftAuth microsoftAuthConfig) {
		return new OfficeLocationFilter(microsoftAuthConfig.isEnableOfficeLocationsFilter(), microsoftAuthConfig
				.getOfficeLocations().stream().map(OfficeLocation::getOfficeLocation).collect(Collectors.toList()));
	}

	private Modules fetchModulesConfig(final String organizationId) {
		try {
			return organizationsClient.getOrganizationModules(organizationId);
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw new OrganizationApiCommunicationException(e);
		}
	}

	private MicrosoftAuth fetchMicrosoftAuthConfig(final String organizationId, final boolean withOfficeLocations) {
		try {
			return organizationsClient.getMicrosoftAuthConfig(organizationId, withOfficeLocations);
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw new OrganizationApiCommunicationException(e);
		}
	}

	private MicrosoftUsers fetchMicrosoftUsersConfig(String organizationId) {
		try {
			return organizationsClient.getMicrosoftUsersConfig(organizationId);
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw new OrganizationApiCommunicationException(e);
		}
	}

	private MicrosoftGroups fetchMicrosoftGroupsConfig(String organizationId) {
		try {
			return organizationsClient.getMicrosoftGroupsConfig(organizationId);
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw new OrganizationApiCommunicationException(e);
		}
	}

	private MicrosoftUserSubscription fetchMicrosoftUserSubscription(final String clientState) {
		try {
			return organizationsClient.getSubscription(clientState);
		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw new OrganizationApiCommunicationException(e);
		}
	}

	private SyncUserGroupsResult syncMicrosftGroups(final String organizationId, final User microsoftUser,
			final List<Group> bookkerUserGroupList) {

		boolean userGroupsChange = false;

		final MicrosoftGroups microsoftGroupsConfig = fetchMicrosoftGroupsConfig(organizationId);

		if (Objects.isNull(microsoftGroupsConfig)) {
			log.info(MICROSOFT_GROUPS_CONFIG_NOT_FOUND_LOG, organizationId);
			return new SyncUserGroupsResult(bookkerUserGroupList, userGroupsChange);
		}

		if (Boolean.TRUE.equals(microsoftGroupsConfig.getDelegatedPermissions())) {
			log.info(MICROSOFT_GROUPS_CONFIG_WITH_DELEGATED_PERMISSIONS_LOG, organizationId);
			return new SyncUserGroupsResult(bookkerUserGroupList, userGroupsChange);
		}

		final MSGraphService msGraphService = constructGraphService(microsoftGroupsConfig);

		// Recuperamos los grupos que el usuario tiene en Microsoft
		final List<String> microsoftUserGroupIdList = fetchMicrosoftUserGroups(microsoftUser, msGraphService);

		// Recuperamos todos los grupos sincronizados de una organizaci??n
		List<Group> synchronizedOrganizationGroupList = fetchOrganizationSynchronizedGroups(organizationId);

		if (Objects.isNull(synchronizedOrganizationGroupList)) {
			// Solo puede ser null si se ha producido un error al recuperar los grupos de
			// Microsoft.
			return new SyncUserGroupsResult(bookkerUserGroupList, userGroupsChange);
		}

		// A??adimos todos los grupos no sincronizados que ya tenia el usuario
		final Set<Group> newUserGroupList = Objects.isNull(bookkerUserGroupList) ? new HashSet<>()
				: bookkerUserGroupList.stream().filter(g -> Strings.isBlank(g.getMicrosoftId()))
						.collect(Collectors.toSet());

		// Comprobamos los grupos sincronizados de la organizaci??n
		for (Group organizationGroup : synchronizedOrganizationGroupList) {
			final String groupMicrosoftId = organizationGroup.getMicrosoftId();
			final String groupId = organizationGroup.getId();

			if (Strings.isBlank(groupMicrosoftId)) {
				continue;
			}

			final boolean groupIsInAzure = microsoftUserGroupIdList.contains(groupMicrosoftId);
			final boolean userHasGroup = !Objects.isNull(bookkerUserGroupList)
					&& bookkerUserGroupList.stream().anyMatch(g -> Objects.equals(groupId, g.getId()));

			// A??adir grupo -> Si tiene el grupo asignado en azure y no en Bookker
			if (groupIsInAzure && !userHasGroup) {
				newUserGroupList.add(organizationGroup);
				userGroupsChange = true;
			}

			// Quitar grupo -> Si no tiene el grupo asignado en azure y s?? en Booker
			if (!groupIsInAzure && userHasGroup) {
				userGroupsChange = true;
			}
		}

		return new SyncUserGroupsResult(new ArrayList<>(newUserGroupList), userGroupsChange);
	}

	private List<String> fetchMicrosoftUserGroups(final User microsoftUser, final MSGraphService msGraphService) {
		try {
			return msGraphService.getUserGroups(microsoftUser.id);
		} catch (GraphServiceException e) {
			if (404 == e.getResponseCode()) {
				log.error(USER_WITH_ID_NOT_FOUND_IN_MICROSOFT_LOG, microsoftUser.id);
			}

			return new ArrayList<>();
		}
	}

	@Data
	private class OfficeLocationFilter {

		private final boolean enabled;
		private final List<String> officeLocations;

	}

	@Data
	private class SyncUserGroupsResult {

		private final List<Group> groups;
		private final boolean hasChanged;

	}
}
