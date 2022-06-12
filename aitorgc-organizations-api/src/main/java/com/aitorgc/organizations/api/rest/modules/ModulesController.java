package com.aitorgc.organizations.api.rest.modules;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Slf4j
@RequiredArgsConstructor
public class ModulesController {

	private final ModulesService modulesService;

	private static final String GET_ORGANIZATION_MODULES_EXTRA = "Retrieving modules extra from organization with id {}";
	private static final String GET_ORGANIZATION_MODULES = "Retrieving modules from organization with id {}";

	@Operation(summary = "Obtener configuración de los módulos extra de una organización mediante el id de organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar la configuración de los módulos extra", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ModulesExtraView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la configuración de los módulos extra", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{organizationId}/modulesextra")
	public ModulesExtraView getOrganizationModulesExtra(@PathVariable String organizationId) {
		log.info(GET_ORGANIZATION_MODULES_EXTRA, organizationId);
		return modulesService.getOrganizationModulesExtra(organizationId);

	}

	@Operation(summary = "Obtener configuración de los módulos de una organización mediante el id de organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar la configuración de los módulos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ModulesView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la configuración de los módulos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{organizationId}/modules")
	public ModulesView getOrganizationModules(@PathVariable String organizationId) {
		log.info(GET_ORGANIZATION_MODULES, organizationId);
		return modulesService.getOrganizationModules(organizationId);

	}

}
