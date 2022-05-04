package com.aitorgc.ms.subscriptions.internalapis.organizations;

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
public class MicrosoftUserSubscription implements Serializable {

	private static final long serialVersionUID = -5748202479148760539L;

	private String id;
	private String applicationId;
	private String changeType;
	private String clientState;
	private OffsetDateTime expirationDateTime;
	private String latestSupportedTlsVersion;
	private String notificationUrl;
	private String resource;
	private String organizationId;
	private Date createdDate;
	private String createdBy;
	private Date lastModifiedDate;
	private String lastModifiedBy;
}
