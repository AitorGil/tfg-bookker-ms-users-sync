package com.aitorgc.users.api.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Embeddable
public class UserFavoriteContactsPK implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@Column(nullable = false, length = 36)
	private String userId;

	@Column(nullable = false, length = 36)
	private String contactId;

	/**
	 * Constructor de la clase.
	 */
	public UserFavoriteContactsPK() {
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param userId    El identificador del usuario.
	 * @param contactId El identificador del contacto.
	 */
	public UserFavoriteContactsPK(String userId, String contactId) {
		this.userId = userId;
		this.contactId = contactId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.userId);
		hash = 37 * hash + Objects.hashCode(this.contactId);
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
		final UserFavoriteContactsPK other = (UserFavoriteContactsPK) obj;
		if (!Objects.equals(this.userId, other.userId)) {
			return false;
		}
		if (!Objects.equals(this.contactId, other.contactId)) {
			return false;
		}
		return true;
	}

}
