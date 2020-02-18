package com.geocode.fullstackproject.restbackend.service;

import java.time.LocalDateTime;

import com.geocode.fullstackproject.restbackend.domain.PagamentoComBoleto;

import org.springframework.stereotype.Service;

/**
 * BoletoService
 */
@Service
public class BoletoService {

  public void fillPagamentoComBoleto(PagamentoComBoleto pgto, LocalDateTime instenteDoPedido) {
    instenteDoPedido.plusWeeks(1L);
    pgto.setDataVencimento(instenteDoPedido.toLocalDate());
  }

}
