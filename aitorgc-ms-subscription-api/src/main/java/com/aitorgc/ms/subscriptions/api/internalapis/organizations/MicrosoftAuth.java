package com.aitorgc.ms.subscriptions.api.internalapis.organizations;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class MicrosoftAuth implements Serializable {

	private static final long serialVersionUID = 6778105819894470243L;

	private final String organizationId;
	private final String tenantId;
	private final boolean delegatedPermissions;
	private final String applicationId;
	private final String applicationSecret;
	private final boolean enableEmployeeId;
	private final boolean enableOfficeLocationsFilter;
	private final Date expirationDate;
	private final String createdBy;
	private final Date createdDate;
	private final String lastModifiedBy;
	private final Date lastModifiedDate;

	private final List<OfficeLocation> officeLocations;
}
