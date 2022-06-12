package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDepartmentsPK implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@Basic(optional = false)
	@NotNull
	@NonNull
	@Column(nullable = false, name = "user_id", length = 36)
	private String userId;

	@NotNull
	@NonNull
	@Column(nullable = false, name = "department_id", length = 36)
	private String departmentId;
}
