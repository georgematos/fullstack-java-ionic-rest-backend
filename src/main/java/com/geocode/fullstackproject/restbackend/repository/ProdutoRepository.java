package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.models.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
