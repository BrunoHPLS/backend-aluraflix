package com.aluraflix.backend.mapper;

import com.aluraflix.backend.entity.DTO.CategoriaDTO;
import com.aluraflix.backend.entity.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaDTO dto){
        return new Categoria(dto.getId(), dto.getTitulo(), dto.getCor());
    }

    public CategoriaDTO toDTO(Categoria entity){
        return new CategoriaDTO(entity.getId(),entity.getTitulo(),entity.getCor());
    }

    public Page<CategoriaDTO> toDTO(Page<Categoria> entityPage){
        return entityPage.map(this::toDTO);
    }
}
