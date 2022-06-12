package com.aitorgc.users.api.rest.user;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgc.users.api.rest.error.ErrorView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Slf4j
@RestController
public class UserController {

	private static final String GET_USER_MESSAGE = "Retrieving user with id {}";
	private static final String CREATE_USER_MESSAGE = "Creating new user. Data: {}";
	private static final String UPDATE_USER_MESSAGE = "Update user withd id {}. Data: {}";
	private static final String DELETE_USER_MESSAGE = "Deleting user with id {}";
	private static final String FIND_USER_MESSAGE = "Find user. Data: {}";
	private static final String SNYC_MICROSOFT_USERS_MESSAGE = "Sync Microsoft users from an organization. Data: {}";

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "Obtener los datos de un usuario mediante su identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha conseguido recuperar el usuario", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@GetMapping("/{userId}")
	public UserView getUser(@PathVariable("userId") String userId) {
		log.info(GET_USER_MESSAGE, userId);
		return userService.getUser(userId);
	}

	@Operation(summary = "Crea un nuevo usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Se ha conseguido crear el usuario", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserView.class)) }),
			@ApiResponse(responseCode = "400", description = "Se ha introducido algún dato incorrecto en la petición", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "409", description = "El email o el upn del usuario ya están registrados para otro usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
		log.info(CREATE_USER_MESSAGE, request);
		return new CreateUserResponse(userService.createUser(request.getUser()));
	}

	@Operation(summary = "Busca un usuario por su user principal name, microsoft id y/o email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha encontrado el usuario", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha conseguido encontrar el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@PostMapping("/find")
	public FindUserResponse findUser(@Valid @RequestBody FindUserRequest request) {
		log.info(FIND_USER_MESSAGE, request);
		return new FindUserResponse(userService.findUser(request));
	}

	@Operation(summary = "Actualiza un usuario dado su identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha editado el usuario", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserView.class)) }),
			@ApiResponse(responseCode = "404", description = "No se ha conseguido encontrar el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "409", description = "El email o el upn del usuario ya están registrados para otro usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@PutMapping("/{userId}")
	public UpdateUserResponse updateUser(@PathVariable("userId") String userId,
			@Valid @RequestBody UpdateUserRequest request) {
		log.info(UPDATE_USER_MESSAGE, userId, request);
		return new UpdateUserResponse(userService.updateUser(userId, request.getUser()));
	}

	@Operation(summary = "Eliminar un usuario mediante su identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Se ha conseguido eliminar el usuario", content = @Content),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("userId") String userId) {
		log.info(DELETE_USER_MESSAGE, userId);
		userService.deleteUser(userId);
	}

	@Operation(summary = "Esta llamada inicia el proceso de sincronización de usuarios de Microsoft de una organización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la configuración de los módulos de la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado la configuración del módulo de sincronización de usuarios con Microsoft de la organización", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "409", description = "La organización no tiene activado el módulo de sincronización de usuarios con Microsoft", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))),
			@ApiResponse(responseCode = "409", description = "La organización no tiene correctamente configurado el módulo de sincronización de usuarios con Microsoft", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorView.class))) })
	@PostMapping("/syncMicrosoftUsers")
	@ResponseStatus(HttpStatus.CREATED)
	public void syncMicrosoftUsers(@Valid @RequestBody SyncMicrosoftUsersRequest request) {
		log.info(SNYC_MICROSOFT_USERS_MESSAGE, request);
		userService.syncMicrosoftUsers(request);
	}
}
