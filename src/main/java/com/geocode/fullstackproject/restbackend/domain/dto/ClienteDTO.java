package com.geocode.fullstackproject.restbackend.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.service.validations.cliente.ClienteUpdate;

import org.hibernate.validator.constraints.Length;

/**
 * ClienteDTO
 */
@ClienteUpdate
public class ClienteDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Length(min = 5, max = 80, message = "Tamnho deve ser entre 5 e 80 cacacteres")
  private String nome;
  
  @Email(message = "Email inválido")
  @NotEmpty(message = "Preenchimento obrigatório")
  private String email;

  @NotEmpty
  private String senha;

  public ClienteDTO() {
  }

  public ClienteDTO(Cliente cliente) {
    id = cliente.getId();
    nome = cliente.getNome();
    email = cliente.getEmail();
    senha = cliente.getSenha();
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

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
