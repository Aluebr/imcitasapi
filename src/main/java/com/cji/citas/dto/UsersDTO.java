package com.cji.citas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(
        name="Users",
        description = "Esquema con el cuerpo de un correo electrónico"
)
public class UsersDTO {

    private Long id;

    @Schema(
            description = "Nombre de usuario",
            example = "Carlos"
    )
    private String name;

    @Schema(
            description = "Email del usuario",
            example = "carlos@email.com"
    )
    private String email;
    @Schema(
            description = "Contraseña del usuario",
            example = "hola01"
    )

    private String password;
}
