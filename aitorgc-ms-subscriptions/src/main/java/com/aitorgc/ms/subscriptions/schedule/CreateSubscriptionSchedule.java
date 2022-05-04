package com.aitorgc.ms.subscriptions.schedule;

import java.time.Instant;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aitorgc.ms.subscriptions.service.SubscriptionsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateSubscriptionSchedule {

	private final SubscriptionsService subscriptionsService;

	@Scheduled(initialDelay = 10000, fixedDelay = 60000)
	private void createSubscriptions() {
		log.info("Starting subscription creation service" + Instant.now().toString());
		subscriptionsService.manageUsersSubscriptionsForOrganizations();
	}
}
