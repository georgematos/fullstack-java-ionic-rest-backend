package com.geocode.fullstackproject.restbackend.service;

import com.geocode.fullstackproject.restbackend.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

  void sendOrderConfirmationEmail(Pedido pedido);

  void sendEmail(SimpleMailMessage message);

}
