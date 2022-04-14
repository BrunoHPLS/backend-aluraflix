package com.aluraflix.backend.mapper;

import com.aluraflix.backend.entity.DTO.CategoriaResponseDTO;
import com.aluraflix.backend.entity.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Categoria toEntity(CategoriaResponseDTO dto){
        return modelMapper.map(dto,Categoria.class);
    }

    public CategoriaResponseDTO toDTO(Categoria entity){
        return modelMapper.map(entity, CategoriaResponseDTO.class);
    }

    public Page<CategoriaResponseDTO> toDTO(Page<Categoria> entityPage){
        return entityPage.map(this::toDTO);
    }
}
