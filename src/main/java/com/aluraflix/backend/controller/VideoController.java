package com.aluraflix.backend.controller;

import com.aluraflix.backend.entity.DTO.VideoDTO;
import com.aluraflix.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
