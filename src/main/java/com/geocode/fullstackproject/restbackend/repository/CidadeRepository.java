package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CidadeRepository
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
