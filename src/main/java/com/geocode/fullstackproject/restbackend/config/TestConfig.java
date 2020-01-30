package com.geocode.fullstackproject.restbackend.config;

import java.util.Arrays;

import com.geocode.fullstackproject.restbackend.models.Categoria;
import com.geocode.fullstackproject.restbackend.models.Produto;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;
import com.geocode.fullstackproject.restbackend.repository.ProdutoRepository;

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

  public TestConfig(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
    this.categoriaRepository = categoriaRepository;
    this.produtoRepository = produtoRepository;
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

  }

}
