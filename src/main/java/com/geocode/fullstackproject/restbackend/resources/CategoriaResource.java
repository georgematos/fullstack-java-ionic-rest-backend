package com.geocode.fullstackproject.restbackend.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.domain.dto.CategoriaDTO;
import com.geocode.fullstackproject.restbackend.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * CategoriaResource
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  private CategoriaService service;

  @Autowired
  public CategoriaResource(CategoriaService categoriaService) {
    this.service = categoriaService;
  }

  @GetMapping
  public ResponseEntity<List<CategoriaDTO>> findAll() {
    List<CategoriaDTO> dtos = service.findAll().stream().map(c -> new CategoriaDTO(c)).collect(Collectors.toList());
    return ResponseEntity.ok().body(dtos);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Categoria> find(@PathVariable Long id) {
    Categoria categoria = service.findById(id);
    return ResponseEntity.ok().body(categoria);
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody Categoria entity) {
    Categoria categoriaSalva = service.save(entity);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaSalva.getId())
        .toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria entityWithNewData) {
    Categoria categoria = service.update(id, entityWithNewData);
    return ResponseEntity.ok().body(categoria);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/pages")
  public ResponseEntity<Page<CategoriaDTO>> findPage(
    @RequestParam(value="page", defaultValue = "0") Integer page, 
    @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPages,
    @RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
    @RequestParam(value="direction", defaultValue = "ASC") String direction) {
      Page<Categoria> categorias = service.findPage(page, linesPerPages, orderBy, direction);
      Page<CategoriaDTO> PageDTOs = categorias.map(c -> new CategoriaDTO(c));
      return ResponseEntity.ok().body(PageDTOs);
    }

}
