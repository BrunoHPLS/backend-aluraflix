package com.aluraflix.backend.mapper;

import com.aluraflix.backend.entity.DTO.VideoDTO;
import com.aluraflix.backend.entity.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class VideoMapper {

    public Video toEntity(VideoDTO dto){
        return new Video(dto.getId(), dto.getTitulo(), dto.getDescricao(), dto.getUrl());
    }

    public VideoDTO toDTO(Video entity){
        return new VideoDTO(entity.getId(),entity.getTitulo(),entity.getDescricao(),entity.getUrl());
    }

    public Page<Video> toEntity(Page<VideoDTO> pageDTO){
        return pageDTO.map(this::toEntity);
    }

    public Page<VideoDTO> toDTO(Page<Video> page){
        return page.map(this::toDTO);
    }
}
