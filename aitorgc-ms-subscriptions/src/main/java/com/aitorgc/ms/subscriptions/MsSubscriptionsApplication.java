package com.aitorgc.ms.subscriptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.aitorgc.ms.subscriptions.internalapis.organizations.OrganizationsProperties;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@Import({ OrganizationsProperties.class })
public class MsSubscriptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSubscriptionsApplication.class, args);
	}

}
