package com.geocode.fullstackproject.restbackend.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.geocode.fullstackproject.restbackend.domain.Estado;

import org.hibernate.validator.constraints.Length;

/**
 * EstadoDTO
 */
public class EstadoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @NotEmpty(message = "Preenchimento Obrigatório")
  @Length(min = 5, max = 80, message = "Tamnho deve ser entre 5 e 80 cacacteres")
  private String nome;

  public EstadoDTO() {
  }

  public EstadoDTO(Estado estado) {
    id = estado.getId();
    nome = estado.getNome();
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
