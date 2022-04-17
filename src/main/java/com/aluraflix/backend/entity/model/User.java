package com.aluraflix.backend.entity.model;

import com.aluraflix.backend.entity.enums.ROLE;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 20)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ROLE role;

}
