package com.aitorgc.users.api.rest.error;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@RequiredArgsConstructor
@Builder
public class ErrorView implements Serializable {

	private static final long serialVersionUID = -1393987977088385125L;

	private final String uniqueId;
	private final Instant timestamp;
	private final int status;
	private final List<String> errors;
	private final String type;
	private final String path;
	private final String message;
}
