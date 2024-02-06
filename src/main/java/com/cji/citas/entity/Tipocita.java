package com.cji.citas.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Tipocita {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombre;

        private int sesiones;

        private float precio;

}
