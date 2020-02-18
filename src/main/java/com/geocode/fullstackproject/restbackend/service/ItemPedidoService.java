package com.geocode.fullstackproject.restbackend.service;

import java.util.Set;

import com.geocode.fullstackproject.restbackend.domain.ItemPedido;
import com.geocode.fullstackproject.restbackend.repository.ItemPedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ItemPedidoService
 */
@Service
public class ItemPedidoService {

  @Autowired
  public ItemPedidoRepository repository;

  public void saveAll(Set<ItemPedido> itens) {
    repository.saveAll(itens);
  }
  
}
