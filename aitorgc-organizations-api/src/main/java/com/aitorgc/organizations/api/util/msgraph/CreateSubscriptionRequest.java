package com.aitorgc.organizations.api.util.msgraph;

import java.time.OffsetDateTime;
import java.util.Objects;

import lombok.Value;

/**
 *
 * @author Aitor Gil Callejo
 */
@Value
public class CreateSubscriptionRequest {

	private final String changeType;
	private final String clientState;
	private OffsetDateTime expirationDateTime;
	private final String notificationUrl;
	private final String resource;
	private final String latestSupportedTlsVersion;

	public CreateSubscriptionRequest(String changeType, String clientState, OffsetDateTime expirationDateTime,
			String notificationUrl, Resource resource, String latestSupportedTlsVersion) {
		this.changeType = changeType;
		this.clientState = clientState;
		this.latestSupportedTlsVersion = latestSupportedTlsVersion;

		if (Objects.nonNull(clientState) && clientState.length() > 128) {
			throw new IllegalArgumentException("The maximum length of clientState is 128");
		}

		this.expirationDateTime = expirationDateTime;
		this.notificationUrl = notificationUrl;

		if (Objects.isNull(resource)) {
			throw new IllegalArgumentException("resource can not be null");
		}

		this.resource = resource.getPath();

	}

}
