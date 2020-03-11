package com.geocode.fullstackproject.restbackend.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.geocode.fullstackproject.restbackend.domain.Estado;
import com.geocode.fullstackproject.restbackend.domain.dto.EstadoDTO;
import com.geocode.fullstackproject.restbackend.service.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EstadoResource
 */
@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

  @Autowired
  private EstadoService service;

  @GetMapping
  public ResponseEntity<List<EstadoDTO>> getMethodName() {

    List<Estado> estados = service.findAll();
    List<EstadoDTO> estadoDTOs = estados.stream().map(e -> new EstadoDTO(e)).collect(Collectors.toList());

    return ResponseEntity.ok().body(estadoDTOs);
  }

}
