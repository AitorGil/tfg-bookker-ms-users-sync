package com.aitorgc.users.api.internalapis.organizations;

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
public class MicrosoftUsers implements Serializable {

	private static final long serialVersionUID = -295070826501066359L;

	private String organizationId;
	private String tenantId;
	private String applicationId;
	private String applicationSecret;
	private Date expirationDate;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
}
