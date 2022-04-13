package com.aluraflix.backend.controller;

import com.aluraflix.backend.entity.DTO.CategoriaDTO;
import com.aluraflix.backend.service.CategoriaService;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CategoriaDTO>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> getOne(Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO dto){
        return new ResponseEntity<>(service.save(dto),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id,@RequestBody CategoriaDTO dto){
        return new ResponseEntity<>(service.update(id,dto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}