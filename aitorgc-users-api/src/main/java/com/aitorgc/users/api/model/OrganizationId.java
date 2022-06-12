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
public class OrganizationId implements Serializable {

	private static final long serialVersionUID = 4118378808355502663L;

	@NonNull
	String id;

	OrganizationId() {
	}

	public OrganizationId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
