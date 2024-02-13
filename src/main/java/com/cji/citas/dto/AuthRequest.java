package com.cji.citas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name="AuthRequest",
        description = "Obtener autorización"
)
public class AuthRequest {

    @Schema(
            description = "Nombre de usuario",
            example = "Carlos"
    )
    private String username;
    @Schema(
            description = "Contraseña de usuario",
            example = "hola01"
    )
    private String password; 
  
}