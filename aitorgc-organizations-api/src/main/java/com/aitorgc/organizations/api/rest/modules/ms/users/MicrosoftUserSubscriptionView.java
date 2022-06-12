package com.aitorgc.organizations.api.rest.modules.ms.users;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@Builder
public class MicrosoftUserSubscriptionView implements Serializable {

	private static final long serialVersionUID = -5748202479148760539L;

	private final String id;
	private final String applicationId;
	private final String changeType;
	private final String clientState;
	private final OffsetDateTime expirationDateTime;
	private final String latestSupportedTlsVersion;
	private final String notificationUrl;
	private final String resource;
	private final String organizationId;
	private final Date createdDate;
	private final String createdBy;
	private final Date lastModifiedDate;
	private final String lastModifiedBy;
}
