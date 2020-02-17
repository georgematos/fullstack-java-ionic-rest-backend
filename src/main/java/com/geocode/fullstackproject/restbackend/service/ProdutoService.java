package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.domain.Produto;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;
import com.geocode.fullstackproject.restbackend.repository.ProdutoRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * ProdutoService
 */
@Service
public class ProdutoService {

  private ProdutoRepository repository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  public ProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  public Produto findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar o pedido com o ID: " + id));
  }

  public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPages, String direction,
      String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
    List<Categoria> categorias = categoriaRepository.findAllById(ids);
    // return repository.search(nome, categorias, pageRequest);
    return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
  }

}
