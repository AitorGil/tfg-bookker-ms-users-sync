package com.aitorgc.organizations.api.rest.modules.ms.groups;

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
public class MicrosoftGroupsView implements Serializable {

    private static final long serialVersionUID = 4819947869529985996L;

    private String organizationId;
    private String tenantId;
    private boolean delegatedPermissions;
    private String applicationId;
    private String applicationSecret;

}
