package com.geocode.fullstackproject.restbackend.repository;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * EstadoRepository
 */
public interface EstadoRepository extends JpaRepository<Estado, Long> {

  @Transactional(readOnly = true)
  public List<Estado> findAllByOrderByNome();

}
