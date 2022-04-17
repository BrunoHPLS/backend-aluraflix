package com.aluraflix.backend.service;

import com.aluraflix.backend.entity.DTO.CategoriaResponseDTO;
import com.aluraflix.backend.entity.DTO.VideoResponseDTO;
import com.aluraflix.backend.entity.DTO.VideoRequestDTO;
import com.aluraflix.backend.entity.DTO.VideoWithoutCategoriasDTO;
import com.aluraflix.backend.entity.model.Video;
import com.aluraflix.backend.exceptions.EntityNotFoundException;
import com.aluraflix.backend.exceptions.EntityNullException;
import com.aluraflix.backend.mapper.VideoMapper;
import com.aluraflix.backend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private CategoriaService categoriaService;

    public Page<VideoResponseDTO> findAll(Integer page,String search){
        return mapper.toDTO(
                repository.findAll(
                        search,
                        PageRequest.of(page <= 1 ? 0:page-1,5,Sort.by(Sort.Order.asc("id")))));
    }

    public Page<VideoResponseDTO> findAllFree(Integer page){
        return mapper.toDTO(
                repository.findAllFree(
                        PageRequest.of(page <= 1 ? 0:page-1,5,Sort.by(Sort.Order.asc("id")))));
    }

    public Page<VideoWithoutCategoriasDTO> getByCategorias(Integer page,Long id) {
        return mapper.toVideoWithoutCategoriasDTO(
                repository.findByCategoria(id,PageRequest.of(page <= 1 ? 0:page-1,5,Sort.by(Sort.Order.asc("id")))));
    }

    public VideoResponseDTO findById(Long id){
        try{
            return mapper.toDTO(repository.findById(id).get());
        }catch(NoSuchElementException ex){
            throw new EntityNotFoundException(new Video(),id);
        }
    }

    public VideoResponseDTO create(VideoRequestDTO videoRequestDTO){
        try {
            for (Field f : videoRequestDTO.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(videoRequestDTO) == null && !f.getName().equals("id")) {
                    throw new EntityNullException(videoRequestDTO);
                }
            }
        }catch (IllegalAccessException ex){}

            CategoriaResponseDTO cat = categoriaService.findById(videoRequestDTO.getCategoriaId());

            return mapper.toDTO(
                    repository.save(
                            mapper.toEntity(
                                    mapper.toDTO(videoRequestDTO,cat))));
    }

    public VideoResponseDTO update(Long id, VideoRequestDTO videoRequestDTO){
        Video atualizado;
        try{
            atualizado = repository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException(new Video(),id);
        }
        try {
            for (Field f : videoRequestDTO.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(videoRequestDTO) == null) {
                    throw new EntityNullException(videoRequestDTO);
                }
            }
        }catch (IllegalAccessException ex){}

        CategoriaResponseDTO cat = categoriaService.findById(videoRequestDTO.getCategoriaId());

        atualizado = mapper.toEntity(mapper.toDTO(videoRequestDTO,cat));

        return mapper.toDTO(repository.save(atualizado));
    }

    public void delete(Long id){
        try{
           repository.delete(repository.findById(id).get());
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException(new Video(),id);
        }
    }
}
