package com.geocode.fullstackproject.restbackend.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.geocode.fullstackproject.restbackend.service.exceptions.EntidadeNaoEncontradaException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ResourceExceptionHandler
 */
@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<StandardError> EntidadeNaoEncontrada(EntidadeNaoEncontradaException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError stdError = new StandardError(status.value(), e.getMessage(), System.currentTimeMillis());
    return ResponseEntity.status(status).body(stdError);
  } 
  
}
