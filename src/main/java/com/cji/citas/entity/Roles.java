/*
package com.cji.citas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity @Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<Users> users;


}*/
