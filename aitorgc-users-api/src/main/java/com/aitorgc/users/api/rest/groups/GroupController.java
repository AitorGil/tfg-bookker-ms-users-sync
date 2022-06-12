package com.aitorgc.users.api.rest.groups;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
@RestController
@Slf4j
@RequiredArgsConstructor
public class GroupController {

	private static final String GET_USER_GROUPS_MESSAGE = "Retrieving groups from user with id {}";
	private static final String GET_ORGANIZATION_GROUPS_MESSAGE = "Retrieving groups from organization with id {}. Only synchronized groups {}";
	private static final String GET_DEFAULT_ORGANIZATION_GROUPS_MESSAGE = "Retrieving default groups from organization with id {}";

	private final GroupService groupService;

	@Operation(summary = "Obtener los datos de los grupos de un usuario mediante su identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se han conseguido recuperar los grupos del usuario", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetUserGroupsResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{userId}/groups")
	public GetUserGroupsResponse getUserGroups(@PathVariable("userId") String userId) {
		log.info(GET_USER_GROUPS_MESSAGE, userId);
		return GetUserGroupsResponse.builder().groups(groupService.getUserGroups(userId)).build();
	}

	@Operation(summary = "Obtener los datos de los grupos de una organización mediante su identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se han conseguido recuperar los grupos de la organización", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetUserGroupsResponse.class)) }) })
	@GetMapping("/groups/organizations/{organizationId}")
	public GetOrganizationGroupsResponse getOrganizationGroups(@PathVariable("organizationId") String organizationId,
			@RequestParam(name = "onlySynchronizedGroups", required = false, defaultValue = "false") boolean onlySynchronizedGroups) {
		log.info(GET_ORGANIZATION_GROUPS_MESSAGE, organizationId, onlySynchronizedGroups);
		return GetOrganizationGroupsResponse.builder()
				.groups(groupService.getOrganizationGroups(organizationId, onlySynchronizedGroups)).build();
	}

	@Operation(summary = "Obtener los datos de los grupos por defecto de una organización mediante su identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se han conseguido recuperar los grupos por defecto de la organización", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetDefaultOrganizationGroupsResponse.class)) }) })
	@GetMapping("/groups/organizations/{organizationId}/default")
	public GetDefaultOrganizationGroupsResponse getDefaultOrganizationGroups(
			@PathVariable("organizationId") String organizationId) {
		log.info(GET_DEFAULT_ORGANIZATION_GROUPS_MESSAGE, organizationId);
		return GetDefaultOrganizationGroupsResponse.builder()
				.groups((groupService.getDefaultOrganizationGroups(organizationId))).build();
	}
}
