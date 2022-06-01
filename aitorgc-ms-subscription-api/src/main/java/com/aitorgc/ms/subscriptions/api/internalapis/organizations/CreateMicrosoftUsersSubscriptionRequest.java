package com.aitorgc.ms.subscriptions.api.internalapis.organizations;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@Builder
public class CreateMicrosoftUsersSubscriptionRequest implements Serializable {

	private static final long serialVersionUID = -5756707847075388263L;

	@NotBlank
	private final String applicationId;

	@NotBlank
	private final String changeType;

	@NotNull
	private final OffsetDateTime expirationDateTime;

	@NotBlank
	private final String notificationUrl;

}
