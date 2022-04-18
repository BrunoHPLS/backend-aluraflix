package com.aluraflix.backend.repository;

import com.aluraflix.backend.entity.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends PagingAndSortingRepository<Video,Long> {

    @Query("SELECT v FROM Video v WHERE (:titulo IS NULL OR v.titulo LIKE %:titulo%) AND (:auth IS TRUE OR v.isfree = true)")
    Page<Video> findAll(Boolean auth,String titulo,Pageable pageable);

    @Query("SELECT v FROM Video v WHERE v.id = :id AND (:auth IS TRUE OR v.isfree = true)")
    Optional<Video> findById(Boolean auth,Long id);

    @Query("SELECT v FROM Video v WHERE v.categoria.id = :id")
    Page<Video> findByCategoria(Long id, Pageable pageable);
}
