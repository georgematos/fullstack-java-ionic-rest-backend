package com.geocode.fullstackproject.restbackend.service;

import javax.mail.internet.MimeMessage;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

  void sendOrderConfirmationEmail(Pedido pedido);

  void sendEmail(SimpleMailMessage message);

  void sendOrderConfirmationHtmlEmail(Pedido pedido);

  void sendHtmlEmail(MimeMessage msg);

  void sendNewPasswordEmail(Cliente cliente, String newPass);

}
