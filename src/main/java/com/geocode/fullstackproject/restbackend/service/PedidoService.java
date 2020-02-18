package com.geocode.fullstackproject.restbackend.service;

import java.time.LocalDateTime;

import com.geocode.fullstackproject.restbackend.domain.ItemPedido;
import com.geocode.fullstackproject.restbackend.domain.PagamentoComBoleto;
import com.geocode.fullstackproject.restbackend.domain.Pedido;
import com.geocode.fullstackproject.restbackend.domain.enums.EstadoPagamento;
import com.geocode.fullstackproject.restbackend.repository.PedidoRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PedidoService
 */
@Service
public class PedidoService {

  private PedidoRepository repository;
  private BoletoService boletoService;
  private PagamentoService pagamentoService;
  private ProdutoService produtoService;
  private ItemPedidoService itemPedidoService;

  @Autowired
  public PedidoService(PedidoRepository repository, BoletoService boletoService, PagamentoService pagamentoService,
      ProdutoService produtoService, ItemPedidoService itemPedidoService) {
    this.repository = repository;
    this.boletoService = boletoService;
    this.pagamentoService = pagamentoService;
    this.produtoService = produtoService;
    this.itemPedidoService = itemPedidoService;
  }

  public Pedido findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar o pedido com o ID: " + id));
  }

  @Transactional
  public Pedido insert(Pedido pedido) {
    pedido.setId(null);
    pedido.setInstant(LocalDateTime.now());
    pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
    pedido.getPagamento().setPedido(pedido);
    if (pedido.getPagamento() instanceof PagamentoComBoleto) {
      PagamentoComBoleto pgto = (PagamentoComBoleto) pedido.getPagamento();
      boletoService.fillPagamentoComBoleto(pgto, pedido.getInstant());
    }

    pedido = repository.save(pedido);
    pagamentoService.insert(pedido.getPagamento());
    for (ItemPedido ip : pedido.getItens()) {
      ip.setDesconto(0.0);
      ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
      ip.setPedido(pedido);
    }
    itemPedidoService.saveAll(pedido.getItens());
    return pedido;
  }

}
