package com.aitorgc.ms.subscriptions.api.rest.users;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class MicrosoftUserSubscriptionsController {

	private static final String USER_CHANGE_NOTIFICATION_MESSAGE = "New user change notification request. Info: {}";
	private static final String USER_SUBSCRIPTION_VALIDATION_MESSAGE = "New user subscription validation request {}, validationToken {}";

	private final MicrosoftUserSubscriptionsService microsoftUserSubscriptionsService;

	@PostMapping(path = "/microsoft/users/subscriptions", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> validateSubscription(@RequestParam String validationToken) {
		log.debug(USER_SUBSCRIPTION_VALIDATION_MESSAGE, Instant.now().toString(), validationToken);

		return new ResponseEntity<>(validationToken, HttpStatus.OK);
	}

	@PostMapping(path = "/microsoft/users/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void processSubscription(@RequestBody String notificationInfo) {
		log.debug(USER_CHANGE_NOTIFICATION_MESSAGE, notificationInfo);
		microsoftUserSubscriptionsService.processSubscription(notificationInfo);
	}

}
