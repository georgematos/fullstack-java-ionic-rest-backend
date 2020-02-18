package com.geocode.fullstackproject.restbackend.resources;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Produto;
import com.geocode.fullstackproject.restbackend.domain.dto.ProdutoDTO;
import com.geocode.fullstackproject.restbackend.resources.utils.URLHandler;
import com.geocode.fullstackproject.restbackend.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProdutoResource
 */
@RestController
@RequestMapping(value = "produtos")
public class ProdutoResource {

  private final ProdutoService service;

  @Autowired
  public ProdutoResource(ProdutoService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Produto> findById(@PathVariable Long id) {
    Produto produto = service.findById(id);
    return ResponseEntity.ok().body(produto);
  }

  @GetMapping
  public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "nome", defaultValue = "") String nome,
      @RequestParam(value = "categorias", defaultValue = "") String categorias,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPages,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

    String nomeDecoded = URLHandler.convertUTF8Param(nome);
    List<Long> categoriasIds = URLHandler.parseLongStringParams(categorias);

    Page<Produto> produtos = service.search(nomeDecoded, categoriasIds, page, linesPerPages, direction, orderBy);
    Page<ProdutoDTO> PageDTOs = produtos.map(c -> new ProdutoDTO(c));
    return ResponseEntity.ok().body(PageDTOs);
  }

}
