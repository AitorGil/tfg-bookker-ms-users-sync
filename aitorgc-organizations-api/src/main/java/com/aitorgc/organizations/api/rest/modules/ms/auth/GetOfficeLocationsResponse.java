package com.aitorgc.organizations.api.rest.modules.ms.auth;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@Builder
public class GetOfficeLocationsResponse implements Serializable {

	private static final long serialVersionUID = 9200681748913593785L;

	private final List<OfficeLocationView> officeLocations;
}
