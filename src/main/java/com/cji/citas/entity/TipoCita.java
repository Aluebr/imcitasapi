package com.cji.citas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

public class TipoCita {

    @Entity
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public class Citas {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombre;

        private int sesiones;

        private float precio;

    }

}
