package com.cji.citas.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitasDTO {
    private Long id;
    private LocalDateTime fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private UsersDTO usuario;
    private UsersDTO gestor;

}
