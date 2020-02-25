package com.geocode.fullstackproject.restbackend.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * SmtpEmailService
 */
public class SmtpEmailService extends AbstractEmailService {

  @Autowired
  private MailSender mailSender;

  @Autowired
  private JavaMailSender javaMailSender;

  private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

  @Override
  public void sendEmail(SimpleMailMessage message) {
    LOG.info("Envio de email...");
    mailSender.send(message);
    LOG.info("Email enviado");
  }

  @Override
  public void sendHtmlEmail(MimeMessage msg) {
    LOG.info("Envio de email...");
    javaMailSender.send(msg);
    LOG.info("Email enviado");
  }

}
