package com.aitorgc.organizations.api.util.msgraph;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.Subscription;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.SubscriptionCollectionPage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aitor Gil Callejo
 */
@Slf4j
public class MSGraphService {

	private final GraphServiceClient<?> graphClient;

	public MSGraphService(final ApplicationCredentials applicationCredentials) {

		final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
				.clientId(applicationCredentials.getClientId()).clientSecret(applicationCredentials.getSecretId())
				.tenantId(applicationCredentials.getTenantId()).build();

		final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(
				clientSecretCredential);

		graphClient = GraphServiceClient.builder().authenticationProvider(tokenCredentialAuthProvider).buildClient();

		log.debug("Graph client's created successfully!");
	}

	public Subscription createSubscription(final CreateSubscriptionRequest request) {
		Subscription subscription = new Subscription();
		subscription.changeType = request.getChangeType();
		subscription.notificationUrl = request.getNotificationUrl();
		subscription.resource = request.getResource();
		subscription.expirationDateTime = request.getExpirationDateTime();
		subscription.clientState = request.getClientState();
		subscription.latestSupportedTlsVersion = request.getLatestSupportedTlsVersion();

		return graphClient.subscriptions().buildRequest().post(subscription);
	}

	public Subscription getSubscription(final String id) {
		try {
			return graphClient.subscriptions(id).buildRequest().get();

		} catch (Exception e) {
			return null;
		}
	}

	public List<Subscription> getSubscriptions() {
		List<Subscription> subscriptions = new ArrayList<>();

		SubscriptionCollectionPage page = graphClient.subscriptions().buildRequest().get();

		subscriptions.addAll(page.getCurrentPage());

		while (!Objects.isNull(page.getNextPage())) {
			page = page.getNextPage().buildRequest().get();

			subscriptions.addAll(page.getCurrentPage());
		}

		return subscriptions;
	}

	public Subscription updateSubscription(final String id, final OffsetDateTime expirationDateTime) {
		Subscription subscription = new Subscription();
		subscription.expirationDateTime = expirationDateTime;

		return graphClient.subscriptions(id).buildRequest().patch(subscription);
	}

	public void deleteSubscription(final String id) {
		graphClient.subscriptions(id).buildRequest().delete();
	}

}
