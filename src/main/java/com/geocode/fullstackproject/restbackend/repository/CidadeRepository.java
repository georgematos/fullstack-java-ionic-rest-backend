package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CidadeRepository
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
