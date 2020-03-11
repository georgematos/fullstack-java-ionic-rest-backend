package com.geocode.fullstackproject.restbackend.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.geocode.fullstackproject.restbackend.domain.Cidade;
import com.geocode.fullstackproject.restbackend.domain.dto.CidadeDTO;
import com.geocode.fullstackproject.restbackend.service.CidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CidadeResource
 */
@RestController
@RequestMapping(value = "/estados/{estado_id}/cidades")
public class CidadeResource {

  @Autowired
  private CidadeService service;

  @GetMapping
  public ResponseEntity<List<CidadeDTO>> getMethodName(@PathVariable Long estado_id) {

    List<Cidade> cidades = service.findCidades(estado_id);
    List<CidadeDTO> cidadeDTOs = cidades.stream().map(e -> new CidadeDTO(e)).collect(Collectors.toList());

    return ResponseEntity.ok().body(cidadeDTOs);
  }

}
