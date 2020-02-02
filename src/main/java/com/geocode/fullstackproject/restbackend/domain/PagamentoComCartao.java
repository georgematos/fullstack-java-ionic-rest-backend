package com.geocode.fullstackproject.restbackend.domain;

import javax.persistence.Entity;

import com.geocode.fullstackproject.restbackend.domain.enums.EstadoPagamento;

/**
 * PagamentoComCartao
 */
@Entity
public class PagamentoComCartao extends Pagamento {

  private static final long serialVersionUID = 1L;

  private Integer numeroDeParcelas;

  public PagamentoComCartao() {
  }

  public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
    super(id, estado, pedido);
    this.numeroDeParcelas = numeroDeParcelas;
  }

  public Integer getNumeroDeParcelas() {
    return numeroDeParcelas;
  }

  public void setNumeroDeParcelas(Integer numeroDeParcelas) {
    this.numeroDeParcelas = numeroDeParcelas;
  }

}
