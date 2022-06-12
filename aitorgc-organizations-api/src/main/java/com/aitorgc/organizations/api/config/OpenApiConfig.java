package com.aitorgc.organizations.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appName,
			@Value("${info.app.version}") String appVersion) {
		return new OpenAPI()
				.info(new Info().title(appName + " v" + appVersion).version(appVersion).description(appVersion));

	}
}
