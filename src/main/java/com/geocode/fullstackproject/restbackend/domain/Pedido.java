package com.geocode.fullstackproject.restbackend.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Pedido
 */
@Entity
public class Pedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime instant;

  @ManyToOne
  @JoinColumn(name = "endereco_de_entrega_id")
  private Endereco enderecoDeEntrega;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
  private Pagamento pagamento;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @OneToMany(mappedBy = "id.pedido")
  private Set<ItemPedido> itens = new HashSet<>();

  public Pedido() {
  }

  public Pedido(Long id, LocalDateTime instant, Endereco enderecoDeEntrega, Cliente cliente) {
    this.id = id;
    this.instant = instant;
    this.enderecoDeEntrega = enderecoDeEntrega;
    this.cliente = cliente;
  }

  @JsonIgnore
  public List<Produto> getProdutos() {
    List<Produto> produtos = new ArrayList<>();
    itens.stream().forEach(item -> produtos.add(item.getProduto()));
    return produtos;
  }

  public Double getTotalPedidos() {
    return itens.stream().map(x -> x.getSubTotal()).reduce((x, y) -> x + y).get();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getInstant() {
    return instant;
  }

  public void setInstant(LocalDateTime instant) {
    this.instant = instant;
  }

  public Endereco getEnderecoDeEntrega() {
    return enderecoDeEntrega;
  }

  public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public Pagamento getPagamento() {
    return pagamento;
  }

  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Set<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
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
    Pedido other = (Pedido) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
