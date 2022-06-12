package com.aitorgc.users.api.util.msgraph;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Aitor Gil Callejo
 */
@Data
@RequiredArgsConstructor
public class ApplicationCredentials {

	private final String clientId;
	private final String secretId;
	private final String tenantId;
}
