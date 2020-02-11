package com.geocode.fullstackproject.restbackend.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<FieldMessage> errors = new ArrayList<>();

  public ValidationError() {
  }

  public ValidationError(Integer status, String msg, Long timestamp) {
    super(status, msg, timestamp);
  }

  public List<FieldMessage> getErrors() {
    return errors;
  }

  public void setError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }
}
