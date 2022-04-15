package com.aluraflix.backend.controller;

import com.aluraflix.backend.entity.DTO.CategoriaResponseDTO;
import com.aluraflix.backend.entity.DTO.VideoWithoutCategoriasDTO;
import com.aluraflix.backend.service.CategoriaService;
import com.aluraflix.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Autowired
    private VideoService videoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CategoriaResponseDTO>> getAll(@RequestParam(required = false)Integer page){
        return new ResponseEntity<>(service.findAll(page), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaResponseDTO> getOne(Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/videos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<VideoWithoutCategoriasDTO>> getVideos(@RequestParam(required = false)Integer page,@PathVariable("id") Long id){
        return new ResponseEntity<>(videoService.getByCategorias(page,id),HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaResponseDTO> create(@RequestBody CategoriaResponseDTO dto){
        return new ResponseEntity<>(service.save(dto),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id, @RequestBody CategoriaResponseDTO dto){
        return new ResponseEntity<>(service.update(id,dto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
