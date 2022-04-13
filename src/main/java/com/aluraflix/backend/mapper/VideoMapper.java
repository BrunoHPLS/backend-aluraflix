package com.aluraflix.backend.mapper;

import com.aluraflix.backend.entity.DTO.VideoDTO;
import com.aluraflix.backend.entity.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class VideoMapper {

    private CategoriaMapper categoriaMapper;

    public Video toEntity(VideoDTO dto){
        return new Video(dto.getId(), dto.getTitulo(), dto.getDescricao(), dto.getUrl(), categoriaMapper.toEntity(dto.getCategoria()));
    }

    public VideoDTO toDTO(Video entity){
        return new VideoDTO(entity.getId(),entity.getTitulo(),entity.getDescricao(),entity.getUrl(),categoriaMapper.toDTO(entity.getCategoria()));
    }

    public Page<Video> toEntity(Page<VideoDTO> pageDTO){
        return pageDTO.map(this::toEntity);
    }

    public Page<VideoDTO> toDTO(Page<Video> page){
        return page.map(this::toDTO);
    }
}
