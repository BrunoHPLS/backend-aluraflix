package com.aluraflix.backend.service;

import com.aluraflix.backend.entity.DTO.VideoDTO;
import com.aluraflix.backend.mapper.VideoMapper;
import com.aluraflix.backend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private VideoMapper mapper;

    public Page<VideoDTO> findAll(){
        return mapper.toDTO(
                repository.findAll(
                        PageRequest.of(0,10,Sort.by(Sort.Order.asc("id")))));
    }
}
