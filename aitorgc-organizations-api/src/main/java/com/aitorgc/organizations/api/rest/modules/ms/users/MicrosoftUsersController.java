package com.aitorgc.organizations.api.rest.modules.ms.users;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
public class MicrosoftUsersController {

	private static final String GET_MICROSOFT_USERS_CONFIG = "Retrieving Microsoft users config from organization with id {}";
	private static final String GET_MICROSOFT_USERS_SUBSCRIPTIONS = "Retrieving Microsoft users subscriptions from organization with id {}";
	private static final String GET_MICROSOFT_USERS_SUBSCRIPTIONS_BY_CLIENT_STATE = "Retrieving Microsoft users subscription with client state {}";
	private static final String DELETE_MICROSOFT_USERS_SUBSCRIPTION = "Deleting Microsoft users subscription with id {} from organization with id {}";
	private static final String UPDATE_MICROSOFT_USERS_SUBSCRIPTION = "Updating Microsoft users subscription with id {} from organization with id {}. Data: {}";
	private static final String CREATE_MICROSOFT_USERS_SUBSCRIPTION = "Creating Microsoft users subscription from organization with id {}. Data: {}";

	private final MicrosoftUsersService modulesService;

	@Operation(summary = "Obtener configuración del módulo de sincronización de usuarios con Microsoft mediante el id de organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar la configuración del módulo", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MicrosoftUsersView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la configuración del módulo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{organizationId}/modules/microsoftUsers")
	public MicrosoftUsersView getMicrosoftUsersConfig(@PathVariable String organizationId) {
		log.info(GET_MICROSOFT_USERS_CONFIG, organizationId);
		return modulesService.getMicrosoftUsersConfig(organizationId);
	}

	@Operation(summary = "Obtiene una lista de suscripciones del módulo de sincronización de usuarios con Microsoft de una organización mediante el id de organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se han conseguido recuperar las suscripciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetMicrosoftUsersSubscriptionsResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{organizationId}/modules/microsoftUsers/subscriptions")
	public GetMicrosoftUsersSubscriptionsResponse getSubscriptions(@PathVariable String organizationId,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "top", defaultValue = "50", required = false) int top) {
		log.info(GET_MICROSOFT_USERS_SUBSCRIPTIONS, organizationId);
		return GetMicrosoftUsersSubscriptionsResponse.builder()
				.subscriptions(modulesService.getSubscriptions(organizationId, page, top)).build();
	}

	@Operation(summary = "Crea una suscripción del módulo de sincronización de usuarios con Microsoft de una organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Se ha conseguido actualizar la suscripción", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMicrosoftUsersSubscriptionRequest.class))),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la suscripción", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@PostMapping("/{organizationId}/modules/microsoftUsers/subscriptions")
	@ResponseStatus(HttpStatus.CREATED)
	public MicrosoftUserSubscriptionView createSubscription(@PathVariable String organizationId,
			@Valid @RequestBody CreateMicrosoftUsersSubscriptionRequest request) {
		log.info(CREATE_MICROSOFT_USERS_SUBSCRIPTION, organizationId, request);
		return modulesService.createSubscription(organizationId, request);
	}

	@Operation(summary = "Actualiza una suscripción del módulo de sincronización de usuarios con Microsoft de una organización mediante el id de organización y el identificador de la suscripción")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido actualizar la suscripción", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateSubscriptionRequest.class))),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la suscripción", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@PutMapping("/{organizationId}/modules/microsoftUsers/subscriptions/{subscriptionId}")
	public MicrosoftUserSubscriptionView updateSubscription(@PathVariable String organizationId,
			@PathVariable String subscriptionId, @Valid @RequestBody UpdateSubscriptionRequest request) {
		log.info(UPDATE_MICROSOFT_USERS_SUBSCRIPTION, subscriptionId, organizationId, request);
		return modulesService.updateSubscription(organizationId, subscriptionId, request);
	}

	@Operation(summary = "Elimina una suscripción del módulo de sincronización de usuarios con Microsoft de una organización mediante el id de organización y el identificador de la suscripción")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Se ha conseguido eliminar la suscripción", content = @Content),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la suscripción", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@DeleteMapping("/{organizationId}/modules/microsoftUsers/subscriptions/{subscriptionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSubscription(@PathVariable String organizationId, @PathVariable String subscriptionId) {
		log.info(DELETE_MICROSOFT_USERS_SUBSCRIPTION, subscriptionId, organizationId);
		modulesService.deleteSubscription(organizationId, subscriptionId);
	}

	@Operation(summary = "Obtiene un suscripción del módulo de sincronización de usuarios con Microsoft mediante su client state")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se han conseguido recupera la suscripción", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MicrosoftUserSubscriptionView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la suscripción", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/modules/microsoftUsers/subscriptions")
	public MicrosoftUserSubscriptionView getSubscription(
			@RequestParam(name = "clientState", required = true) String clientState) {
		log.info(GET_MICROSOFT_USERS_SUBSCRIPTIONS_BY_CLIENT_STATE, clientState);
		return modulesService.getSubscriptionByClientState(clientState);
	}

}
