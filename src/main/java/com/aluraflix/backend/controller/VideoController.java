package com.aluraflix.backend.controller;

import com.aluraflix.backend.entity.DTO.VideoDTO;
import com.aluraflix.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/videos")
@RestController
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<VideoDTO>> get(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VideoDTO> get(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VideoDTO> save(@RequestBody VideoDTO dto){
        return new ResponseEntity<>(service.create(dto),HttpStatus.CREATED);
    }
}
