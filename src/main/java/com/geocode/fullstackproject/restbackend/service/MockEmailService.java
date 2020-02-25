package com.geocode.fullstackproject.restbackend.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * MockEmailService
 */
public class MockEmailService extends AbstractEmailService {

  private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

  @Override
  public void sendEmail(SimpleMailMessage message) {
    LOG.info("Simulando envio de email...");
    LOG.info(message.toString());
    LOG.info("Email enviado");
  }

  @Override
  public void sendHtmlEmail(MimeMessage msg) {
    LOG.info("Simulando envio de email HTML...");
    LOG.info(msg.toString());
    LOG.info("Email enviado");
  }

}
