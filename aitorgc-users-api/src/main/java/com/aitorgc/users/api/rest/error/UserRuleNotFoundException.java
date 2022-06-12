package com.aitorgc.users.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class UserRuleNotFoundException extends ResponseStatusException {

    private static final long serialVersionUID = 8340842108195715226L;

    public UserRuleNotFoundException(final String userId) {
	super(HttpStatus.NOT_FOUND, String.format("User rule with id %s not found", userId));
    }

}
