package com.aluraflix.backend.repository;

import com.aluraflix.backend.entity.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends PagingAndSortingRepository<Video,Long> {

    @Query("SELECT v FROM Video v")
    Page<Video> findAll(Pageable pageable);
}
