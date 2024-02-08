package com.cji.citas.dto;

import com.cji.citas.entity.Tipocita;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitasDTO {
    private Long id;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private UsersDTO usuario;
    private UsersDTO gestor;
    private TipocitaDTO tipoCita;

}
