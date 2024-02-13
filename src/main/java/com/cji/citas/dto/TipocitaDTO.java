package com.cji.citas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name="Tipos de Cita",
        description = "Esquema con el cuerpo de tipos de cita"
)
public class TipocitaDTO {

    private Long id;

    @Schema(
            description = "Nombre de la gestión",
            example = "Empadronamiento y similares"
    )
    private String nombre;

    @Schema(
            description = "Sesiones de la gestión",
            example = "1"
    )
    private int sesiones;
    @Schema(
            description = "Precio de la gestión",
            example = "15.0"
    )
    private float precio;

}
