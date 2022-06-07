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

    private String organizationId;
    private String tenantId;
    private boolean delegatedPermissions;
    private String applicationId;
    private String applicationSecret;
    private boolean enableEmployeeId;
    private boolean enableOfficeLocationsFilter;
    private Date expirationDate;
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;

    private List<OfficeLocation> officeLocations;
}
