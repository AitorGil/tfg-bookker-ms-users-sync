package com.aitorgc.users.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class DefaultUserRuleNotFoundException extends ResponseStatusException {

    private static final String MESSAGE = "Default user rule from organization with id %s not found";
    private static final long serialVersionUID = 8340842108195715226L;

    public DefaultUserRuleNotFoundException(final String organizationId) {
	super(HttpStatus.NOT_FOUND, String.format(MESSAGE, organizationId));
    }

}
