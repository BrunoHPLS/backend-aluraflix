package com.aluraflix.backend.repository;

import com.aluraflix.backend.entity.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria,Long> {

    @Query("SELECT ca FROM Categoria ca")
    Page<Categoria> findAll(Pageable pageable);

    @Query("SELECT ca FROM Categoria ca WHERE ca.id = :id")
    Optional<Categoria> findById(Long id);
}
