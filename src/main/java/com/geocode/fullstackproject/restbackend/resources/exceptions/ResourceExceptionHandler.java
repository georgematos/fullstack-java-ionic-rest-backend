package com.geocode.fullstackproject.restbackend.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.geocode.fullstackproject.restbackend.service.exceptions.EntidadeNaoEncontradaException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<StandardError> entidadeNaoEncontrada(EntidadeNaoEncontradaException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError stdError = new StandardError(status.value(), e.getMessage(), System.currentTimeMillis());
    return ResponseEntity.status(status).body(stdError);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError stdError = new StandardError(status.value(),
        "Não é possível excluir a categoria pois há produtos vinculados.", System.currentTimeMillis());
    return ResponseEntity.status(status).body(stdError);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<StandardError> emptyResult(EmptyResultDataAccessException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError stdError = new StandardError(status.value(), e.getMessage(), System.currentTimeMillis());
    return ResponseEntity.status(status).body(stdError);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> invalidArgument(MethodArgumentNotValidException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    ValidationError validationError = new ValidationError(status.value(), "Erro de validação",
        System.currentTimeMillis());
    for (FieldError fe : e.getBindingResult().getFieldErrors()) {
      validationError.setError(fe.getField(), fe.getDefaultMessage());
    }
    return ResponseEntity.status(status).body(validationError);
  }

}
