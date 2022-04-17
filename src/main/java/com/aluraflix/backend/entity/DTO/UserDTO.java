package com.aluraflix.backend.entity.DTO;

import com.aluraflix.backend.entity.enums.ROLE;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserDTO {

    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private ROLE role;
}
