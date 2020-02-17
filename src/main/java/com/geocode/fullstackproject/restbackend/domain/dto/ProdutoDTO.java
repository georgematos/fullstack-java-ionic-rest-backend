package com.geocode.fullstackproject.restbackend.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.domain.Produto;

/**
 * Produto.DTO
 */
public class ProdutoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;
  private Double preco;

  private List<Categoria> categorias = new ArrayList<>();

  public ProdutoDTO() {
  }

  public ProdutoDTO(Produto produto) {
    id = produto.getId();
    nome = produto.getNome();
    preco = produto.getPreco();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

}
