package com.aitorgc.organizations.api.rest.modules.ms.auth;

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
public class OfficeLocationView implements Serializable {

	private static final long serialVersionUID = 5010254692702314114L;

	private final String id;
	private final String tenantId;
	private final String officeLocation;
	private final String organizationId;

}
