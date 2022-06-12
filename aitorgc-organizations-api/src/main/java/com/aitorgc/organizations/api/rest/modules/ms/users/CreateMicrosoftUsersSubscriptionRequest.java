package com.aitorgc.organizations.api.rest.modules.ms.users;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
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
