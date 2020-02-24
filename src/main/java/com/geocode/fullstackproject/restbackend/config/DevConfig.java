package com.geocode.fullstackproject.restbackend.config;

import com.geocode.fullstackproject.restbackend.service.DBService;
import com.geocode.fullstackproject.restbackend.service.EmailService;
import com.geocode.fullstackproject.restbackend.service.SmtpEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * DevConfig
 */
@Configuration
@Profile("dev")
public class DevConfig {

  @Autowired
  private DBService dbService;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String strategy;

  @Bean
  public boolean instantiateDatabase() throws Exception {

    if (!"create".equals(strategy)) {
      return false;
    }

    dbService.instantiateTestDatabase();
    return true;
  }

  @Bean
  public EmailService emailService() {
    return new SmtpEmailService();
  }

}
