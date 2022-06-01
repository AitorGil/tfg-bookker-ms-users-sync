package com.aitorgc.ms.subscriptions.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.aitorgc.ms.subscriptions.api.internalapis.organizations.OrganizationsProperties;
import com.aitorgc.ms.subscriptions.api.internalapis.users.UsersProperties;

@SpringBootApplication
@EnableFeignClients
@Import({ OrganizationsProperties.class, UsersProperties.class })
public class AitorgcMsSubscriptionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AitorgcMsSubscriptionApiApplication.class, args);
	}

}
