package com.aitorgc.organizations.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
public class ModulesExtraNotFoundException extends ResponseStatusException {

    private static final long serialVersionUID = -298962193992453201L;

    private static final String MESSAGE = "ModulesExtra with id %s not found";

    public ModulesExtraNotFoundException(String id) {
	super(HttpStatus.NOT_FOUND, String.format(MESSAGE, id));
    }

}
