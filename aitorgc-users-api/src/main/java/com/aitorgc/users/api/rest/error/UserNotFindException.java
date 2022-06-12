package com.aitorgc.users.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aitor Gil Callejo
 *
 */
public class UserNotFindException extends ResponseStatusException {

    private static final long serialVersionUID = 8340842108195715226L;

    public UserNotFindException(final String upn, final String microsoftId, final String email) {
        super(HttpStatus.NOT_FOUND, String.format("User not find. User principal name: %s, MicrosoftId: %s, Email: %s", upn, microsoftId, email));
    }

}
