package com.aitorgc.ms.subscriptions.internalapis.organizations;

import java.io.Serializable;
import java.time.OffsetDateTime;

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
public class UpdateSubscriptionRequest implements Serializable {

	private static final long serialVersionUID = 4995417541582031101L;

	@NotNull
	private final OffsetDateTime expirationDateTime;
}
