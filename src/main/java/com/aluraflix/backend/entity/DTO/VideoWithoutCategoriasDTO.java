package com.aluraflix.backend.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoWithoutCategoriasDTO {

    private String titulo;
    private String descricao;
    private String url;
}
