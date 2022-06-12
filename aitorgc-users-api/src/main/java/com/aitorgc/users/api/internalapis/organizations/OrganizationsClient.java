package com.aitorgc.users.api.internalapis.organizations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aitor Gil Callejo
 */
@FeignClient(name = "organizationsClient", url = "${bookker.internal.apis.organizations.url}", configuration = OrganizationsFeignClientConfiguration.class)
public interface OrganizationsClient {

	@GetMapping(value = "/{organizationId}")
	Organization getOrganization(@PathVariable String organizationId,
			@RequestHeader(name = "x-on-behalf-of", required = false) String onBehalfOfHeaderValue);

	@GetMapping(value = "/{organizationId}/modules")
	Modules getOrganizationModules(@PathVariable String organizationId,
			@RequestHeader(name = "x-on-behalf-of", required = false) String onBehalfOfHeaderValue);

	@GetMapping("/{organizationId}/modules/microsoftUsers")
	public MicrosoftUsers getMicrosoftUsersConfig(@PathVariable String organizationId);

	@GetMapping("/{organizationId}/modules/microsoftAuth")
	public MicrosoftAuth getMicrosoftAuthConfig(@PathVariable String organizationId,
			@RequestParam(name = "withOfficeLocations", defaultValue = "false") boolean withOfficeLocations);

	@GetMapping("/{organizationId}/modules/microsoftGroups")
	public MicrosoftGroups getMicrosoftGroupsConfig(@PathVariable String organizationId);

}