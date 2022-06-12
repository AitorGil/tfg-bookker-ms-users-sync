package com.aitorgc.organizations.api.rest.error;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microsoft.graph.http.GraphServiceException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String ACCESS_DENIED = "Access denied!";
	public static final String INVALID_REQUEST = "Invalid request";
	public static final String ERROR_MESSAGE_TEMPLATE = "message: %s %n requested uri: %s";
	public static final String LIST_JOIN_DELIMITER = ",";
	public static final String FIELD_ERROR_SEPARATOR = ": ";
	private static final String ERRORS_FOR_PATH = "errors {} for path {}";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> validationErrors = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
				.collect(Collectors.toList());
		return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return getExceptionResponseEntity(exception, status, request,
				Collections.singletonList(exception.getLocalizedMessage()));
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception,
			WebRequest request) {

		final List<String> validationErrors = exception.getConstraintViolations().stream()
				.map(violation -> violation.getPropertyPath() + FIELD_ERROR_SEPARATOR + violation.getMessage())
				.collect(Collectors.toList());
		return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
	}

	@ExceptionHandler({ GraphServiceException.class })
	public ResponseEntity<Object> handleGraphServiceExceptions(GraphServiceException exception, WebRequest request) {
		final HttpStatus status = HttpStatus.valueOf(exception.getResponseCode());
		final String localizedMessage = exception.getLocalizedMessage();
		final String path = request.getDescription(false);
		String message = (StringUtils.isNotEmpty(localizedMessage) ? localizedMessage : status.getReasonPhrase());
		logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
	}

	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<Object> handleAllExceptions(ResponseStatusException exception, WebRequest request) {
		final HttpStatus status = exception.getStatus();
		final String localizedMessage = exception.getLocalizedMessage();
		final String path = request.getDescription(false);
		String message = (StringUtils.isNotEmpty(localizedMessage) ? localizedMessage : status.getReasonPhrase());
		logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
	}

	/**
	 * A general handler for all uncaught exceptions
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
		final HttpStatus status = responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
		final String localizedMessage = exception.getLocalizedMessage();
		final String path = request.getDescription(false);
		String message = (StringUtils.isNotEmpty(localizedMessage) ? localizedMessage : status.getReasonPhrase());
		logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
	}

	/**
	 * Build a detailed information about the exception in the response
	 */
	private ResponseEntity<Object> getExceptionResponseEntity(final Exception exception, final HttpStatus status,
			final WebRequest request, final List<String> errors) {
		final String path = request.getDescription(false);

		final ErrorView error = ErrorView.builder().uniqueId(UUID.randomUUID().toString()).timestamp(Instant.now())
				.status(status.value()).errors(errors).type(exception.getClass().getSimpleName()).path(path)
				.message(getMessageForStatus(status)).build();

		final String errorsMessage = !CollectionUtils.isEmpty(errors)
				? errors.stream().filter(StringUtils::isNotEmpty).collect(Collectors.joining(LIST_JOIN_DELIMITER))
				: status.getReasonPhrase();
		log.error(ERRORS_FOR_PATH, errorsMessage, path);
		return new ResponseEntity<>(error, status);
	}

	private String getMessageForStatus(HttpStatus status) {
		switch (status) {
		case UNAUTHORIZED:
			return ACCESS_DENIED;
		case BAD_REQUEST:
			return INVALID_REQUEST;
		default:
			return status.getReasonPhrase();
		}
	}
}
