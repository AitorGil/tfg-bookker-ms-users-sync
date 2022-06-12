package com.aitorgc.users.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import com.aitorgc.users.api.internalapis.organizations.OrganizationsProperties;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableAsync
@EnableJpaAuditing
@Import({ OrganizationsProperties.class })
@RefreshScope
public class UsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApiApplication.class, args);
	}

}
