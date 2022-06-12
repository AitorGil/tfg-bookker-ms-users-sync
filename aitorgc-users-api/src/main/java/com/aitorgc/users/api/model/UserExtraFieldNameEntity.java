package com.aitorgc.users.api.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.NonNull;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "c_user_extra_field_name", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "column_name", "organization_id" }) })
public class UserExtraFieldNameEntity extends BaseEntity {

	private static final long serialVersionUID = -4892624666492205947L;

	@NonNull
	@AttributeOverride(name = "id", column = @Column(name = "organization_id"))
	private OrganizationId organizationId;

	@NonNull
	@Size(max = 255)
	@Column(length = 255)
	private String fieldName;

	@NonNull
	@Size(max = 255)
	@Column(length = 255, name = "column_name")
	private String columnName;

	@NonNull
	@Max(50)
	private Integer fieldNumber;

	@NonNull
	private Boolean active;

	protected UserExtraFieldNameEntity() {
	}

	public UserExtraFieldNameEntity(OrganizationId organizationId, String fieldName, Integer fieldNumber,
			String columnName, Boolean active) {
		this.organizationId = organizationId;
		this.fieldName = fieldName;
		this.fieldNumber = fieldNumber;
		this.columnName = columnName;
		this.active = active;
	}

	public OrganizationId getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(OrganizationId organizationId) {
		this.organizationId = organizationId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getFieldNumber() {
		return fieldNumber;
	}

}
