package com.aluraflix.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNullException extends ResponseStatusException {

    public static final long serialVersionUID = 1L;

    public EntityNullException(Object entity) {
        super(HttpStatus.NOT_ACCEPTABLE, String.format("Required field of %s is null",entity.getClass().getName()));
    }
}
