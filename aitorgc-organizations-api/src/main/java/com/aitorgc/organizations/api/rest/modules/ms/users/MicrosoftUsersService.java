package com.aitorgc.organizations.api.rest.modules.ms.users;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitorgc.organizations.api.model.OrganizationRepository;
import com.aitorgc.organizations.api.model.modules.ms.users.MicrosoftUsersEntity;
import com.aitorgc.organizations.api.model.modules.ms.users.MicrosoftUsersRepository;
import com.aitorgc.organizations.api.model.modules.ms.users.MicrosoftUsersSubscriptionEntity;
import com.aitorgc.organizations.api.model.modules.ms.users.MicrosoftUsersSubscriptionRepository;
import com.aitorgc.organizations.api.rest.error.MicrosoftUserSubscriptionNotFoundException;
import com.aitorgc.organizations.api.rest.error.MicrosoftUsersIsMisconfiguredException;
import com.aitorgc.organizations.api.rest.error.MicrosoftUsersNotFoundException;
import com.aitorgc.organizations.api.rest.error.OrganizationNotFoundException;
import com.aitorgc.organizations.api.util.msgraph.ApplicationCredentials;
import com.aitorgc.organizations.api.util.msgraph.CreateSubscriptionRequest;
import com.aitorgc.organizations.api.util.msgraph.MSGraphService;
import com.aitorgc.organizations.api.util.msgraph.Resource;
import com.aitorgc.organizations.api.util.msgraph.ResourceType;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.models.Subscription;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@RequiredArgsConstructor
public class MicrosoftUsersService {

	private static final String ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY = "organizationId can not be null or empty";
	private static final String SUBSCRIPTION_ID_CAN_NOT_BE_NULL_OR_EMPTY = "subscriptionId can not be null or empty";

	private static final String TLS_VERSION = "v1_2";

	private static final Resource resource = Resource.builder().type(ResourceType.USERS).build();

	private final MicrosoftUsersRepository microsoftUsersRepository;
	private final MicrosoftUsersSubscriptionRepository microsoftUsersSubscriptionRepository;
	private final OrganizationRepository organizationRepository;

	@Transactional(readOnly = true)
	public MicrosoftUsersView getMicrosoftUsersConfig(String organizationId) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (!organizationRepository.existsById(organizationId)) {
			throw new OrganizationNotFoundException(organizationId);
		}

		final MicrosoftUsersEntity microsoftUsersEntity = microsoftUsersRepository.findById(organizationId)
				.orElseThrow(() -> new MicrosoftUsersNotFoundException(organizationId));

		return constructMicrosoftUsersView(microsoftUsersEntity);
	}

	@Transactional(readOnly = true)
	public List<MicrosoftUserSubscriptionView> getSubscriptions(final String organizationId, final int page,
			final int top) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (!organizationRepository.existsById(organizationId)) {
			throw new OrganizationNotFoundException(organizationId);
		}

		return microsoftUsersSubscriptionRepository.findAllByOrganizationId(organizationId, PageRequest.of(page, top))
				.stream().map(this::constructUserSubscriptionView).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public MicrosoftUserSubscriptionView getSubscriptionByClientState(final String clientState) {
		final MicrosoftUsersSubscriptionEntity entity = microsoftUsersSubscriptionRepository
				.findByClientState(clientState);

		if (Objects.isNull(entity)) {
			throw new MicrosoftUserSubscriptionNotFoundException(
					new MicrosoftUserSubscriptionNotFoundException.ClientState(clientState));
		}

		return constructUserSubscriptionView(entity);
	}

	@Transactional
	public MicrosoftUserSubscriptionView createSubscription(final String organizationId,
			final CreateMicrosoftUsersSubscriptionRequest request) {

		final MSGraphService msGraphService = constructMicrosftGraphService(organizationId);

		final String clientState = generateRandomClientState();

		final CreateSubscriptionRequest createSubscriptionRequest = new CreateSubscriptionRequest(
				request.getChangeType(), clientState, request.getExpirationDateTime(), request.getNotificationUrl(),
				resource, TLS_VERSION);

		final Subscription subscription = msGraphService.createSubscription(createSubscriptionRequest);

		final MicrosoftUsersSubscriptionEntity entity = new MicrosoftUsersSubscriptionEntity(subscription.id,
				request.getApplicationId(), request.getChangeType(), clientState, request.getExpirationDateTime(),
				TLS_VERSION, request.getNotificationUrl(), resource.getPath(), organizationId);

		microsoftUsersSubscriptionRepository.save(entity);

		return constructUserSubscriptionView(entity);
	}

	@Transactional
	public MicrosoftUserSubscriptionView updateSubscription(final String organizationId, final String subscriptionId,
			final UpdateSubscriptionRequest request) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (Strings.isBlank(subscriptionId)) {
			throw new IllegalArgumentException(SUBSCRIPTION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (!organizationRepository.existsById(organizationId)) {
			throw new OrganizationNotFoundException(organizationId);
		}

		final MicrosoftUsersSubscriptionEntity entity = microsoftUsersSubscriptionRepository
				.findByIdAndOrganizationId(subscriptionId, organizationId);

		if (Objects.isNull(entity)) {
			throw new MicrosoftUserSubscriptionNotFoundException(
					new MicrosoftUserSubscriptionNotFoundException.SubscriptionId(subscriptionId, organizationId));
		}

		entity.setExpirationDateTime(request.getExpirationDateTime());

		final MSGraphService msGraphService = constructMicrosftGraphService(organizationId);

		msGraphService.updateSubscription(subscriptionId, request.getExpirationDateTime());

		microsoftUsersSubscriptionRepository.save(entity);

		return constructUserSubscriptionView(entity);
	}

	@Transactional
	public void deleteSubscription(final String organizationId, final String subscriptionId) {
		if (Strings.isBlank(organizationId)) {
			throw new IllegalArgumentException(ORGANIZATION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (Strings.isBlank(subscriptionId)) {
			throw new IllegalArgumentException(SUBSCRIPTION_ID_CAN_NOT_BE_NULL_OR_EMPTY);
		}

		if (!organizationRepository.existsById(organizationId)) {
			throw new OrganizationNotFoundException(organizationId);
		}

		if (!microsoftUsersSubscriptionRepository.existsByIdAndOrganizationId(subscriptionId, organizationId)) {
			throw new MicrosoftUserSubscriptionNotFoundException(
					new MicrosoftUserSubscriptionNotFoundException.SubscriptionId(subscriptionId, organizationId));
		}

		final MSGraphService msGraphService = constructMicrosftGraphService(organizationId);

		try {
			msGraphService.deleteSubscription(subscriptionId);

			microsoftUsersSubscriptionRepository.deleteById(subscriptionId);
		} catch (GraphServiceException e) {
			if (404 != e.getResponseCode()) {
				throw e;
			}

		}
	}

	private String generateRandomClientState() {
		return UUID.randomUUID().toString();
	}

	private MSGraphService constructMicrosftGraphService(final String organizationId) {
		final MicrosoftUsersEntity entity = microsoftUsersRepository.findByOrganizationId(organizationId);

		if (Objects.isNull(entity)) {
			throw new MicrosoftUsersNotFoundException(organizationId);
		}

		if (Strings.isBlank(entity.getApplicationId()) || Strings.isBlank(entity.getApplicationSecret())
				|| Strings.isBlank(entity.getTenantId())) {
			throw new MicrosoftUsersIsMisconfiguredException(organizationId);
		}

		final ApplicationCredentials credentials = new ApplicationCredentials(entity.getApplicationId(),
				entity.getApplicationSecret(), entity.getTenantId());

		return new MSGraphService(credentials);
	}

	private MicrosoftUsersView constructMicrosoftUsersView(final MicrosoftUsersEntity dao) {
		return MicrosoftUsersView.builder().organizationId(dao.getOrganizationId()).tenantId(dao.getTenantId())
				.applicationId(dao.getApplicationId()).applicationSecret(dao.getApplicationSecret())
				.expirationDate(dao.getExpirationDate()).createdBy(dao.getCreatedBy()).createdDate(dao.getCreatedDate())
				.lastModifiedBy(dao.getLastModifiedBy()).lastModifiedDate(dao.getLastModifiedDate()).build();
	}

	private MicrosoftUserSubscriptionView constructUserSubscriptionView(final MicrosoftUsersSubscriptionEntity entity) {
		return MicrosoftUserSubscriptionView.builder().id(entity.getId()).applicationId(entity.getApplicationId())
				.changeType(entity.getChangeType()).clientState(entity.getClientState())
				.expirationDateTime(entity.getExpirationDateTime())
				.latestSupportedTlsVersion(entity.getLatestSupportedTlsVersion())
				.notificationUrl(entity.getNotificationUrl()).resource(entity.getResource())
				.organizationId(entity.getOrganizationId()).createdDate(entity.getCreatedDate())
				.createdBy(entity.getCreatedBy()).lastModifiedBy(entity.getLastModifiedBy())
				.lastModifiedDate(entity.getLastModifiedDate()).build();
	}

}
