package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClienteRepository
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  @Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
