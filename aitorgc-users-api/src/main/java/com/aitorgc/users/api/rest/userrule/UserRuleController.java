package com.aitorgc.users.api.rest.userrule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgc.users.api.rest.error.ErrorView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRuleController {

	private static final String GET_ORGANIZATION_DEFAULT_USER_RULE_MESSAGE = "Retrieving organization with id {} default user rule";

	private final UserRuleService userRuleService;

	@Operation(summary = "Obtener los datos de la regla de usuario por defecto de una organización mediante el identificador de la organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar la regla de usuario por defecto de la organización", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserRuleView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la regla de usuario por defecto de la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/rules/organizations/{organizationId}/default")
	public UserRuleView getOrganizationDefaultUserRule(@PathVariable("organizationId") String organizationId) {
		log.info(GET_ORGANIZATION_DEFAULT_USER_RULE_MESSAGE, organizationId);
		return userRuleService.getDefaultUserRuleByOrganization(organizationId);
	}
}
