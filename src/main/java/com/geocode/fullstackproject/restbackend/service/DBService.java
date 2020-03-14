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
    Produto p13 = new Produto(null, "Produto 13", 10.00);
    Produto p14 = new Produto(null, "Produto 14", 10.00);
    Produto p15 = new Produto(null, "Produto 15", 10.00);
    Produto p16 = new Produto(null, "Produto 16", 10.00);
    Produto p17 = new Produto(null, "Produto 17", 10.00);
    Produto p18 = new Produto(null, "Produto 18", 10.00);
    Produto p19 = new Produto(null, "Produto 19", 10.00);
    Produto p20 = new Produto(null, "Produto 20", 10.00);
    Produto p21 = new Produto(null, "Produto 21", 10.00);
    Produto p22 = new Produto(null, "Produto 22", 10.00);
    Produto p23 = new Produto(null, "Produto 23", 10.00);
    Produto p24 = new Produto(null, "Produto 24", 10.00);
    Produto p25 = new Produto(null, "Produto 25", 10.00);
    Produto p26 = new Produto(null, "Produto 26", 10.00);
    Produto p27 = new Produto(null, "Produto 27", 10.00);
    Produto p28 = new Produto(null, "Produto 28", 10.00);
    Produto p29 = new Produto(null, "Produto 29", 10.00);
    Produto p30 = new Produto(null, "Produto 30", 10.00);
    Produto p31 = new Produto(null, "Produto 31", 10.00);
    Produto p32 = new Produto(null, "Produto 32", 10.00);
    Produto p33 = new Produto(null, "Produto 33", 10.00);
    Produto p34 = new Produto(null, "Produto 34", 10.00);
    Produto p35 = new Produto(null, "Produto 35", 10.00);
    Produto p36 = new Produto(null, "Produto 36", 10.00);
    Produto p37 = new Produto(null, "Produto 37", 10.00);
    Produto p38 = new Produto(null, "Produto 38", 10.00);
    Produto p39 = new Produto(null, "Produto 39", 10.00);
    Produto p40 = new Produto(null, "Produto 40", 10.00);
    Produto p41 = new Produto(null, "Produto 41", 10.00);
    Produto p42 = new Produto(null, "Produto 42", 10.00);
    Produto p43 = new Produto(null, "Produto 43", 10.00);
    Produto p44 = new Produto(null, "Produto 44", 10.00);
    Produto p45 = new Produto(null, "Produto 45", 10.00);
    Produto p46 = new Produto(null, "Produto 46", 10.00);
    Produto p47 = new Produto(null, "Produto 47", 10.00);
    Produto p48 = new Produto(null, "Produto 48", 10.00);
    Produto p49 = new Produto(null, "Produto 49", 10.00);
    Produto p50 = new Produto(null, "Produto 50", 10.00);

    cat1.getProdutos()
        .addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29,
            p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

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
    p12.getCategorias().add(cat1);
    p13.getCategorias().add(cat1);
    p14.getCategorias().add(cat1);
    p15.getCategorias().add(cat1);
    p16.getCategorias().add(cat1);
    p17.getCategorias().add(cat1);
    p18.getCategorias().add(cat1);
    p19.getCategorias().add(cat1);
    p20.getCategorias().add(cat1);
    p21.getCategorias().add(cat1);
    p22.getCategorias().add(cat1);
    p23.getCategorias().add(cat1);
    p24.getCategorias().add(cat1);
    p25.getCategorias().add(cat1);
    p26.getCategorias().add(cat1);
    p27.getCategorias().add(cat1);
    p28.getCategorias().add(cat1);
    p29.getCategorias().add(cat1);
    p30.getCategorias().add(cat1);
    p31.getCategorias().add(cat1);
    p32.getCategorias().add(cat1);
    p33.getCategorias().add(cat1);
    p34.getCategorias().add(cat1);
    p35.getCategorias().add(cat1);
    p36.getCategorias().add(cat1);
    p37.getCategorias().add(cat1);
    p38.getCategorias().add(cat1);
    p39.getCategorias().add(cat1);
    p40.getCategorias().add(cat1);
    p41.getCategorias().add(cat1);
    p42.getCategorias().add(cat1);
    p43.getCategorias().add(cat1);
    p44.getCategorias().add(cat1);
    p45.getCategorias().add(cat1);
    p46.getCategorias().add(cat1);
    p47.getCategorias().add(cat1);
    p48.getCategorias().add(cat1);
    p49.getCategorias().add(cat1);
    p50.getCategorias().add(cat1);

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
    produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
    produtoRepository
        .saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29,
            p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

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
