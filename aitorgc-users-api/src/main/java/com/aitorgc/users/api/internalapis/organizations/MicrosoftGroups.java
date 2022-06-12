package com.aitorgc.users.api.internalapis.organizations;

import java.io.Serializable;

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
public class MicrosoftGroups implements Serializable {

    private static final long serialVersionUID = 4819947869529985996L;

    private final String organizationId;
    private final String tenantId;
    private final Boolean delegatedPermissions;
    private final String applicationId;
    private final String applicationSecret;

}
