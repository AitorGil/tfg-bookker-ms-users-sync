package com.aitorgc.users.api.rest.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.users.api.internalapis.organizations.MicrosoftAuth;
import com.aitorgc.users.api.internalapis.organizations.MicrosoftGroups;
import com.aitorgc.users.api.internalapis.organizations.MicrosoftUsers;
import com.aitorgc.users.api.internalapis.organizations.Modules;
import com.aitorgc.users.api.internalapis.organizations.OfficeLocation;
import com.aitorgc.users.api.internalapis.organizations.OrganizationsClient;
import com.aitorgc.users.api.model.UserDepartmentsEntity;
import com.aitorgc.users.api.model.UserDepartmentsRepository;
import com.aitorgc.users.api.model.UserEntity;
import com.aitorgc.users.api.model.UserGroupsEntity;
import com.aitorgc.users.api.model.UserGroupsRepository;
import com.aitorgc.users.api.model.UserRepository;
import com.aitorgc.users.api.model.UserStatus;
import com.aitorgc.users.api.rest.error.BadRequestException;
import com.aitorgc.users.api.rest.error.DefaultUserRuleNotFoundException;
import com.aitorgc.users.api.rest.error.EmailAlreadyExistsException;
import com.aitorgc.users.api.rest.error.OrganizationApiCommunicationException;
import com.aitorgc.users.api.rest.error.UserNotFindException;
import com.aitorgc.users.api.rest.error.UserNotFoundException;
import com.aitorgc.users.api.rest.error.UserPrincipalNameAlreadyExistsException;
import com.aitorgc.users.api.rest.error.UserRuleNotFoundException;
import com.aitorgc.users.api.rest.groups.GroupService;
import com.aitorgc.users.api.rest.groups.GroupView;
import com.aitorgc.users.api.rest.userrule.UserRuleService;
import com.aitorgc.users.api.rest.userrule.UserRuleView;
import com.aitorgc.users.api.rest.util.UpdatesService;
import com.aitorgc.users.api.util.msgraph.ApplicationCredentials;
import com.aitorgc.users.api.util.msgraph.MSGraphService;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.UserCollectionPage;
import com.microsoft.graph.requests.UserCollectionRequestBuilder;

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
public class UserService {

    private static final String USER_GROUPS_NEED_TO_BE_UPDATED_LOG = "User groups need to be updated";
    private static final String EMPLOYEE_ID_NEEDS_TO_BE_UPDATED_LOG = "Employee id needs to be updated. Old: {}. New: {}";
    private static final String USER_PRINCIPAL_NAME_NEEDS_TO_BE_UPDATED_LOG = "User principal name needs to be updated. Old: {}. New: {}";
    private static final String MICROSOFT_ID_NEEDS_TO_BE_UPDATED_LOG = "Microsoft Id needs to be updated. Old: {}. New: {}";
    private static final String EMAIL_NEEDS_TO_BE_UPDATED_LOG = "Email needs to be updated. Old: {}. New: {}";
    private static final String SURNAME_NEEDS_TO_BE_UPDATED_LOG = "Surname needs to be updated. Old: {}. New: {}";
    private static final String USERNAME_NEEDS_TO_BE_UPDATED_LOG = "Username needs to be updated. Old: {}. New: {}";
    private static final String CREATE_OR_UPDATE_USER_IN_BOOKKER_LOG = "Create or update user in Bookker with Microsoft Id {}";
    private static final String SYNC_USERS_RESULT_LOG = "Retrieved a total of {} users from {} pages in {} milliseconds";
    private static final String ERROR_PROCESSING_USER_LOG = "Error processing user with Microsoft id {}";
    private static final String MICROSOFT_USER_FETCHED_LOG = "Microsoft user fetched: Id: {} UserPrincipalName: {} Mail: {}";
    private static final String RETRIEVING_ORGANIZATION_USERS_FROM_AZURE_LOG = "Retrieving organization {} users from Azure tenant with id {}";
    private static final String USER_ID_CAN_NOT_BE_NULL_OR_EMPTY = "userId can not be null or empty";
    private static final String USER_MAIL_IS_REQUIRED_LOG = "User {} with Microsoft Id {} can't be created or updated because it does not have mail";
    private static final String USER_CREATED_SUCCESSFULLY_LOG = "User with id {} created successfully. Data: Microsoft id {}, email {}, upn {}";
    private static final String USER_OFFICE_LOCATION_IS_NOT_VALID_LOG = "Office location '{}' of the user with Microsoft id {}, is not valid";
    private static final String MICROSOFT_AUTH_CONFIG_NOT_FOUND_LOG = "Microsoft user authentication module not found from organization with id {}";
    private static final String MICROSOFT_USERS_CONFIG_NOT_FOUND_LOG = "Microsoft users module not found from organization with id {}";
    private static final String MICROSOFT_GROUPS_CONFIG_NOT_FOUND_LOG = "Microsoft groups module not found from organization with id {}";
    private static final String MICROSOFT_GROUPS_CONFIG_WITH_DELEGATED_PERMISSIONS_LOG = "Microsoft groups module from organization with id {} is configured with delegated permissions";
    private static final String MODULES_CONFIG_NOT_FOUND_LOG = "Modules config not found from organization with id {}";
    private static final String USER_WITH_ID_NOT_FOUND_IN_MICROSOFT_LOG = "User with id {} not found in Microsoft";

    private static final String DEFAULT_MOBILE_ROLE_ID = "5220b6a2-8920-4dae-8ff0-ba96707d439d";

    private static final String DATA_SOURCE_UNKNOWN = "UNKNOWN";

    private static final int MS_USERS_TOP = 200;

    private final UserRepository userRepository;
    private final UserGroupsRepository userGroupsRepository;
    private final UserDepartmentsRepository userDepartmentsRepository;

    private final UpdatesService updatesService;
    private final GroupService groupService;
    private final UserRuleService userRuleService;

    private final OrganizationsClient organizationsClient;

    @Transactional(readOnly = true)
    public UserView getUser(String userId) throws BadRequestException, UserNotFoundException {
	if (Strings.isBlank(userId)) {
	    throw new IllegalArgumentException(USER_ID_CAN_NOT_BE_NULL_OR_EMPTY);
	}
	UserEntity userDAO = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	return new UserView(userDAO);
    }

    @Transactional(readOnly = true)
    public UserView findUser(FindUserRequest request) {
	return findUser(request.getUpn(), request.getEmail(), request.getMicrosoftId());
    }

    private UserView findUser(final String upn, final String email, final String microsoftId) {
	UserEntity userDAO = null;

	if (!Strings.isBlank(upn)) {
	    userDAO = userRepository.findByUpn(upn);
	}

	if (Objects.isNull(userDAO) && !Strings.isBlank(microsoftId)) {
	    userDAO = userRepository.findByMicrosoftId(microsoftId);
	}

	if (Objects.isNull(userDAO) && !Strings.isBlank(email)) {
	    userDAO = userRepository.findByEmail(email);
	}

	if (Objects.isNull(userDAO)) {
	    throw new UserNotFindException(upn, microsoftId, email);
	}

	return new UserView(userDAO);
    }

    @Transactional
    public UserView createUser(CreateUserRequest.User newUser) {

	if (userRepository.existsByUpn(newUser.getUpn())) {
	    throw new UserPrincipalNameAlreadyExistsException(newUser.getUpn());
	}

	if (userRepository.existsByEmail(newUser.getEmail())) {
	    throw new EmailAlreadyExistsException(newUser.getEmail());
	}

	UserEntity userDAO = constructUser(newUser);

	userDAO = userRepository.saveAndFlush(userDAO);

	final String userId = userDAO.getId();

	if (!Objects.isNull(newUser.getGroups()) && !newUser.getGroups().isEmpty()) {
	    userGroupsRepository.saveAll(newUser.getGroups().stream()
		    .map(groupId -> new UserGroupsEntity(groupId, userId)).collect(Collectors.toList()));
	}

	if (!Objects.isNull(newUser.getDepartments()) && !newUser.getDepartments().isEmpty()) {
	    userDepartmentsRepository.saveAll(newUser.getDepartments().stream()
		    .map(departmentId -> new UserDepartmentsEntity(userId, departmentId)).collect(Collectors.toList()));
	}

	updatesService.refreshUpdatesDate(userDAO.getOrganizationId(), true, true, false, true, true, false,
		!Objects.isNull(newUser.getGroups()) && !newUser.getGroups().isEmpty());

	return new UserView(userDAO);
    }

    @Transactional
    public UserView updateUser(final String userId, final UpdateUserRequest.User newUserData) {
	if (Strings.isBlank(userId)) {
	    throw new IllegalArgumentException(USER_ID_CAN_NOT_BE_NULL_OR_EMPTY);
	}

	final Date now = new Date();

	UserEntity userDAO = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	boolean userInfoIsUpdated = false;

	if (!Strings.isBlank(newUserData.getName())) {
	    userDAO.setName(newUserData.getName());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getSurname())) {
	    userDAO.setSurname(newUserData.getSurname());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getEmail())) {
	    if (userRepository.existsByEmailAndIdNot(newUserData.getEmail(), userDAO.getId())) {
		throw new EmailAlreadyExistsException(newUserData.getEmail());
	    }

	    userDAO.setEmail(newUserData.getEmail());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getUpn())) {
	    if (userRepository.existsByUpnAndIdNot(newUserData.getUpn(), userDAO.getId())) {
		throw new UserPrincipalNameAlreadyExistsException(newUserData.getUpn());
	    }

	    userDAO.setUpn(newUserData.getUpn());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getPhone())) {
	    userDAO.setPhone(newUserData.getPhone());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getLandLine())) {
	    userDAO.setLandLine(newUserData.getLandLine());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getStatus())) {
	    userDAO.setStatus(newUserData.getStatus());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getType())) {
	    userDAO.setType(newUserData.getType());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getCanBeSearched())) {
	    userDAO.setCanBeSearched(newUserData.getCanBeSearched());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getDepartmentId())) {
	    userDAO.setDepartmentId(
		    Strings.isBlank(newUserData.getDepartmentId()) ? null : newUserData.getDepartmentId());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getUserRuleId())) {
	    userDAO.setUserRuleId(Strings.isBlank(newUserData.getUserRuleId()) ? null : newUserData.getUserRuleId());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getWebRoleId())) {
	    userDAO.setWebRoleId(Strings.isBlank(newUserData.getWebRoleId()) ? null : newUserData.getWebRoleId());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getMobileRoleId())) {
	    userDAO.setMobileRoleId(
		    Strings.isBlank(newUserData.getMobileRoleId()) ? null : newUserData.getMobileRoleId());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getPassword())) {
	    userDAO.setPassword(newUserData.getPassword());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getMicrosoftId())) {
	    userDAO.setMicrosoftId(newUserData.getMicrosoftId());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getEmployeeId())) {
	    userDAO.setEmployeeId(newUserData.getEmployeeId());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getWorkingCalendarId())) {
	    userDAO.setWorkingCalendarId(
		    Strings.isBlank(newUserData.getWorkingCalendarId()) ? null : newUserData.getWorkingCalendarId());
	    userInfoIsUpdated = true;
	}

	if (!Strings.isBlank(newUserData.getDataSource())) {
	    userDAO.setDataSource(newUserData.getDataSource());
	    userInfoIsUpdated = true;
	}

	if (!Objects.isNull(newUserData.getUpdateLastLoginDate())) {
	    userDAO.setLastLoginDate(now);
	    userInfoIsUpdated = true;
	}

	if (userInfoIsUpdated) {
	    userDAO.setLastModifiedDate(now);
	    userRepository.save(userDAO);
	}

	if (!Objects.isNull(newUserData.getGroups())) {
	    userGroupsRepository.deleteAllByUserGroupsPKUserId(userId);
	    userGroupsRepository.saveAll(newUserData.getGroups().stream()
		    .map(groupId -> new UserGroupsEntity(groupId, userId)).collect(Collectors.toList()));
	    updatesService.deleteUpdateUserResources(userId);
	}

	if (!Objects.isNull(newUserData.getDepartments())) {
	    userDepartmentsRepository.deleteAllByUserDepartmentsPKUserId(userId);
	    userDepartmentsRepository.saveAll(newUserData.getDepartments().stream()
		    .map(departmentId -> new UserDepartmentsEntity(userId, departmentId)).collect(Collectors.toList()));
	}

	updatesService.refreshUpdatesDate(userDAO.getOrganizationId(), true, true, false, true, true, false,
		!Objects.isNull(newUserData.getGroups()) && !newUserData.getGroups().isEmpty());

	return new UserView(userDAO);
    }

    @Transactional
    public void deleteUser(final String userId) {
	final UserEntity userDAO = this.userRepository.findById(userId)
		.orElseThrow(() -> new UserNotFoundException(userId));

	if (userGroupsRepository.existsByUserGroupsPKUserId(userId)) {
	    updatesService.refreshUpdatesDate(userDAO.getOrganizationId(), false, false, false, false, false, false,
		    true);
	}

	userDAO.setStatus(UserStatus.FIRED);
	userDAO.setFiredDate(new Date());
	userRepository.save(userDAO);
    }

    private UserEntity constructUser(CreateUserRequest.User newUser) {
	final Date now = new Date();

	UserEntity userDAO = new UserEntity(newUser.getName(), newUser.getSurname(), generateUserAlias(),
		newUser.getUpn(), newUser.getEmail(), newUser.getPassword(), newUser.getStatus(),
		newUser.getCanBeSearched(), newUser.getOrganizationId(), newUser.getType(), false, false);

	userDAO.setActivationReference(
		Strings.isBlank(newUser.getActivationReference()) ? null : newUser.getActivationReference());
	userDAO.setDepartmentId(Strings.isBlank(newUser.getDepartmentId()) ? null : newUser.getDepartmentId());
	userDAO.setMobileRoleId(Strings.isBlank(newUser.getMobileRoleId()) ? null : newUser.getMobileRoleId());
	userDAO.setPhone(Strings.isBlank(newUser.getPhone()) ? null : newUser.getPhone());
	userDAO.setLandLine(Strings.isBlank(newUser.getLandLine()) ? null : newUser.getLandLine());
	userDAO.setUserRuleId(newUser.getUserRuleId());
	userDAO.setWebRoleId(Strings.isBlank(newUser.getWebRoleId()) ? null : newUser.getWebRoleId());
	userDAO.setWorkingCalendarId(
		Strings.isBlank(newUser.getWorkingCalendarId()) ? null : newUser.getWorkingCalendarId());
	userDAO.setMicrosoftId(Strings.isBlank(newUser.getMicrosoftId()) ? null : newUser.getMicrosoftId());
	userDAO.setEmployeeId(Strings.isBlank(newUser.getEmployeeId()) ? null : newUser.getEmployeeId());
	userDAO.setDataSource(Strings.isBlank(newUser.getDataSource()) ? DATA_SOURCE_UNKNOWN : newUser.getDataSource());

	if (newUser.isIsSelfRegistration()) {
	    userDAO.setLastLoginDate(now);
	}

	userDAO.setCreatedDate(now);

	return userDAO;
    }

    private String generateUserAlias() {
	return UUID.randomUUID().toString().replace("-", "");
    }

    public void syncMicrosoftUsers(final SyncMicrosoftUsersRequest request) {

	final String organizationId = request.getOrganizationId();

	// Verificar si la organización tiene el filtro de office location
	// activado
	final Modules modulesConfig = fetchModulesConfig(organizationId);

	if (Objects.isNull(modulesConfig)) {
	    log.warn(MODULES_CONFIG_NOT_FOUND_LOG, organizationId);
	    return;
	}

	if (modulesConfig.isEnableMicrosoftUsersSync()) {

	    final MicrosoftAuth microsoftAuthConfig = fetchMicrosoftAuthConfig(organizationId, true);
	    if (Objects.isNull(microsoftAuthConfig)) {
		log.warn(MICROSOFT_AUTH_CONFIG_NOT_FOUND_LOG, organizationId);
		return;
	    }

	    final MicrosoftUsers microsoftUsersConfig = fetchMicrosoftUsersConfig(organizationId);
	    if (Objects.isNull(microsoftUsersConfig)) {
		log.warn(MICROSOFT_USERS_CONFIG_NOT_FOUND_LOG, organizationId);
		return;
	    }

	    fetchMicrosoftUsers(microsoftAuthConfig, microsoftUsersConfig, modulesConfig.isAllowMicrosoftGroupSync());
	}
    }

    private void fetchMicrosoftUsers(final MicrosoftAuth microsoftAuthConfig, final MicrosoftUsers microsoftUsersConfig,
	    boolean isAllowMicrosoftGroupSync) {
	final MSGraphService msGraphService = constructGraphService(microsoftUsersConfig);

	log.info(RETRIEVING_ORGANIZATION_USERS_FROM_AZURE_LOG, microsoftUsersConfig.getOrganizationId(),
		microsoftUsersConfig.getTenantId());
	final long start = System.currentTimeMillis();
	UserCollectionPage usersPage = msGraphService.fetchUsers(MS_USERS_TOP);
	UserCollectionRequestBuilder nextPage;
	AtomicInteger totalPages = new AtomicInteger(0);
	AtomicInteger totalUsers = new AtomicInteger(0);

	final MicrosoftGroups microsoftGroupsConfig = fetchMicrosoftGroupsConfig(
		microsoftAuthConfig.getOrganizationId());

	do {

	    usersPage.getCurrentPage().forEach(user -> {
		log.info(MICROSOFT_USER_FETCHED_LOG, user.id, user.userPrincipalName, user.mail);

		try {
		    createOrUpdateMicrosoftUser(user, microsoftAuthConfig, isAllowMicrosoftGroupSync,
			    microsoftGroupsConfig);
		} catch (Exception e) {
		    log.error(ERROR_PROCESSING_USER_LOG, user.id, e);
		}

		totalUsers.getAndIncrement();
	    });

	    totalPages.getAndIncrement();

	    nextPage = usersPage.getNextPage();
	    if (Objects.nonNull(usersPage.getNextPage())) {
		usersPage = msGraphService.fetchUsersNextPage(usersPage.getNextPage());
	    }
	} while (Objects.nonNull(nextPage));

	final long totalMillis = System.currentTimeMillis() - start;
	log.info(SYNC_USERS_RESULT_LOG, totalUsers.get(), totalPages.get(), totalMillis);
    }

    private void createOrUpdateMicrosoftUser(final User microsoftUser, final MicrosoftAuth microsoftAuthConfig,
	    final boolean microsoftGroupSyncEnabled, final MicrosoftGroups microsoftGroupsConfig) {
	log.info(CREATE_OR_UPDATE_USER_IN_BOOKKER_LOG, microsoftUser.id);

	final OfficeLocationFilter officeLocationFilter = constructOfficeLocationFilter(microsoftAuthConfig);

	if (!userHasValidOfficeLocation(microsoftUser.officeLocation, officeLocationFilter)) {
	    log.warn(USER_OFFICE_LOCATION_IS_NOT_VALID_LOG, microsoftUser.officeLocation, microsoftUser.id);
	    return;
	}

	UserView bookkerUser = null;

	try {
	    bookkerUser = findUser(microsoftUser.userPrincipalName, microsoftUser.mail, microsoftUser.id);
	} catch (UserNotFindException e) {
	    // Do nothing, its a new user
	}

	if (Objects.isNull(bookkerUser)) {
	    // Crear usuario
	    createMicrosoftUser(microsoftUser, microsoftAuthConfig, microsoftGroupSyncEnabled, microsoftGroupsConfig);
	} else {
	    // Actualizar usuario
	    updateMicrosoftUser(microsoftUser, bookkerUser, microsoftAuthConfig, microsoftGroupSyncEnabled,
		    microsoftGroupsConfig);
	}
    }

    private void createMicrosoftUser(final User microsoftUser, final MicrosoftAuth microsoftAuthConfig,
	    final boolean microsoftGroupSyncEnabled, final MicrosoftGroups microsoftGroupsConfig) {

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

	// Recuperar grupos por defecto de la organización
	final List<GroupView> defaultGroups = groupService.getDefaultOrganizationGroups(organizationId);
	if (!Objects.isNull(defaultGroups) && !defaultGroups.isEmpty()) {
	    newBookkerUser.setGroups(defaultGroups.stream().map(GroupView::getId).collect(Collectors.toList()));
	}

	// Recuperar regla por defecto de la organización
	final UserRuleView defaultUserRule = getDefaultOrganizationUserRule(organizationId);
	if (!Objects.isNull(defaultUserRule)) {
	    newBookkerUser.setUserRuleId(defaultUserRule.getId());
	}

	// En caso necesario, sincronizar los grupos del usuario con Microsoft
	if (microsoftGroupSyncEnabled) {
	    final SyncUserGroupsResult syncUserGroupsResult = syncMicrosftGroups(
		    microsoftAuthConfig.getOrganizationId(), microsoftGroupsConfig, microsoftUser, defaultGroups);
	    if (syncUserGroupsResult.isHasChanged()) {
		newBookkerUser.setGroups(
			syncUserGroupsResult.getGroups().stream().map(GroupView::getId).collect(Collectors.toList()));
	    }
	}

	final UserView createdUser = createUser(newBookkerUser);
	log.info(USER_CREATED_SUCCESSFULLY_LOG, createdUser.getId(), createdUser.getMicrosoftId(),
		createdUser.getEmail(), createdUser.getUpn());
    }

    private UserRuleView getDefaultOrganizationUserRule(String organizationId) {
	try {
	    return userRuleService.getDefaultUserRuleByOrganization(organizationId);
	} catch (DefaultUserRuleNotFoundException | UserRuleNotFoundException e) {
	    return null;
	}
    }

    private void updateMicrosoftUser(final User microsoftUser, UserView bookkerUser,
	    final MicrosoftAuth microsoftAuthConfig, final boolean microsoftGroupSyncEnabled,
	    final MicrosoftGroups microsoftGroupsConfig) {
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
	    final List<GroupView> userGroups = getUserGroups(bookkerUser.getId());

	    if (!Objects.isNull(userGroups)) {
		final SyncUserGroupsResult syncUserGroupsResult = syncMicrosftGroups(bookkerUser.getOrganizationId(),
			microsoftGroupsConfig, microsoftUser, userGroups);
		if (syncUserGroupsResult.isHasChanged()) {
		    log.info(USER_GROUPS_NEED_TO_BE_UPDATED_LOG);
		    updateUser.setGroups(syncUserGroupsResult.getGroups().stream().map(GroupView::getId)
			    .collect(Collectors.toList()));
		    mustBeUpdated = true;
		}
	    }

	}

	if (mustBeUpdated) {
	    updateUser(bookkerUser.getId(), updateUser);
	}
    }

    private List<GroupView> getUserGroups(String userId) {
	try {
	    return groupService.getUserGroups(userId);
	} catch (UserNotFoundException e) {
	    return new ArrayList<>();
	}
    }

    private SyncUserGroupsResult syncMicrosftGroups(final String organizationId,
	    final MicrosoftGroups microsoftGroupsConfig, final User microsoftUser,
	    final List<GroupView> bookkerUserGroupList) {

	boolean userGroupsChange = false;

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

	// Recuperamos todos los grupos sincronizados de una organización
	List<GroupView> synchronizedOrganizationGroupList = fetchOrganizationSynchronizedGroups(organizationId);

	if (Objects.isNull(synchronizedOrganizationGroupList)) {
	    // Solo puede ser null si se ha producido un error al recuperar los grupos de
	    // Microsoft.
	    return new SyncUserGroupsResult(bookkerUserGroupList, userGroupsChange);
	}

	// Añadimos todos los grupos no sincronizados que ya tenia el usuario
	final Set<GroupView> newUserGroupList = Objects.isNull(bookkerUserGroupList) ? new HashSet<>()
		: bookkerUserGroupList.stream().filter(g -> Strings.isBlank(g.getMicrosoftId()))
			.collect(Collectors.toSet());

	// Comprobamos los grupos sincronizados de la organización
	for (GroupView organizationGroup : synchronizedOrganizationGroupList) {
	    final String groupMicrosoftId = organizationGroup.getMicrosoftId();
	    final String groupId = organizationGroup.getId();

	    if (Strings.isBlank(groupMicrosoftId)) {
		continue;
	    }

	    final boolean groupIsInAzure = microsoftUserGroupIdList.contains(groupMicrosoftId);
	    final boolean userHasGroup = !Objects.isNull(bookkerUserGroupList)
		    && bookkerUserGroupList.stream().anyMatch(g -> Objects.equals(groupId, g.getId()));

	    // Añadir grupo -> Si tiene el grupo asignado en azure y no en Bookker
	    if (groupIsInAzure && !userHasGroup) {
		newUserGroupList.add(organizationGroup);
		userGroupsChange = true;
	    }

	    // Quitar grupo -> Si no tiene el grupo asignado en azure y sí en Booker
	    if (!groupIsInAzure && userHasGroup) {
		userGroupsChange = true;
	    }
	}

	return new SyncUserGroupsResult(new ArrayList<>(newUserGroupList), userGroupsChange);
    }

    private List<GroupView> fetchOrganizationSynchronizedGroups(final String organizationId) {
	return groupService.getOrganizationGroups(organizationId, true);
    }

    private List<String> fetchMicrosoftUserGroups(final User microsoftUser, final MSGraphService msGraphService) {
	try {
	    return msGraphService.fetchUserGroups(microsoftUser.id);
	} catch (GraphServiceException e) {
	    if (404 == e.getResponseCode()) {
		log.error(USER_WITH_ID_NOT_FOUND_IN_MICROSOFT_LOG, microsoftUser.id);
	    }

	    return new ArrayList<>();
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

    private Modules fetchModulesConfig(final String organizationId) {
	try {
	    return organizationsClient.getOrganizationModules(organizationId, null);
	} catch (FeignException e) {
	    if (404 == e.status()) {
		return null;
	    }

	    throw new OrganizationApiCommunicationException(e.getMessage());
	}
    }

    private MicrosoftAuth fetchMicrosoftAuthConfig(final String organizationId, final boolean withOfficeLocations) {
	try {
	    return organizationsClient.getMicrosoftAuthConfig(organizationId, withOfficeLocations);
	} catch (FeignException e) {
	    if (404 == e.status()) {
		return null;
	    }

	    throw new OrganizationApiCommunicationException(e.getMessage());
	}
    }

    private MicrosoftUsers fetchMicrosoftUsersConfig(String organizationId) {
	try {
	    return organizationsClient.getMicrosoftUsersConfig(organizationId);
	} catch (FeignException e) {
	    if (404 == e.status()) {
		return null;
	    }

	    throw new OrganizationApiCommunicationException(e.getMessage());
	}
    }

    private MicrosoftGroups fetchMicrosoftGroupsConfig(String organizationId) {
	try {
	    return organizationsClient.getMicrosoftGroupsConfig(organizationId);
	} catch (FeignException e) {
	    if (404 == e.status()) {
		return null;
	    }

	    throw new OrganizationApiCommunicationException(e.getMessage());
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

    @Data
    private class OfficeLocationFilter {

	private final boolean enabled;
	private final List<String> officeLocations;

    }

    @Data
    private class SyncUserGroupsResult {

	private final List<GroupView> groups;
	private final boolean hasChanged;

    }

}
