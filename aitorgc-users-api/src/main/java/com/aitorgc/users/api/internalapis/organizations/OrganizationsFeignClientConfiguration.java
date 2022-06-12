package com.aitorgc.users.api.internalapis.organizations;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

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

}
