package com.aitorgc.organizations.api.util.msgraph;

import lombok.Builder;
import lombok.Value;

/**
 *
 * @author Aitor Gil Callejo
 */
@Value
@Builder
public class Resource {

	private final String id;
	private final ResourceType type;

	public String getPath() {
		switch (type) {
		case EVENT:
			return String.format("/users/%s/events", id);

		case MESSAGE:
			return String.format("/users/%s/messages", id);

		case USERS:
			return "/users";

		default:
			throw new EnumConstantNotPresentException(ResourceType.class, type.name());
		}

	}
}
