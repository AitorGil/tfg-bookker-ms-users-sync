package com.aitorgc.organizations.api.rest.modules.ms.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgc.organizations.api.rest.error.ErrorView;

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
@RestController
@RequiredArgsConstructor
@Slf4j
public class MicrosoftAuthController {

	private static final String GET_MICROSOFT_AUTH_CONFIG = "Retrieving Microsoft auth config from organization with id {}. With office locations: {}";
	private static final String GET_OFFICE_LOCATIONS = "Retrieving Microsoft auth config from organization with id {}. With office locations: {}";

	private final MicrosoftAuthService microsoftAuthService;

	@Operation(summary = "Obtener configuración del módulo de autenticación de usuarios con Microsoft Azure mediante el id de organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar la configuración del módulo", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MicrosoftAuthView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la configuración del módulo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{organizationId}/modules/microsoftAuth")
	public MicrosoftAuthView getMicrosoftAuthConfig(@PathVariable String organizationId,
			@RequestParam(name = "withOfficeLocations", defaultValue = "false") boolean withOfficeLocations) {
		log.info(GET_MICROSOFT_AUTH_CONFIG, organizationId, withOfficeLocations);
		return microsoftAuthService.getMicrosoftAuthConfig(organizationId, withOfficeLocations);
	}

	@Operation(summary = "Obtener la lista de offices locations pertenecientes a la configuración del módulo de autenticación de usuarios con Microsoft Azure mediante el id de organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar la lista de office locations", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetOfficeLocationsResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{organizationId}/modules/microsoftAuth/officeLocations")
	public GetOfficeLocationsResponse getOfficeLocations(@PathVariable String organizationId) {
		log.info(GET_OFFICE_LOCATIONS, organizationId);
		return GetOfficeLocationsResponse.builder()
				.officeLocations(microsoftAuthService.getOfficeLocations(organizationId)).build();
	}
}
