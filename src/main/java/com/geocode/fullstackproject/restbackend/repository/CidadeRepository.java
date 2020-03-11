package com.geocode.fullstackproject.restbackend.repository;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CidadeRepository
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

  @Transactional(readOnly = true)
  public List<Cidade> findCidadesByEstadoId(Long id);

}
