package com.aitorgc.users.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author Aitor Gil Callejo
 * 
 */
@Entity
@Table(name = "c_department")
public class DepartmentEntity extends BaseEntity {

	private static final long serialVersionUID = 3513413898706318275L;

	@Column(nullable = false, length = 255)
	@NotEmpty(message = "* Name can't be empty")
	private String name;

	@Column(nullable = false, length = 36)
	private String organizationId;

	/**
	 * Constructor de la clase.
	 */
	public DepartmentEntity() {
	}

	/**
	 * Constructor de la clase.
	 * 
	 * @param name           El name.
	 * @param organizationId El identificador de la organización.
	 */
	public DepartmentEntity(String name, String organizationId) {
		this.name = name;
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, organizationId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentEntity other = (DepartmentEntity) obj;
		return Objects.equals(name, other.name) && Objects.equals(organizationId, other.organizationId);
	}

}
