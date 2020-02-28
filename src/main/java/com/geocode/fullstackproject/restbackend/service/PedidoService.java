package com.geocode.fullstackproject.restbackend.service;

import java.time.LocalDateTime;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.ItemPedido;
import com.geocode.fullstackproject.restbackend.domain.PagamentoComBoleto;
import com.geocode.fullstackproject.restbackend.domain.Pedido;
import com.geocode.fullstackproject.restbackend.domain.enums.EstadoPagamento;
import com.geocode.fullstackproject.restbackend.repository.PedidoRepository;
import com.geocode.fullstackproject.restbackend.security.UserSS;
import com.geocode.fullstackproject.restbackend.service.exceptions.AuthorizationException;
import com.geocode.fullstackproject.restbackend.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
  private ClienteService clienteService;

  @Autowired
  @Qualifier("smtpEmailService")
  private EmailService emailService;

  @Autowired
  public PedidoService(PedidoRepository repository, BoletoService boletoService, PagamentoService pagamentoService,
      ProdutoService produtoService, ItemPedidoService itemPedidoService, ClienteService clienteService) {
    this.repository = repository;
    this.boletoService = boletoService;
    this.pagamentoService = pagamentoService;
    this.produtoService = produtoService;
    this.itemPedidoService = itemPedidoService;
    this.clienteService = clienteService;
  }

  public Pedido findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar o pedido com o ID: " + id));
  }

  public Page<Pedido> findPage(Integer page, Integer linesPerPages, String direction, String orderBy) {
    UserSS user = UserService.authenticatedUser();
    if (user == null) {
      throw new AuthorizationException("Acesso negado");
    }

    PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
    Cliente cliente = clienteService.findById(user.getId());
    return repository.findByCliente(cliente, pageRequest);
  }

  @Transactional
  public Pedido insert(Pedido pedido) {
    pedido.setId(null);
    pedido.setInstant(LocalDateTime.now());
    pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
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
      ip.setProduto(produtoService.findById(ip.getProduto().getId()));
      ip.setPreco(ip.getProduto().getPreco());
      ip.setPedido(pedido);
    }
    itemPedidoService.saveAll(pedido.getItens());

    emailService.sendOrderConfirmationHtmlEmail(pedido);

    return pedido;
  }

}
