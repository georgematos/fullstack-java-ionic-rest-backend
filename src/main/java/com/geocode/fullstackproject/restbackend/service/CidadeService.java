package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Cidade;
import com.geocode.fullstackproject.restbackend.repository.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CidadeService
 */
@Service
public class CidadeService {

  @Autowired
  private CidadeRepository repository;

  public List<Cidade> findCidades(Long id) {
    return repository.findCidadesByEstadoId(id);
  }
  
}
