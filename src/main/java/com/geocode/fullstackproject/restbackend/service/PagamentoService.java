package com.geocode.fullstackproject.restbackend.service;

import com.geocode.fullstackproject.restbackend.domain.Pagamento;
import com.geocode.fullstackproject.restbackend.repository.PagamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PagamentoService
 */
@Service
public class PagamentoService {

  @Autowired
  private PagamentoRepository repository;

  public Pagamento insert(Pagamento pagamento) {
    return repository.save(pagamento);
  }
  
}
