package com.aitorgc.users.api.model.groups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.aitorgc.users.api.model.BaseEntity;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "c_group")
public class GroupEntity extends BaseEntity {

	private static final long serialVersionUID = 3513413898706318275L;

	@Column(nullable = false, length = 255)
	@NotEmpty(message = "* Name can't be empty")
	private String name;

	@Column(length = 255)
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private GroupTypes type;

	@NotNull
	@Column(name = "building_id", nullable = false, length = 36)
	private String buildingId;

	@Column(name = "microsoft_id", length = 100)
	private String microsoftId;

	protected GroupEntity() {
	}

	public GroupEntity(String name, GroupTypes type, String buildingId) {
		this.name = name;
		this.type = type;
		this.buildingId = buildingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GroupTypes getType() {
		return type;
	}

	public void setType(GroupTypes type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getMicrosoftId() {
		return microsoftId;
	}

	public void setMicrosoftId(String microsoftId) {
		this.microsoftId = microsoftId;
	}

}
