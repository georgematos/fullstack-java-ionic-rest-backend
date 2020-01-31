package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EstadoRepository
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  
}
