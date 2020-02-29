package com.geocode.fullstackproject.restbackend.service;

import java.util.Random;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthService
 */
@Service
public class AuthService {

  @Autowired
  private ClienteRepository repository;

  @Autowired
  private BCryptPasswordEncoder bcrypt;

  @Autowired
  private EmailService emailService;

  private Random rand = new Random();

  public void sendNewPassword(String email) {

    Cliente cliente = repository.findByEmail(email);

    if(cliente == null) {
      throw new ObjectNotFoundException("Objeto não encontrado.");
    }

    String newPassword = newPassword();
    cliente.setSenha(bcrypt.encode(newPassword));
    repository.save(cliente);

    emailService.sendNewPasswordEmail(cliente, newPassword);
  }

  private String newPassword() {
    char[] vet = new char[10];
    for (int i = 0; i < 10; i++) {
      vet[i] = randomChar();
    }
    return new String(vet);
  }

  private char randomChar() {
    int opt = rand.nextInt(3);
    if (opt == 0) {
      return (char) (rand.nextInt(10) + 48);
    } else if (opt == 1) {
      return (char) (rand.nextInt(26) + 65);
    } else {
      return (char) (rand.nextInt(26) + 97);
    }
  }
  
}
