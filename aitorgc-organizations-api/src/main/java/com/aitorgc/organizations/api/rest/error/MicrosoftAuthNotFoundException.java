package com.aitorgc.organizations.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class MicrosoftAuthNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = -298962193992453201L;

	private static final String MESSAGE = "Microsoft auth config from organization with id %s not found";

	public MicrosoftAuthNotFoundException(String id) {
		super(HttpStatus.NOT_FOUND, String.format(MESSAGE, id));
	}

}
