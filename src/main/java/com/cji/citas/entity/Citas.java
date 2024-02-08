package com.cji.citas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Users usuario;

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    private Users gestor;

    @ManyToOne
    @JoinColumn(name = "tipo_cita_id")
    private Tipocita tipoCita;
}

