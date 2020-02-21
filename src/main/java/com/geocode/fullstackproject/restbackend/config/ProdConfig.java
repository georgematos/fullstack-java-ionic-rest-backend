package com.geocode.fullstackproject.restbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * DevConfig
 */
@Configuration
@Profile("prod")
public class ProdConfig {

  @Bean
  public boolean instantiateDatabase() throws Exception {
    return true;
  }

}
