package com.geocode.fullstackproject.restbackend.service;

import com.geocode.fullstackproject.restbackend.domain.Pedido;
import com.geocode.fullstackproject.restbackend.repository.PedidoRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PedidoService
 */
@Service
public class PedidoService {

  private PedidoRepository repository;

  @Autowired
  public PedidoService(PedidoRepository repository) {
    this.repository = repository;
  }

  public Pedido findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar o pedido com o ID: " + id));
  }

}
