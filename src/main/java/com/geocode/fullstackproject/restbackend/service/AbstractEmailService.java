package com.geocode.fullstackproject.restbackend.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.geocode.fullstackproject.restbackend.domain.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailService implements EmailService {

  @Value("${default.sender}")
  private String sender;

  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  private JavaMailSender jms;

  @Override
  public void sendOrderConfirmationEmail(Pedido pedido) {
    SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(pedido);
    sendEmail(smm);
  }

  protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
    SimpleMailMessage smm = new SimpleMailMessage();
    smm.setTo(pedido.getCliente().getEmail());
    smm.setFrom(sender);
    smm.setSubject("Pedido confirmado: " + pedido.getId());
    smm.setSentDate(new Date(System.currentTimeMillis()));
    smm.setText(pedido.toString());
    return smm;
  }

  @Override
  public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
    MimeMessage mm;
    try {
      mm = prepareMimeMessageFromPedido(pedido);
      sendHtmlEmail(mm);
    } catch (MessagingException e) {
      sendOrderConfirmationEmail(pedido);
    }
  }

  private MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
    MimeMessage mimeMessage = jms.createMimeMessage();
    MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
    mmh.setTo(pedido.getCliente().getEmail());
    mmh.setFrom(sender);
    mmh.setSubject("Pedido confirmado! Código: " + pedido.getId());
    mmh.setSentDate(new Date(System.currentTimeMillis()));
    mmh.setText(htmlFromTemplatePedido(pedido), true);
    return mimeMessage;
  }

  protected String htmlFromTemplatePedido(Pedido pedido) {
    Context context = new Context();
    context.setVariable("pedido", pedido);
    return templateEngine.process("email/confirmacaoPedido", context);
  }

}
