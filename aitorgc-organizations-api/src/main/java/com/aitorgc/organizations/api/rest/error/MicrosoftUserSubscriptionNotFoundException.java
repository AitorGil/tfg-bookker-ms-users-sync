package com.aitorgc.organizations.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.Value;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class MicrosoftUserSubscriptionNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = -298962193992453201L;

	private static final String CLIENT_STATE_MESSAGE = "Microsoft user subscription with client state %s not found";
	private static final String SUBSCRIPTION_ID_MESSAGE = "Microsoft user subscription with subscription id %s not found from organization with id %s";

	public MicrosoftUserSubscriptionNotFoundException(final ClientState clientState) {
		super(HttpStatus.NOT_FOUND, String.format(CLIENT_STATE_MESSAGE, clientState.getId()));
	}

	public MicrosoftUserSubscriptionNotFoundException(final SubscriptionId subscriptionId) {
		super(HttpStatus.NOT_FOUND,
				String.format(SUBSCRIPTION_ID_MESSAGE, subscriptionId.getId(), subscriptionId.getOrganizationId()));
	}

	@Value
	public static class ClientState {
		private final String id;

	}

	@Value
	public static class SubscriptionId {
		private final String id;
		private final String organizationId;

	}
}
