package com.aitorgc.ms.subscriptions.api.msgraph;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;

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

	public User getUser(String userId) {
		return graphClient.users(userId).buildRequest()
				.select("id,displayName,mail,givenName,surname,officeLocation,phone,employeeId,userPrincipalName")
				.get();
	}

}
