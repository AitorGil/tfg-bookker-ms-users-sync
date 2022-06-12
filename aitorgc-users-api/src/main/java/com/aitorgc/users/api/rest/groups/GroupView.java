package com.aitorgc.users.api.rest.groups;

import java.io.Serializable;
import java.util.Date;

import com.aitorgc.users.api.model.groups.GroupTypes;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupView implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String id;
	private final String name;
	private final String description;
	private final GroupTypes type;
	private final String buildingId;
	private final String microsoftId;

	private final String createdBy;
	private final Date createdDate;
	private final String lastModifiedBy;
	private final Date lastModifiedDate;
}
