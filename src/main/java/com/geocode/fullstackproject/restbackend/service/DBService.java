package com.geocode.fullstackproject.restbackend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.domain.Cidade;
import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.Endereco;
import com.geocode.fullstackproject.restbackend.domain.Estado;
import com.geocode.fullstackproject.restbackend.domain.ItemPedido;
import com.geocode.fullstackproject.restbackend.domain.Pagamento;
import com.geocode.fullstackproject.restbackend.domain.PagamentoComBoleto;
import com.geocode.fullstackproject.restbackend.domain.PagamentoComCartao;
import com.geocode.fullstackproject.restbackend.domain.Pedido;
import com.geocode.fullstackproject.restbackend.domain.Produto;
import com.geocode.fullstackproject.restbackend.domain.enums.EstadoPagamento;
import com.geocode.fullstackproject.restbackend.domain.enums.Perfil;
import com.geocode.fullstackproject.restbackend.domain.enums.TipoCliente;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;
import com.geocode.fullstackproject.restbackend.repository.CidadeRepository;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.repository.EnderecoRepository;
import com.geocode.fullstackproject.restbackend.repository.EstadoRepository;
import com.geocode.fullstackproject.restbackend.repository.ItemPedidoRepository;
import com.geocode.fullstackproject.restbackend.repository.PagamentoRepository;
import com.geocode.fullstackproject.restbackend.repository.PedidoRepository;
import com.geocode.fullstackproject.restbackend.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * DBService
 */
@Service
public class DBService {

  private CategoriaRepository categoriaRepository;
  private ProdutoRepository produtoRepository;
  private EstadoRepository estadoRepository;
  private CidadeRepository cidadeRepository;
  private ClienteRepository clienteRepository;
  private EnderecoRepository enderecoRepository;
  private PagamentoRepository pagamentoRepository;
  private PedidoRepository pedidoRepository;
  private ItemPedidoRepository itemPedidoRepository;
  private BCryptPasswordEncoder bCrypt;

  @Autowired
  public DBService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
      EstadoRepository estadoRepository, CidadeRepository cidadeRepository, ClienteRepository clienteRepository,
      EnderecoRepository enderecoRepository, PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository,
      ItemPedidoRepository itemPedidoRepository, BCryptPasswordEncoder bCrypt) {
    this.categoriaRepository = categoriaRepository;
    this.produtoRepository = produtoRepository;
    this.estadoRepository = estadoRepository;
    this.cidadeRepository = cidadeRepository;
    this.clienteRepository = clienteRepository;
    this.enderecoRepository = enderecoRepository;
    this.pagamentoRepository = pagamentoRepository;
    this.pedidoRepository = pedidoRepository;
    this.itemPedidoRepository = itemPedidoRepository;
    this.bCrypt = bCrypt;
  }

  public void instantiateTestDatabase() throws Exception {

    Categoria cat1 = new Categoria(null, "Informática");
    Categoria cat2 = new Categoria(null, "Escritório");
    Categoria cat3 = new Categoria(null, "Cama mesa e banho");
    Categoria cat4 = new Categoria(null, "Eletrônicos");
    Categoria cat5 = new Categoria(null, "Jardinagem");
    Categoria cat6 = new Categoria(null, "Decoração");
    Categoria cat7 = new Categoria(null, "Perfumaria");
    Categoria cat8 = new Categoria(null, "Games");

    Produto p1 = new Produto(null, "Computador", 2000.0);
    Produto p2 = new Produto(null, "Impressora", 800.0);
    Produto p3 = new Produto(null, "Mouse", 80.0);
    Produto p4 = new Produto(null, "Mesa de escritório", 300.0);
    Produto p5 = new Produto(null, "Toalha", 50.0);
    Produto p6 = new Produto(null, "Colcha", 200.0);
    Produto p7 = new Produto(null, "TV True Color", 1200.0);
    Produto p8 = new Produto(null, "Roçadeira", 800.0);
    Produto p9 = new Produto(null, "Abajour", 100.0);
    Produto p10 = new Produto(null, "Pendente", 180.0);
    Produto p11 = new Produto(null, "Shampoo", 90.0);
    Produto p12 = new Produto(null, "Playstation 5", 4090.0);

    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
    cat2.getProdutos().addAll(Arrays.asList(p2, p4));
    cat3.getProdutos().addAll(Arrays.asList(p5, p6));
    cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
    cat5.getProdutos().addAll(Arrays.asList(p8));
    cat6.getProdutos().addAll(Arrays.asList(p9, p10));
    cat7.getProdutos().addAll(Arrays.asList(p11));
    cat8.getProdutos().addAll(Arrays.asList(p12));

    p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
    p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
    p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
    p4.getCategorias().addAll(Arrays.asList(cat2));
    p5.getCategorias().addAll(Arrays.asList(cat3));
    p6.getCategorias().addAll(Arrays.asList(cat3));
    p7.getCategorias().addAll(Arrays.asList(cat4));
    p8.getCategorias().addAll(Arrays.asList(cat5));
    p9.getCategorias().addAll(Arrays.asList(cat6));
    p10.getCategorias().addAll(Arrays.asList(cat6));
    p11.getCategorias().addAll(Arrays.asList(cat7));

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
    produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));

    Estado estado1 = new Estado(null, "Ceará");
    Estado estado2 = new Estado(null, "São Paulo");

    Cidade cidade1 = new Cidade(null, "Maracanaú", estado1);
    Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
    Cidade cidade3 = new Cidade(null, "Campinas", estado2);

    estado1.getCidades().addAll(Arrays.asList(cidade1));
    estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

    estadoRepository.saveAll(Arrays.asList(estado1, estado2));
    cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

    Cliente cliente1 = new Cliente(null, "George Matos", "georgemattos@gmail.com", "37258072217",
        TipoCliente.PESSOAFISICA, bCrypt.encode("senhaCliente"));
    cliente1.getTelefones().addAll(Arrays.asList("27368833", "88372299"));

    Cliente cliente2 = new Cliente(null, "Ana Costa", "ana@gmail.com", "49608628660", TipoCliente.PESSOAFISICA,
        bCrypt.encode("senhaAdmin"));
    cliente1.getTelefones().addAll(Arrays.asList("27678844", "88382185"));
    cliente2.setPerfil(Perfil.ADMIN);

    Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cidade1, cliente1);
    Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cidade2,
        cliente1);
    Endereco endereco3 = new Endereco(null, "Rua Gringolandia", "666", null, "Rebimboca da Parafuseta", "56773012",
        cidade2, cliente2);

    cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
    cliente2.getEnderecos().addAll(Arrays.asList(endereco3));

    clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
    enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));

    LocalDateTime datePedido1 = LocalDateTime.of(2017, 9, 30, 10, 32);
    LocalDateTime datePedido2 = LocalDateTime.of(2017, 10, 10, 19, 35);

    Pedido pedido1 = new Pedido(null, datePedido1, endereco1, cliente1);
    Pedido pedido2 = new Pedido(null, datePedido2, endereco2, cliente1);

    Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
    pedido1.setPagamento(pagto1);

    LocalDate dateVencimento = LocalDate.of(2017, 10, 20);
    Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, dateVencimento, null);
    pedido2.setPagamento(pagto2);

    cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

    pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
    pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

    ItemPedido itemPedido1 = new ItemPedido(pedido1, p1, 0.0, 1, 2000.0);
    ItemPedido itemPedido2 = new ItemPedido(pedido1, p3, 0.0, 2, 80.0);
    ItemPedido itemPedido3 = new ItemPedido(pedido2, p2, 100.0, 1, 800.0);

    pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
    pedido2.getItens().addAll(Arrays.asList(itemPedido3));

    p1.getItens().addAll(Arrays.asList(itemPedido1));
    p2.getItens().addAll(Arrays.asList(itemPedido3));
    p3.getItens().addAll(Arrays.asList(itemPedido2));

    itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
  }
}
