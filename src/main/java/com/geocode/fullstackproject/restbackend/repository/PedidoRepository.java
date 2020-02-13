package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PedidoRepository
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
