package com.geocode.fullstackproject.restbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.geocode.fullstackproject.restbackend.domain.enums.TipoCliente;
import com.geocode.fullstackproject.restbackend.service.validations.cliente.CpfOuCnpj;

import org.hibernate.validator.constraints.Length;

/**
 * Cliente
 */
@Entity
@CpfOuCnpj
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Length(min = 5, max = 80, message = "Tamnho deve ser entre 5 e 80 cacacteres")
  private String nome;

  @Email(message = "Email inválido")
  @NotEmpty(message = "Preenchimento obrigatório")
  private String email;

  @NotEmpty(message = "Preenchimento obrigatório")
  private String cpfOuCnpj;
  private Integer tipo;

  // Quando uma associação for muito simples, com apenas 1 campo, pode-se fazer
  // assim não precisa criar uma entidade para a tabela telefone.
  @ElementCollection
  @CollectionTable(name = "TELEFONE")
  private Set<String> telefones = new HashSet<>();

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Endereco> enderecos = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidos = new ArrayList<>();

  public Cliente() {
  }

  public Cliente(Long id, String nome, String email, String cpfouCnpj, TipoCliente tipo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpfOuCnpj = cpfouCnpj;
    this.tipo = tipo.getCod();
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

  public String getCpfOuCnpj() {
    return cpfOuCnpj;
  }

  public void setCpfOuCnpj(String cpfouCnpj) {
    this.cpfOuCnpj = cpfouCnpj;
  }

  public TipoCliente getTipo() {
    return TipoCliente.toEnum(tipo);
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getCod();
  }

  public Set<String> getTelefones() {
    return telefones;
  }

  public void setTelefones(Set<String> telefones) {
    this.telefones = telefones;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public List<Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Cliente other = (Cliente) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
