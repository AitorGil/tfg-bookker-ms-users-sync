package com.aitorgc.users.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aitor Gil Callejo
 *
 */
public class EmailAlreadyExistsException extends ResponseStatusException {

    private static final long serialVersionUID = 8340842108195715226L;

    public EmailAlreadyExistsException(final String upn) {
        super(HttpStatus.CONFLICT, String.format("%s email already exists", upn));
    }

}
