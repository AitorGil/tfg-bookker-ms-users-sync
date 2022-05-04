package com.aitorgc.ms.subscriptions.internalapis.organizations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aitor Gil Callejo
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ConfigurationProperties(prefix = "bookker.internal.apis.organizations")
public class OrganizationsProperties {

	private String url;

	private String user;

	private String password;

}
