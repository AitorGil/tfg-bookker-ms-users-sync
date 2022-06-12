package com.aitorgc.users.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class BadRequestException extends ResponseStatusException {

	private static final long serialVersionUID = 8340842108195715226L;

	public BadRequestException(final String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
