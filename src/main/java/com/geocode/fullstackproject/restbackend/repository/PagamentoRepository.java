package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PagamentoRepository
 */
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
