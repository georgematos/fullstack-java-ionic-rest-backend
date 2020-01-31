package com.geocode.fullstackproject.restbackend.config;

import java.util.Arrays;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.domain.Cidade;
import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.Endereco;
import com.geocode.fullstackproject.restbackend.domain.Estado;
import com.geocode.fullstackproject.restbackend.domain.Produto;
import com.geocode.fullstackproject.restbackend.domain.enums.TipoCliente;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;
import com.geocode.fullstackproject.restbackend.repository.CidadeRepository;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.repository.EnderecoRepository;
import com.geocode.fullstackproject.restbackend.repository.EstadoRepository;
import com.geocode.fullstackproject.restbackend.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * TestConfig
 */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

  private CategoriaRepository categoriaRepository;
  private ProdutoRepository produtoRepository;
  private EstadoRepository estadoRepository;
  private CidadeRepository cidadeRepository;
  private ClienteRepository clienteRepository;
  private EnderecoRepository enderecoRepository;

  @Autowired
  public TestConfig(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
      EstadoRepository estadoRepository, CidadeRepository cidadeRepository, ClienteRepository clienteRepository,
      EnderecoRepository enderecoRepository) {
    this.categoriaRepository = categoriaRepository;
    this.produtoRepository = produtoRepository;
    this.estadoRepository = estadoRepository;
    this.cidadeRepository = cidadeRepository;
    this.clienteRepository = clienteRepository;
    this.enderecoRepository = enderecoRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Categoria cat1 = new Categoria(null, "Informática");
    Categoria cat2 = new Categoria(null, "Escritório");

    Produto p1 = new Produto(null, "Computador", 2000.0);
    Produto p2 = new Produto(null, "Impressora", 800.0);
    Produto p3 = new Produto(null, "Mouse", 80.0);

    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
    cat2.getProdutos().addAll(Arrays.asList(p2)); // uso o Arrays.asList para caso haja necessidad futura de acrescentar
                                                  // mais
    p1.getCategorias().addAll(Arrays.asList(cat1));
    p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
    p3.getCategorias().addAll(Arrays.asList(cat1));

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

    Estado estado1 = new Estado(null, "Ceará");
    Estado estado2 = new Estado(null, "São Paulo");

    // estadoRepository.saveAll(Arrays.asList(estado1, estado2));

    Cidade cidade1 = new Cidade(null, "Maracanaú", estado1);
    Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
    Cidade cidade3 = new Cidade(null, "Campinas", estado2);

    estado1.getCidades().addAll(Arrays.asList(cidade1));
    estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

    estadoRepository.saveAll(Arrays.asList(estado1, estado2));
    cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

    Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "30378912377", TipoCliente.PESSOAFISICA);
    cli1.getTelefones().addAll(Arrays.asList("27368833", "88372299"));

    // clienteRepository.saveAll(Arrays.asList(cli1));

    Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cidade1, cli1);
    Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cidade2, cli1);

    cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

    clienteRepository.saveAll(Arrays.asList(cli1));
    enderecoRepository.saveAll(Arrays.asList(e1, e2));

  }

}
