package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.Pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * PedidoRepository
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
  @Transactional
  Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);

}
