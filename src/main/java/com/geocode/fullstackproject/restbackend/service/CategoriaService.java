package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.models.Categoria;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CategoriaService
 */
@Service
public class CategoriaService {

  private CategoriaRepository repository;

  @Autowired
  public CategoriaService(CategoriaRepository repository) {
    this.repository = repository;
  }

  public Categoria findById(Long id) {
    return repository.findById(id).get();
  }

  public List<Categoria> findAll() {
    return repository.findAll();
  }

  public Categoria save(Categoria categoria) {
    return repository.save(categoria);
  }

}
