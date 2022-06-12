package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Embeddable
@EqualsAndHashCode
public class UserId implements Serializable {

	private static final long serialVersionUID = -291582551354416824L;

	@NonNull
	private String id;

	protected UserId() {
	}

	public UserId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
