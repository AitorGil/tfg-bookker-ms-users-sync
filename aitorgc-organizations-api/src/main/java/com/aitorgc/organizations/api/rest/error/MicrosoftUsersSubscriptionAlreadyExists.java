package com.aitorgc.organizations.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class MicrosoftUsersSubscriptionAlreadyExists extends ResponseStatusException {

	private static final long serialVersionUID = -298962193992453201L;

	private static final String MESSAGE = "Microsoft users subscription with id %s already exists";

	public MicrosoftUsersSubscriptionAlreadyExists(String id) {
		super(HttpStatus.CONFLICT, String.format(MESSAGE, id));
	}
}
