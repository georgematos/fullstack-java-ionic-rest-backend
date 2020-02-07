package com.geocode.fullstackproject.restbackend.domain.dto;

import java.io.Serializable;

import com.geocode.fullstackproject.restbackend.domain.Categoria;

/**
 * CategoriaDTO
 */
public class CategoriaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;

  public CategoriaDTO() {
  }

  public CategoriaDTO(Categoria categoria) {
    id = categoria.getId();
    nome = categoria.getNome();
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

}
