package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Estado;
import com.geocode.fullstackproject.restbackend.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EstadoService
 */
@Service
public class EstadoService {

  @Autowired
  private EstadoRepository repository;

  public List<Estado> findAll() {
    return repository.findAllByOrderByNome();
  }
  
}
