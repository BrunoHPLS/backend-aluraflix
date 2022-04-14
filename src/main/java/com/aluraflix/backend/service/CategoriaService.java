package com.aluraflix.backend.service;

import com.aluraflix.backend.entity.DTO.CategoriaResponseDTO;
import com.aluraflix.backend.entity.model.Categoria;
import com.aluraflix.backend.exceptions.EntityNotFoundException;
import com.aluraflix.backend.exceptions.EntityNullException;
import com.aluraflix.backend.mapper.CategoriaMapper;
import com.aluraflix.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaMapper mapper;

    public Page<CategoriaResponseDTO> findAll(){
        return mapper.toDTO(
                    repository.findAll(
                            PageRequest.of(0,10, Sort.by(Sort.Order.asc("id")))));
    }

    public CategoriaResponseDTO findById(Long id){
        try {
            return mapper.toDTO(repository.findById(id).get());
        }catch(NoSuchElementException ex){
            throw new EntityNotFoundException(new Categoria(),id);
        }
    }

    public CategoriaResponseDTO save(CategoriaResponseDTO dto){
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

    public CategoriaResponseDTO update(Long id, CategoriaResponseDTO dto){
        Categoria atualizada;
        try{
            atualizada = repository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException(new Categoria(), id);
        }
        try {
            for (Field f : dto.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(dto) == null) {
                    throw new EntityNullException(dto);
                }
            }
        }catch (IllegalAccessException ex){}
        atualizada = mapper.toEntity(dto);

        return mapper.toDTO(
                    repository.save(atualizada));
    }

    public void delete(Long id) {
        try {
            repository.delete(repository.findById(id).get());
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException(new Categoria(), id);
        }
    }
}
