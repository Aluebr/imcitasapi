package com.cji.citas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String roles;

}