package com.geocode.fullstackproject.restbackend.service;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailServiceImpl
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private ClienteRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    Cliente cliente = repository.findByEmail(username);
    if (cliente == null) {
      throw new UsernameNotFoundException(username);
    }

    return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
  }

  
}
