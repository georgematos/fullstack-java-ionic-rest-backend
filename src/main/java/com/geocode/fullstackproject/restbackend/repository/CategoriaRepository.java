package com.geocode.fullstackproject.restbackend.repository;

import com.geocode.fullstackproject.restbackend.models.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoriaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
