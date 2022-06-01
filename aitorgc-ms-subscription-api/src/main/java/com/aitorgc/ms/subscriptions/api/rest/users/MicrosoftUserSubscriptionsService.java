package com.aitorgc.ms.subscriptions.api.rest.users;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aitorgc.ms.subscriptions.api.internalapis.organizations.MicrosoftAuth;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.MicrosoftUserSubscription;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.MicrosoftUsers;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.OfficeLocation;
import com.aitorgc.ms.subscriptions.api.internalapis.organizations.OrganizationsClient;
import com.aitorgc.ms.subscriptions.api.internalapis.users.CreateUserRequest;
import com.aitorgc.ms.subscriptions.api.internalapis.users.CreateUserResponse;
import com.aitorgc.ms.subscriptions.api.internalapis.users.FindUserRequest;
import com.aitorgc.ms.subscriptions.api.internalapis.users.UpdateUserRequest;
import com.aitorgc.ms.subscriptions.api.internalapis.users.UsersClient;
import com.aitorgc.ms.subscriptions.api.msgraph.ApplicationCredentials;
import com.aitorgc.ms.subscriptions.api.msgraph.MSGraphService;
import com.aitorgc.ms.subscriptions.api.rest.error.OrganizationApiCommunicationException;
import com.aitorgc.ms.subscriptions.api.rest.error.UserApiCommunicationException;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.models.ChangeNotification;
import com.microsoft.graph.models.ChangeNotificationCollection;
import com.microsoft.graph.models.ChangeType;
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

	private static final String USER_CREATED_SUCCESSFULLY_LOG = "User with id {} created successfully. Data: Microsoft id {}, email {}, upn {}";
	private static final String USER_OFFICE_LOCATION_IS_NOT_VALID_LOG = "Office location '{}' of the user with Microsoft id {}, is not valid";
	private static final String SUBSCRIPTION_WITH_CLIENT_STATE_IS_NOT_RECOGNIZED_LOG = "Subscription with client state {} is not recognized";
	private static final String MICROSOFT_AUTH_CONFIG_NOT_FOUND_LOG = "Microsoft user authentication module not found from organization with id {}";

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

		// Verificar si la organización tiene el filtro de office location
		// activado
		final MicrosoftAuth microsoftAuthConfig = fetchMicrosoftAuthConfig(subscription.getOrganizationId(), true);
		final MicrosoftUsers microsoftUsersConfig = fetchMicrosoftUsersConfig(subscription.getOrganizationId());
		if (Objects.isNull(microsoftAuthConfig)) {
			log.warn(MICROSOFT_AUTH_CONFIG_NOT_FOUND_LOG, subscription.getOrganizationId());
			return;
		}

		final boolean isDeleted = ChangeType.DELETED.equals(changeNotification.changeType);
		final String userId = changeNotification.resource.split("/")[1];

		if (isDeleted) {
			deleteUser(userId);
		} else {
			createOrUpdateUser(userId, microsoftAuthConfig, microsoftUsersConfig);
		}
	}

	private void createOrUpdateUser(final String userId, final MicrosoftAuth microsoftAuthConfig,
			final MicrosoftUsers microsoftUsersConfig) {
		log.info("Create or update user in Bookker with Microsoft Id {}", userId);
		final MSGraphService msGraphService = constructGraphService(microsoftUsersConfig);
		final User microsoftUser = fetchMicrosoftUser(userId, msGraphService);

		if (!Objects.isNull(microsoftUser)) {
			log.info("Se ha conseguido recuperar el usuario en Microsoft {}", microsoftUser.userPrincipalName);
			final OfficeLocationFilter officeLocationFilter = constructOfficeLocationFilter(microsoftAuthConfig);

			if (!userHasValidOfficeLocation(microsoftUser.officeLocation, officeLocationFilter)) {
				log.warn(USER_OFFICE_LOCATION_IS_NOT_VALID_LOG, microsoftUser.officeLocation, microsoftUser.id);
				return;
			}

			final com.aitorgc.ms.subscriptions.api.internalapis.users.User bookkerUser = fetchBookkerUser(
					microsoftUser.id);
			if (Objects.isNull(bookkerUser)) {
				// Crear usuario
				log.info("Hay que crear el usuario en Bookker");
				createUser(microsoftUser, microsoftAuthConfig.getOrganizationId());
			} else {
				// Actualizar usuario
				log.info("Se ha conseguido recuperar el usuario en Bookker {}", bookkerUser.getUpn());
				updateUser(microsoftUser, bookkerUser);
			}

		}

	}

	private void createUser(final User microsoftUser, final String organizationId) {

		// Crear usuario
		final CreateUserRequest.User newBookkerUser = new CreateUserRequest.User(microsoftUser.givenName,
				microsoftUser.surname, microsoftUser.mail, microsoftUser.userPrincipalName, organizationId,
				DEFAULT_MOBILE_ROLE_ID, generateRandomPassword());
		// TODO: Recuperar grupos por defecto
		// TODO: Recuperar grupos sincronizados del usuario (en caso de que el módulo
		// esté activado)

		// TODO: Recuperar regla por defecto

		try {
			CreateUserResponse response = usersClient.createUser(new CreateUserRequest(newBookkerUser));
			log.info(USER_CREATED_SUCCESSFULLY_LOG, response.getUser().getId(), response.getUser().getMicrosoftId(),
					response.getUser().getEmail(), response.getUser().getUpn());
		} catch (FeignException e) {
			throw new UserApiCommunicationException(e);
		}
	}

	private void updateUser(final User microsoftUser,
			final com.aitorgc.ms.subscriptions.api.internalapis.users.User bookkerUser) {
		UpdateUserRequest.User updateUser = new UpdateUserRequest.User();
		boolean mustBeUpdated = false;

		if (!Objects.equals(microsoftUser.givenName, bookkerUser.getName())) {
			log.info("Hay que actualizar el nombre del usuario. Antiguo: {}. Nuevo: {}", bookkerUser.getName(),
					microsoftUser.givenName);
			updateUser.setName(microsoftUser.givenName);
			mustBeUpdated = true;
		}

		if (!Objects.equals(microsoftUser.surname, bookkerUser.getEmail())) {
			log.info("Hay que actualizar el apellido del usuario. Antiguo: {}. Nuevo: {}", bookkerUser.getSurname(),
					microsoftUser.surname);
			updateUser.setName(microsoftUser.surname);
			mustBeUpdated = true;
		}

		if (!Objects.equals(microsoftUser.mail, bookkerUser.getEmail())) {
			log.info("Hay que actualizar el email del usuario. Antiguo: {}. Nuevo: {}", bookkerUser.getEmail(),
					microsoftUser.mail);
			updateUser.setName(microsoftUser.mail);
			mustBeUpdated = true;
		}

		if (!Objects.equals(microsoftUser.userPrincipalName, bookkerUser.getUpn())) {
			log.info("Hay que actualizar el upn del usuario. Antiguo: {}. Nuevo: {}", bookkerUser.getUpn(),
					microsoftUser.userPrincipalName);
			updateUser.setName(microsoftUser.userPrincipalName);
			mustBeUpdated = true;
		}

		// TODO: Verificar si hay que almacenarlo en la organización
		if (!Objects.equals(microsoftUser.employeeId, bookkerUser.getEmployeeId())) {
			log.info("Hay que actualizar el employeeId del usuario. Antiguo: {}. Nuevo: {}",
					bookkerUser.getEmployeeId(), microsoftUser.employeeId);
			updateUser.setName(microsoftUser.employeeId);
			mustBeUpdated = true;
		}

		// TODO: Actualizar grupos sincronizados en caso de que la organización tenga el
		// módulo activado

		if (mustBeUpdated) {
			try {
				usersClient.updateUser(bookkerUser.getId(), new UpdateUserRequest(updateUser));
			} catch (FeignException e) {
				throw new UserApiCommunicationException(e);
			}
		}
	}

	private void deleteUser(final String microsoftUserId) {
		log.info("Delete user in Bookker with Microsoft Id {}", microsoftUserId);

		final com.aitorgc.ms.subscriptions.api.internalapis.users.User bookkerUser = fetchBookkerUser(microsoftUserId);

		if (!Objects.isNull(bookkerUser)) {
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
			return true; // No está activado el filtro
		}

		return officeLocationFilter.getOfficeLocations().stream().anyMatch(ol -> Objects.equals(ol, officeLocation));
	}

	private com.aitorgc.ms.subscriptions.api.internalapis.users.User fetchBookkerUser(final String microsoftUserId) {
		try {
			return usersClient.findUser(new FindUserRequest(null, null, microsoftUserId)).getUser();
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
			log.error("User with id {} not found in Microsoft", userId);

			return null;
		}
	}

	private MSGraphService constructGraphService(final MicrosoftAuth microsoftAuthConfig) {
		return constructGraphService(new ApplicationCredentials(microsoftAuthConfig.getApplicationId(),
				microsoftAuthConfig.getApplicationSecret(), microsoftAuthConfig.getTenantId()));
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

	@Data
	private class OfficeLocationFilter {

		private final boolean enabled;
		private final List<String> officeLocations;

	}

}
