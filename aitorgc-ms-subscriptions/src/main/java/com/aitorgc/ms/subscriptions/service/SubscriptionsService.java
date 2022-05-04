package com.aitorgc.ms.subscriptions.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aitorgc.ms.subscriptions.errors.OrganizationApiCommunicationException;
import com.aitorgc.ms.subscriptions.internalapis.organizations.CreateMicrosoftUsersSubscriptionRequest;
import com.aitorgc.ms.subscriptions.internalapis.organizations.MicrosoftUserSubscription;
import com.aitorgc.ms.subscriptions.internalapis.organizations.MicrosoftUsers;
import com.aitorgc.ms.subscriptions.internalapis.organizations.Modules;
import com.aitorgc.ms.subscriptions.internalapis.organizations.Organization;
import com.aitorgc.ms.subscriptions.internalapis.organizations.OrganizationsClient;
import com.aitorgc.ms.subscriptions.internalapis.organizations.UpdateSubscriptionRequest;

import feign.FeignException;
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
public class SubscriptionsService {

	private static final String MICROSOFT_SUBSCRIPTIONS_ENDPOINT = "/microsoft/subscriptions";
	
	private static final String FAILED_TO_DELETE_USER_SUBSCRIPTION_FOR_ORGANIZATION_LOG = "Failed to delete user subscription {} for organization with id {}";
	private static final String ERROR_CREATING_USER_SUBSCRIPTION_FOR_ORGANIZATION_LOG = "Error creating user subscription for organization with id {}";
	private static final String ORGANIZATION_HAS_SUBSCRIPTIONS_LOG = "Organization with id {} has {} subscriptions";
	private static final String ORGANIZATION_HAS_NO_SUBSCRIPTIONS_LOG = "Organization with id {} has no subscriptions";
	private static final String ORGANIZATION_HAS_USER_SYNCHRONIZATION_MODULE_LOG = "The organization with id {} has contracted the user synchronization module";
	private static final String USER_SYNCHRONIZATION_MODULE_SETTINGS_NOT_FOUND_LOG = "The organization with id {} does not have the user synchronization module settings";
	private static final String MODULE_CONFIGURATION_NOT_FOUND_LOG = "The organization with id {} does not have the module configuration";
	
	private static final String CREATING_USER_SUBSCRIPTION = "Creating user subscription from organization with id {}";
	private static final String UPDATING_USER_SUBSCRIPTION = "Updating user subscription with id {} from organization with id {}";
	private static final String DELETING_USER_SUBSCRIPTION = "Deleting user subscription with id {} from organization with id {}";

	private static final String CHANGE_TYPE = "created,updated,deleted";

	private static final int TOP = 50;

	private static final int USER_SUBSCRIPTION_MAX_LIFE_MINUTES = 41759; // 41760

	@Value("${bookker.internal.apis.users.url}")
	private String usersApi;

	private final OrganizationsClient organizationsClient;

	public void manageUsersSubscriptionsForOrganizations() {

		// Recuperar todas las organizaciones que tienen el módulo contratado
		int page = 0;
		List<Organization> organizationList = organizationsClient.getOrganizations(page, TOP).getOrganizations();

		do {
			organizationList.forEach(this::processOrganization);

			page++;
			organizationList = organizationsClient.getOrganizations(page, TOP).getOrganizations();
		} while (!Objects.isNull(organizationList) && !organizationList.isEmpty());

	}

	private void processOrganization(Organization organization) {
		// Recupera los módulos de la organización
		final Modules modules = fetchModules(organization.getId());

		if (Objects.isNull(modules)) {
			log.warn(MODULE_CONFIGURATION_NOT_FOUND_LOG, organization.getId());
			return;
		}

		if (modules.isEnableMicrosoftUsersSync()) {
			// Revisar si el módulo de sincronización de usuarios está activado
			final MicrosoftUsers microsoftUsersConfig = fetchMicrosoftUsers(organization.getId());

			if (Objects.isNull(microsoftUsersConfig)) {
				log.warn(USER_SYNCHRONIZATION_MODULE_SETTINGS_NOT_FOUND_LOG,
						organization.getId());
				return;
			}

			log.info(ORGANIZATION_HAS_USER_SYNCHRONIZATION_MODULE_LOG,
					organization.getId());

			// Para cada una de ellas, recuperamos sus suscripciones de usuarios
			int page = 0;
			List<MicrosoftUserSubscription> subscriptionList = organizationsClient
					.getSubscriptions(organization.getId(), page, TOP).getSubscriptions();

			if (subscriptionList.isEmpty()) {
				log.info(ORGANIZATION_HAS_NO_SUBSCRIPTIONS_LOG, organization.getId());
				// No hay suscripciones

				createMicrosoftUsersSubscription(organization, microsoftUsersConfig.getApplicationId());

			} else {
				log.info(ORGANIZATION_HAS_SUBSCRIPTIONS_LOG, organization.getId(), subscriptionList.size());
				// Si tiene alguna suscripción, revisamos la fecha de expiración y la
				// actualizamos o la recreamos
				checkCreatedMicrosoftUsersSubscriptions(organization, subscriptionList);
			}

		}
	}

	private void createMicrosoftUsersSubscription(Organization organization, String applicationId) {
		try {
			final String notificationUrl = usersApi + MICROSOFT_SUBSCRIPTIONS_ENDPOINT;

			final CreateMicrosoftUsersSubscriptionRequest createSubscriptionRequest = CreateMicrosoftUsersSubscriptionRequest
					.builder().applicationId(applicationId).changeType(CHANGE_TYPE)
					.expirationDateTime(OffsetDateTime.now().plusMinutes(USER_SUBSCRIPTION_MAX_LIFE_MINUTES))
					.notificationUrl(notificationUrl).build();

			organizationsClient.createSubscription(organization.getId(), createSubscriptionRequest);
			log.info(CREATING_USER_SUBSCRIPTION, organization.getId());
		} catch (FeignException e) {
			log.error(ERROR_CREATING_USER_SUBSCRIPTION_FOR_ORGANIZATION_LOG, organization.getId(),
					e);
		}
	}

	private void checkCreatedMicrosoftUsersSubscriptions(Organization organization,
			List<MicrosoftUserSubscription> subscriptionList) {
		if (Objects.isNull(subscriptionList) || subscriptionList.isEmpty()) {
			return;
		}

		subscriptionList.forEach(subscription -> checkCreatedMicrosoftusersSubscription(organization, subscription));
	}

	private void checkCreatedMicrosoftusersSubscription(Organization organization,
			MicrosoftUserSubscription subscription) {
		if (subscription.getExpirationDateTime().isBefore(OffsetDateTime.now())) {
			// Subscripción expirada. Hay que eliminarla y recrearla
			deleteMicrosoftUsersSubscription(organization, subscription.getId());
			createMicrosoftUsersSubscription(organization, subscription.getApplicationId());

		} else if (subscription.getExpirationDateTime().minusWeeks(1).isBefore(OffsetDateTime.now())) {
			updateMicrosoftUsersSubscription(organization, subscription.getId());
		}
	}

	private void updateMicrosoftUsersSubscription(Organization organization, String subscriptionId) {
		final UpdateSubscriptionRequest updateSubscriptionRequest = UpdateSubscriptionRequest.builder()
				.expirationDateTime(OffsetDateTime.now().plusMinutes(USER_SUBSCRIPTION_MAX_LIFE_MINUTES)).build();

		organizationsClient.updateSubscription(organization.getId(), subscriptionId, updateSubscriptionRequest);
		log.info(UPDATING_USER_SUBSCRIPTION, subscriptionId, organization.getId());
	}

	private void deleteMicrosoftUsersSubscription(Organization organization, String subscriptionId) {
		try {
			organizationsClient.deleteSubscription(organization.getId(), subscriptionId);
			log.info(DELETING_USER_SUBSCRIPTION, subscriptionId, organization.getId());
		} catch (FeignException e) {
			log.error(FAILED_TO_DELETE_USER_SUBSCRIPTION_FOR_ORGANIZATION_LOG, subscriptionId,
					organization.getId(), e);
		}
	}

	private Modules fetchModules(String organizationId) {
		try {
			return organizationsClient.getOrganizationModules(organizationId);

		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw new OrganizationApiCommunicationException(e);
		}
	}

	private MicrosoftUsers fetchMicrosoftUsers(final String organizationId) {
		try {
			return organizationsClient.getMicrosoftUsersConfig(organizationId);

		} catch (FeignException e) {
			if (404 == e.status()) {
				return null;
			}

			throw new OrganizationApiCommunicationException(e);
		}
	}

}
