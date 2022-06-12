package com.aitorgc.users.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.ToString;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@MappedSuperclass
@ToString
public abstract class BaseEntity extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3513413898706318275L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", nullable = false, length = 36)
	private String id;

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		return getId().equals(other.getId());
	}

	public String getId() {
		return id;
	}

}
