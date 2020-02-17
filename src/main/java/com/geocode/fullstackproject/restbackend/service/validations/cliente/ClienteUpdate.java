package com.geocode.fullstackproject.restbackend.service.validations.cliente;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ClienteUpdateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteUpdate {
  public String message() default "Erro de validação";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};
}
