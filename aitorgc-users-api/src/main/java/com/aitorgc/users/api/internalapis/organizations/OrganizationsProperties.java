package com.aitorgc.users.api.internalapis.organizations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Data
@ConfigurationProperties(prefix = "bookker.internal.apis.organizations")
public class OrganizationsProperties {

	private String url;

	private String user;

	private String password;

}
