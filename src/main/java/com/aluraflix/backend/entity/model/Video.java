package com.aluraflix.backend.entity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo",nullable = false, length = 110)
    private String titulo;

    @Column(name = "descricao",nullable = false)
    private String descricao;

    @Column(name = "url",nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "isfree", nullable = false)
    private Boolean isfree;
}
