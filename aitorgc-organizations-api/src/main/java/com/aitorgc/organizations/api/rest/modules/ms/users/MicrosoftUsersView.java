package com.aitorgc.organizations.api.rest.modules.ms.users;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MicrosoftUsersView implements Serializable {

	private static final long serialVersionUID = -295070826501066359L;

	private final String organizationId;
	private final String tenantId;
	private final String applicationId;
	private final String applicationSecret;
	private final Date expirationDate;
	private final String createdBy;
	private final Date createdDate;
	private final String lastModifiedBy;
	private final Date lastModifiedDate;
}
