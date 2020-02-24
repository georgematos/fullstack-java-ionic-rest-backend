package com.geocode.fullstackproject.restbackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * SmtpEmailService
 */
public class SmtpEmailService extends AbstractEmailService {

  @Autowired
  private MailSender mailSender;

  private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
  
  @Override
  public void sendEmail(SimpleMailMessage message) {
    LOG.info("Envio de email...");
    mailSender.send(message);
    LOG.info("Email enviado");

  }

  
}
