package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.EntidadeNaoEncontradaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClienteService
 */
@Service
public class ClienteService {

  final private ClienteRepository repository;

  @Autowired
  public ClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

  public Cliente findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
        String.format("Objeto não encontrado. Id: %d. Tipo: %s", id, Cliente.class.getName())));
  }

  public List<Cliente> findAll() {
    return repository.findAll();
  }

}
