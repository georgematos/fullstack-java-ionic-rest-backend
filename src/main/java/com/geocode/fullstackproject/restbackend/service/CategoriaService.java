package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.domain.dto.CategoriaDTO;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.EntidadeNaoEncontradaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

  public Categoria update(Long id, Categoria catNewData) {
    Categoria categoria = findById(id);
    fillCategoriaToUpdate(categoria, catNewData);
    return repository.save(categoria);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public Page<Categoria> findPage(Integer page, Integer linesPerPages, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
    return repository.findAll(pageRequest);
  }

  /**
   * Métodos auxiliares
   */
  private void fillCategoriaToUpdate(Categoria categoria, Categoria catNewData) {
    categoria.setNome(catNewData.getNome());
  }

  public Categoria fromDTO(CategoriaDTO dto) {
    return new Categoria(dto.getId(), dto.getNome());
  }

}
