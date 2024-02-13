package com.cji.citas.dto;

import com.cji.citas.entity.Tipocita;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name="Citas",
        description = "Esquema con el cuerpo de una cita"
)
public class CitasDTO {

    private Long id;

    @Schema(
            description = "Fecha inicial",
            example = "2025-02-13T10:00:00"
    )
    private LocalDateTime horaInicio;

    @Schema(
            description = "Fecha final",
            example = "2025-02-13T11:00:00"
    )
    private LocalDateTime horaFin;


    private UsersDTO usuario;


    private UsersDTO gestor;


    private TipocitaDTO tipoCita;

}
