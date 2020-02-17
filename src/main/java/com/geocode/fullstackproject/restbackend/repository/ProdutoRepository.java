package com.geocode.fullstackproject.restbackend.repository;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Categoria;
import com.geocode.fullstackproject.restbackend.domain.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProdutoRepository
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
  // @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat
  // WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
  // Page<Produto> search(@Param("nome") String nome, @Param("categorias")
  // List<Categoria> categorias, Pageable pageRequest);

  @Transactional(readOnly = true)
  Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
      Pageable pageRequest);
}
