package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.EntidadeNaoEncontradaException;

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
    return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
        "Objeto não encontrado. ID: " + id + " Tipo: " + Categoria.class.getName()));
  }

  public List<Categoria> findAll() {
    return repository.findAll();
  }

  public Categoria save(Categoria categoria) {
    return repository.save(categoria);
  }

}
