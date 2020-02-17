package com.geocode.fullstackproject.restbackend.service.validations.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteDTO;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private ClienteRepository repository;

  @Override
  public void initialize(ClienteUpdate ann) {
  }

  @Override
  public boolean isValid(ClienteDTO clienteDto, ConstraintValidatorContext context) {

    @SuppressWarnings("unchecked")
    Map<String, String> map = (Map<String, String>) request
        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    Long uriId = Long.parseLong(map.get("id"));

    List<FieldMessage> errorMessages = new ArrayList<>();

    Cliente cliente = repository.findByEmail(clienteDto.getEmail());
    if (cliente != null && cliente.getId() != uriId) {
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
