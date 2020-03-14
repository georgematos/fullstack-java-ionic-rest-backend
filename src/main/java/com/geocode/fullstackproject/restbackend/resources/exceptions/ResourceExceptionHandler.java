package com.geocode.fullstackproject.restbackend.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.glue.model.EntityNotFoundException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.geocode.fullstackproject.restbackend.service.exceptions.AuthorizationException;
import com.geocode.fullstackproject.restbackend.service.exceptions.DataIntegrityException;
import com.geocode.fullstackproject.restbackend.service.exceptions.FileException;
import com.geocode.fullstackproject.restbackend.service.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ResourceExceptionHandler
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	// Overriding the default exception
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Objeto não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Entidade não encontrada", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de validação", e.getMessage(), request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> fileException(FileException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de IO",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {

		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		ValidationError err = new ValidationError(System.currentTimeMillis(), code.value(), "Amazon Service error",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(code).body(err);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Amazon Client error", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Amazon S3 error", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationError(AuthorizationException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Acesso não autorizado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

}
