package com.aitorgc.ms.subscriptions.api.internalapis.users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

/**
 *
 * @author Aitor Gil Callejo
 */
public class UsersFeignClientConfiguration {

	private final UsersProperties properties;

	public UsersFeignClientConfiguration(UsersProperties properties) {
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
