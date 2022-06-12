package com.aitorgc.organizations.api.rest.modules.ms.users;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateSubscriptionRequest implements Serializable {

	private static final long serialVersionUID = 4995417541582031101L;

	@NotNull
	private OffsetDateTime expirationDateTime;
}
