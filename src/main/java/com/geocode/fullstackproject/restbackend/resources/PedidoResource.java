package com.geocode.fullstackproject.restbackend.resources;

import com.geocode.fullstackproject.restbackend.domain.Pedido;
import com.geocode.fullstackproject.restbackend.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PedidoResource
 */
@RestController
@RequestMapping(value = "pedidos")
public class PedidoResource {

  private final PedidoService service;

  @Autowired
  public PedidoResource(PedidoService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Pedido> findById(@PathVariable Long id) {
    Pedido pedido = service.findById(id);
    return ResponseEntity.ok().body(pedido);
  }

}
