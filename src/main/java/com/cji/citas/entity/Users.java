package com.cji.citas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    /*private boolean enabled;
    private boolean tokenExpired;*/

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Roles> roles;

}