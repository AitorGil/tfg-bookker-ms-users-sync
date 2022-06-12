package com.aitorgc.organizations.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class MicrosoftUsersIsMisconfiguredException extends ResponseStatusException {

	private static final long serialVersionUID = -298962193992453201L;

	private static final String MESSAGE = "Microsoft users module is misconfigured from organization with id %s not found";

	public MicrosoftUsersIsMisconfiguredException(String organizationId) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, String.format(MESSAGE, organizationId));
	}

}
