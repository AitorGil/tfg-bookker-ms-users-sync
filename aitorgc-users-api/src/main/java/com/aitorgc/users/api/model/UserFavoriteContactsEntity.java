package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "user_favorite_contacts")
public class UserFavoriteContactsEntity extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@EmbeddedId
	protected UserFavoriteContactsPK userFavoriteContactsPK;

	/**
	 * Constructor de la clase.
	 */
	public UserFavoriteContactsEntity() {
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param userId    El identificador del grupo.
	 * @param contactId El identificador del contacto.
	 */
	public UserFavoriteContactsEntity(String userId, String contactId) {
		this.userFavoriteContactsPK = new UserFavoriteContactsPK(userId, contactId);
	}

	public UserFavoriteContactsPK getUserFavoriteContactsPK() {
		return userFavoriteContactsPK;
	}

	public void setUserFavoriteContactsPK(UserFavoriteContactsPK userFavoriteContactsPK) {
		this.userFavoriteContactsPK = userFavoriteContactsPK;
	}

}
