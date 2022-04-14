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

    @Query("SELECT v FROM Video v")
    Page<Video> findAll(Pageable pageable);

    @Query("SELECT v FROM Video v WHERE v.id = :id")
    Optional<Video> findById(Long id);

    @Query("SELECT v FROM Video v WHERE v.categoria.id = :id")
    Page<Video> findByCategoria(Long id, Pageable pageable);
}
