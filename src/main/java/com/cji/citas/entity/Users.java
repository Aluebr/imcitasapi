package com.cji.citas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Collection;

@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Column(unique = true)
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    private String roles;

}