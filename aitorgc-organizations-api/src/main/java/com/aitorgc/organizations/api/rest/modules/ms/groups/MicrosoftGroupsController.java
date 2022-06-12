package com.aitorgc.organizations.api.rest.modules.ms.groups;

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
@RequiredArgsConstructor
@Slf4j
public class MicrosoftGroupsController {

    private static final String GET_MICROSOFT_GROUPS_CONFIG = "Retrieving Microsoft groups config from organization with id {}";

    private final MicrosoftGroupsService microsoftGroupsService;

    @Operation(summary = "Obtener configuración del módulo de sincronización de grupos de usuarios con Microsoft Azure mediante el id de organización")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar la configuración del módulo", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = MicrosoftGroupsView.class)) }),
	    @ApiResponse(responseCode = "404", description = "No se ha encontrado la configuración del módulo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
	    @ApiResponse(responseCode = "404", description = "No se ha encontrado la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
    @GetMapping("/{organizationId}/modules/microsoftGroups")
    public MicrosoftGroupsView getMicrosoftAuthConfig(@PathVariable String organizationId) {
	log.info(GET_MICROSOFT_GROUPS_CONFIG, organizationId);
	return microsoftGroupsService.getMicrosoftGroupsConfig(organizationId);
    }

}
