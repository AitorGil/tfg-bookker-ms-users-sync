package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aitorgc.users.api.model.groups.GroupEntity;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "c_user_groups")
public class UserGroupsEntity extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@EmbeddedId
	protected UserGroupsPK userGroupsPK;

	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity user;

	@JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private GroupEntity group;

	/**
	 * Constructor de la clase.
	 */
	public UserGroupsEntity() {
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param groupId El identificador del grupo.
	 * @param userId  El identificador del usuario.
	 */
	public UserGroupsEntity(String groupId, String userId) {
		this.userGroupsPK = new UserGroupsPK(userId, groupId);
	}

	public UserGroupsPK getUserGroupsPK() {
		return userGroupsPK;
	}

	public void setUserGroupsPK(UserGroupsPK userGroupsPK) {
		this.userGroupsPK = userGroupsPK;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
