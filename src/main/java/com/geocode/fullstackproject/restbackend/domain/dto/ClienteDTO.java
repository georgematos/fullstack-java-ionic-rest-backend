package com.geocode.fullstackproject.restbackend.domain.dto;

import java.io.Serializable;

import com.geocode.fullstackproject.restbackend.domain.Cliente;

/**
 * ClienteDTO
 */
public class ClienteDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;
  private String email;
  private String cpfouCnpj;
  private Integer tipo;

  public ClienteDTO() {
  }

  public ClienteDTO(Cliente cliente) {
    id = cliente.getId();
    nome = cliente.getNome();
    email = cliente.getEmail();
    cpfouCnpj = cliente.getCpfOuCnpj();
    tipo = cliente.getTipo().getCod();
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpfouCnpj() {
    return cpfouCnpj;
  }

  public void setCpfouCnpj(String cpfouCnpj) {
    this.cpfouCnpj = cpfouCnpj;
  }

  public Integer getTipo() {
    return tipo;
  }

  public void setTipo(Integer tipo) {
    this.tipo = tipo;
  }

}
