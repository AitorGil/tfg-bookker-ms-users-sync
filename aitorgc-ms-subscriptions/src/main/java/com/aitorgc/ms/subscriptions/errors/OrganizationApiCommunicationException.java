package com.aitorgc.ms.subscriptions.errors;

public class OrganizationApiCommunicationException extends RuntimeException {

	private static final long serialVersionUID = 8340842108195715226L;

	private static final String MESSAGE = "Communication error with internal Organizations API. Error info: %s";

	public OrganizationApiCommunicationException(final Exception e) {
		super(String.format(MESSAGE, e.getMessage()), e.getCause());
	}
}