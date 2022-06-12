package com.aitorgc.users.api.util.msgraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.DirectoryObjectGetMemberGroupsParameterSet;
import com.microsoft.graph.requests.DirectoryObjectGetMemberGroupsCollectionPage;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.UserCollectionPage;
import com.microsoft.graph.requests.UserCollectionRequestBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aitor Gil Callejo
 */
@Slf4j
public class MSGraphService {

	private static final String NEXT_PAGE_BUILDER_CAN_NOT_BE_NULL_LOG = "nextPageBuilder can not be null";
	private static final String TOP_CAN_NOT_BE_LESS_THAN_1_LOG = "top can not be less than 1";
	private static final String USER_SELECT_FIELDS = "id,displayName,mail,givenName,surname,officeLocation,phone,employeeId,userPrincipalName";

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

	public UserCollectionPage fetchUsers(Integer top) {
		if (Objects.isNull(top)) {
			return graphClient.users().buildRequest().select(USER_SELECT_FIELDS).get();
		}

		if (top < 1) {
			throw new IllegalArgumentException(TOP_CAN_NOT_BE_LESS_THAN_1_LOG);
		}

		return graphClient.users().buildRequest().select(USER_SELECT_FIELDS).top(top).get();
	}

	public UserCollectionPage fetchUsersNextPage(UserCollectionRequestBuilder nextPageBuilder) {
		if (Objects.isNull(nextPageBuilder)) {
			throw new IllegalArgumentException(NEXT_PAGE_BUILDER_CAN_NOT_BE_NULL_LOG);
		}

		return nextPageBuilder.buildRequest().select(USER_SELECT_FIELDS).get();
	}

	public List<String> fetchUserGroups(final String userId) {
		final List<String> groups = new ArrayList<>();

		final DirectoryObjectGetMemberGroupsParameterSet parameterSet = DirectoryObjectGetMemberGroupsParameterSet
				.newBuilder().withSecurityEnabledOnly(false).build();

		DirectoryObjectGetMemberGroupsCollectionPage page = graphClient.users(userId).getMemberGroups(parameterSet)
				.buildRequest().post();

		groups.addAll(page.getCurrentPage());

		while (!Objects.isNull(page.getNextPage())) {
			page = page.getNextPage().buildRequest().post();

			groups.addAll(page.getCurrentPage());
		}

		return groups;
	}

}
