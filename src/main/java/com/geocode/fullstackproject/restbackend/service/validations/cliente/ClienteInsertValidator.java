package com.geocode.fullstackproject.restbackend.service.validations.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteNewDTO;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.resources.exceptions.FieldMessage;
import com.geocode.fullstackproject.restbackend.service.validations.utils.DocumentUtil;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

  @Autowired
  private ClienteRepository repository;

  @Override
  public void initialize(ClienteInsert ann) {
  }

  @Override
  public boolean isValid(ClienteNewDTO clienteDto, ConstraintValidatorContext context) {
    List<FieldMessage> errorMessages = new ArrayList<>();

    if (clienteDto.getTipo() == 1) {
      if (!DocumentUtil.isValidCpf(clienteDto.getCpfOuCnpj())) {
        errorMessages.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
      }
    } else {
      if (!DocumentUtil.isValidCnpj(clienteDto.getCpfOuCnpj())) {
        errorMessages.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
      }
    }

    Cliente cliente = repository.findByEmail(clienteDto.getEmail());
    if (cliente != null) {
      errorMessages.add(new FieldMessage("email", "Email já existente"));
    }

    for (FieldMessage e : errorMessages) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
          .addConstraintViolation();
    }
    return errorMessages.isEmpty();
  }
}
