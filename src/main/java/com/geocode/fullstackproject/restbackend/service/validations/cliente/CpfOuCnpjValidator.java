package com.geocode.fullstackproject.restbackend.service.validations.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.resources.exceptions.FieldMessage;
import com.geocode.fullstackproject.restbackend.service.validations.utils.DocumentUtil;

public class CpfOuCnpjValidator implements ConstraintValidator<CpfOuCnpj, Cliente> {
  @Override
  public void initialize(CpfOuCnpj ann) {
  }

  @Override
  public boolean isValid(Cliente objDto, ConstraintValidatorContext context) {
    List<FieldMessage> list = new ArrayList<>();

    if (objDto.getTipo().getCod() == 1) {
      boolean isValid = DocumentUtil.isValidCpf(objDto.getCpfOuCnpj());
      if (!isValid) {
        list.add(new FieldMessage("CpfOUCnpj", "CPF inválido"));
      }
    } else {
      boolean isValid = DocumentUtil.isValidCnpj(objDto.getCpfOuCnpj());
      if (!isValid) {
        list.add(new FieldMessage("CpfOUCnpj", "CNPJ inválido"));
      }
    }

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
          .addConstraintViolation();
    }
    return list.isEmpty();
  }
}
