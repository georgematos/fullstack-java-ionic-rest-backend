package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * EnderecoRepository
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
