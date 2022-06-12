package com.aitorgc.users.api.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Embeddable
public class UserGroupsPK implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false, name = "user_id", length = 36)
	private String userId;

	@Column(nullable = false, name = "group_id", length = 36)
	private String groupId;

	/**
	 * Constructor de la clase.
	 */
	public UserGroupsPK() {
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param userId  El identificador del usuario.
	 * @param groupId El identificador del grupo.
	 */
	public UserGroupsPK(String userId, String groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + Objects.hashCode(this.userId);
		hash = 53 * hash + Objects.hashCode(this.groupId);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserGroupsPK other = (UserGroupsPK) obj;
		if (!Objects.equals(this.userId, other.userId)) {
			return false;
		}
		if (!Objects.equals(this.groupId, other.groupId)) {
			return false;
		}
		return true;
	}

}
