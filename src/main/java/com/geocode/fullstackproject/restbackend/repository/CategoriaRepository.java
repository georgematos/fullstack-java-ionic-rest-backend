package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoriaRepository
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
