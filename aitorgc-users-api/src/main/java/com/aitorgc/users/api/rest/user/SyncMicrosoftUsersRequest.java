package com.aitorgc.users.api.rest.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
public class SyncMicrosoftUsersRequest implements Serializable {

	private static final long serialVersionUID = 3229985058254329811L;

	@NotBlank
	@Size(min = 36, max = 36)
	private String organizationId;

}
