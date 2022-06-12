package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "c_user_departments")
public class UserDepartmentsEntity extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@EmbeddedId
	protected UserDepartmentsPK userDepartmentsPK;

	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity user;

	@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private DepartmentEntity department;

	protected UserDepartmentsEntity() {
	}

	public UserDepartmentsEntity(UserDepartmentsPK userDepartmentsPK) {
		this.userDepartmentsPK = userDepartmentsPK;
	}

	public UserDepartmentsEntity(String userId, String departmentId) {
		this.userDepartmentsPK = new UserDepartmentsPK(userId, departmentId);
	}

	public UserDepartmentsPK getUserDepartmentsPK() {
		return userDepartmentsPK;
	}

	public UserEntity getUser() {
		return user;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

}
