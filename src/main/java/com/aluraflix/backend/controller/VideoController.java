package com.aluraflix.backend.controller;

import com.aluraflix.backend.entity.DTO.VideoResponseDTO;
import com.aluraflix.backend.entity.DTO.VideoRequestDTO;
import com.aluraflix.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<VideoResponseDTO>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VideoResponseDTO> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VideoResponseDTO> create(@RequestBody VideoRequestDTO simpleDTO){
        return new ResponseEntity<>(service.create(simpleDTO),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VideoResponseDTO> update(@PathVariable("id") Long id, @RequestBody VideoRequestDTO simpleDTO){
        return new ResponseEntity<>(service.update(id,simpleDTO),HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
