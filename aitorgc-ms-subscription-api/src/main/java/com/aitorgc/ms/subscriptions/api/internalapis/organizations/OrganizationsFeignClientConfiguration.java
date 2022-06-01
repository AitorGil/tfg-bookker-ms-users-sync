package com.aitorgc.ms.subscriptions.api.internalapis.organizations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

/**
 *
 * @author Aitor Gil Callejo
 */
public class OrganizationsFeignClientConfiguration {

	private final OrganizationsProperties properties;

	public OrganizationsFeignClientConfiguration(OrganizationsProperties properties) {
		this.properties = properties;
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(properties.getUser(), properties.getPassword());
	}

	@Bean
	public RequestInterceptor addHeaderOnBehalfOf(@Value("${spring.application.name}") String appName) {
		return requestTtemplate -> requestTtemplate.header("x-on-behalf-of", appName);
	}
}
