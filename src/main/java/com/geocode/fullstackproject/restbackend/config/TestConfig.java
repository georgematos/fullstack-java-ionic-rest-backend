package com.geocode.fullstackproject.restbackend.config;

import java.util.Arrays;

import com.geocode.fullstackproject.restbackend.models.Categoria;
import com.geocode.fullstackproject.restbackend.repository.CategoriaRepository;

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

  public TestConfig(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Categoria cat1 = new Categoria(null, "Eletronics");
    Categoria cat2 = new Categoria(null, "Books");
    Categoria cat3 = new Categoria(null, "Computers");

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

  }

}
