package com.aitorgc.ms.subscriptions.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrganizationApiCommunicationException extends ResponseStatusException {

	private static final long serialVersionUID = 8340842108195715226L;

	private static final String MESSAGE = "Communication error with internal Organizations API. Error info: %s";

	public OrganizationApiCommunicationException(final Exception e) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, String.format(MESSAGE, e.getMessage()), e.getCause());
	}
}