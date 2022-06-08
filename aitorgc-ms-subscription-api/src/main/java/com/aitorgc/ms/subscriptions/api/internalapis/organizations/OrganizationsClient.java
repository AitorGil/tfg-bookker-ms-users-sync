package com.aitorgc.ms.subscriptions.api.internalapis.organizations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aitor Gil Callejo
 */
@FeignClient(name = "organizationsClient", url = "${bookker.internal.apis.organizations.url}", configuration = OrganizationsFeignClientConfiguration.class)
public interface OrganizationsClient {

	@GetMapping("/modules/microsoftUsers/subscriptions")
	public MicrosoftUserSubscription getSubscription(
			@RequestParam(name = "clientState", required = true) String clientState);

	@GetMapping("/{organizationId}/modules/microsoftUsers")
	public MicrosoftUsers getMicrosoftUsersConfig(@PathVariable String organizationId);

	@GetMapping("/{organizationId}/modules/microsoftUsers/subscriptions")
	public GetMicrosoftUsersSubscriptionsResponse getSubscriptions(@PathVariable String organizationId,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "top", defaultValue = "50", required = false) int top);

	@PostMapping("/{organizationId}/modules/microsoftUsers/subscriptions")
	public MicrosoftUserSubscription createSubscription(@PathVariable String organizationId,
			@RequestBody CreateMicrosoftUsersSubscriptionRequest request);

	@PutMapping("/{organizationId}/modules/microsoftUsers/subscriptions/{subscriptionId}")
	public MicrosoftUserSubscription updateSubscription(@PathVariable String organizationId,
			@PathVariable String subscriptionId, @RequestBody UpdateSubscriptionRequest request);

	@DeleteMapping("/{organizationId}/modules/microsoftUsers/subscriptions/{subscriptionId}")
	public void deleteSubscription(@PathVariable String organizationId, @PathVariable String subscriptionId);

	@GetMapping
	public GetOrganizationsResponse getOrganizations(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "top", defaultValue = "50", required = false) int top);

	@GetMapping("/{organizationId}/modules")
	public Modules getOrganizationModules(@PathVariable String organizationId);

	@GetMapping("/{organizationId}/modules/microsoftAuth")
	public MicrosoftAuth getMicrosoftAuthConfig(@PathVariable String organizationId,
			@RequestParam(name = "withOfficeLocations", defaultValue = "false") boolean withOfficeLocations);

	@GetMapping("/{organizationId}/modules/microsoftGroups")
	public MicrosoftGroups getMicrosoftGroupsConfig(@PathVariable String organizationId);
}
