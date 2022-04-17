package com.aluraflix.backend.mapper;

import com.aluraflix.backend.entity.DTO.CategoriaResponseDTO;
import com.aluraflix.backend.entity.DTO.VideoResponseDTO;
import com.aluraflix.backend.entity.DTO.VideoRequestDTO;
import com.aluraflix.backend.entity.DTO.VideoWithoutCategoriasDTO;
import com.aluraflix.backend.entity.model.Video;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class VideoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Video toEntity(VideoResponseDTO dto){
        return modelMapper.map(dto,Video.class);
    }

    public VideoResponseDTO toDTO(Video entity){
        return modelMapper.map(entity, VideoResponseDTO.class);
    }

    public VideoResponseDTO toDTO(VideoRequestDTO videoRequestDTO, CategoriaResponseDTO categoriaResponseDTO){
        return new VideoResponseDTO(
                    videoRequestDTO.getId(),
                    videoRequestDTO.getTitulo(),
                    videoRequestDTO.getDescricao(),
                    videoRequestDTO.getUrl(),
                    categoriaResponseDTO,
                    videoRequestDTO.getIsfree());
    }

    public VideoRequestDTO toVideoRequestDTO(VideoResponseDTO dto){
        return new VideoRequestDTO(dto.getId(), dto.getTitulo(), dto.getDescricao(), dto.getUrl(), dto.getCategoria().getId(),dto.getIsfree());
    }

    public VideoWithoutCategoriasDTO toVideoWithoutCategoriasDTO(Video entity){
        return modelMapper.map(entity, VideoWithoutCategoriasDTO.class);
    }

    public Page<Video> toEntity(Page<VideoResponseDTO> pageDTO){
        return pageDTO.map(this::toEntity);
    }

    public Page<VideoResponseDTO> toDTO(Page<Video> page){
        return page.map(this::toDTO);
    }

    public Page<VideoWithoutCategoriasDTO> toVideoWithoutCategoriasDTO(Page<Video> page){return page.map(this::toVideoWithoutCategoriasDTO);}
}
