package com.geocode.fullstackproject.restbackend.config;

import com.geocode.fullstackproject.restbackend.service.DBService;
import com.geocode.fullstackproject.restbackend.service.EmailService;
import com.geocode.fullstackproject.restbackend.service.MockEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * TestConfig
 */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

  @Autowired
  private DBService dbservice;

  @Override
  public void run(String... args) throws Exception {

    dbservice.instantiateTestDatabase();

  }

  @Bean
  public EmailService emailService() {
    return new MockEmailService();
  }
}
