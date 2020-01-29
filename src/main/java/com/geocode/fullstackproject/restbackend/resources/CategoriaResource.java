package com.geocode.fullstackproject.restbackend.resources;

import java.util.List;

import com.geocode.fullstackproject.restbackend.models.Categoria;
import com.geocode.fullstackproject.restbackend.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoriaResource
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  private CategoriaService categoriaService;

  @Autowired
  public CategoriaResource(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @GetMapping
  public ResponseEntity<List<Categoria>> findAll() {
    List<Categoria> categorias = categoriaService.findAll();
    return ResponseEntity.ok().body(categorias);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Categoria> find(@PathVariable Long id) {
    Categoria categoria = categoriaService.findById(id);
    return ResponseEntity.ok().body(categoria);
  }

}
