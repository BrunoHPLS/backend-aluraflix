package com.aluraflix.backend.service;

import com.aluraflix.backend.entity.DTO.VideoDTO;
import com.aluraflix.backend.entity.model.Video;
import com.aluraflix.backend.exceptions.EntityNotFoundException;
import com.aluraflix.backend.exceptions.EntityNullException;
import com.aluraflix.backend.mapper.VideoMapper;
import com.aluraflix.backend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

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

    public VideoDTO findById(Long id){
        try{
            return mapper.toDTO(repository.findById(id).get());
        }catch(NoSuchElementException ex){
            throw new EntityNotFoundException(new Video(),id);
        }
    }

    public VideoDTO create(VideoDTO dto){
        try {
            for (Field f : dto.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(dto) == null && !f.getName().equals("id")) {
                    throw new EntityNullException(dto);
                }
            }
        }catch (IllegalAccessException ex){}
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(dto)));
    }

    public void delete(Long id){
        try{
           repository.delete(repository.findById(id).get());
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException(new Video(),id);
        }
    }
}
