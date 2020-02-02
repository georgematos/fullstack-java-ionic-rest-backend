package com.geocode.fullstackproject.restbackend.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.geocode.fullstackproject.restbackend.domain.enums.EstadoPagamento;

/**
 * PagamentoComBoleto
 */
@Entity
public class PagamentoComBoleto extends Pagamento {

  private static final long serialVersionUID = 1L;

  private LocalDate dataVencimento;
  private LocalDate dataPagamento;

  public PagamentoComBoleto() {
  }

  public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, LocalDate dataVencimento,
      LocalDate dataPagamento) {
    super(id, estado, pedido);
    this.dataVencimento = dataVencimento;
    this.dataPagamento = dataPagamento;
  }

  public LocalDate getDataVencimento() {
    return dataVencimento;
  }

  public void setDataVencimento(LocalDate dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  public LocalDate getDataPagamento() {
    return dataPagamento;
  }

  public void setDataPagamento(LocalDate dataPagamento) {
    this.dataPagamento = dataPagamento;
  }

}