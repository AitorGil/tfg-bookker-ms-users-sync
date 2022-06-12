package com.aitorgc.users.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class OrganizationApiCommunicationException extends ResponseStatusException {

    private static final long serialVersionUID = 8340842108195715226L;

    private static final String MESSAGE = "Communication error with internal Organizations API. Error info: %s";

    public OrganizationApiCommunicationException(final String error) {
	super(HttpStatus.INTERNAL_SERVER_ERROR, String.format(MESSAGE, error));
    }
}