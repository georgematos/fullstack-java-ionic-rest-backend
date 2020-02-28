package com.geocode.fullstackproject.restbackend.resources;

import java.net.URI;

import com.geocode.fullstackproject.restbackend.domain.Pedido;
import com.geocode.fullstackproject.restbackend.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody Pedido entity) {
    Pedido pedido = service.insert(entity);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPages,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "instant") String orderBy) {
    Page<Pedido> pedidos = service.findPage(page, linesPerPages, direction, orderBy);
    return ResponseEntity.ok().body(pedidos);
  }

}
