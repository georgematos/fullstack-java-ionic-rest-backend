package com.geocode.fullstackproject.restbackend.resources;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "clientes")
public class ClienteResource {

  private final ClienteService service;

  @Autowired
  public ClienteResource(ClienteService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> findAll() {
    List<Cliente> clientes = service.findAll();
    return ResponseEntity.ok().body(clientes);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    Cliente cliente = service.findById(id);
    return ResponseEntity.ok().body(cliente);
  }

}
