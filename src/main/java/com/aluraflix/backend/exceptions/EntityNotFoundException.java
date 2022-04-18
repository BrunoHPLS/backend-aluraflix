package com.aluraflix.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    public static final long serialVersionUID = 1L;

    public EntityNotFoundException(Object entity,Object field) {
        super(HttpStatus.NOT_FOUND, String.format("%s with identifier: %s not found",entity.getClass().getName(),field));
    }
}
