package com.cji.citas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name="ContactForm",
        description = "Esquema con el cuerpo de un correo electrónico"
)
public class ContactFormDTO {

    @Schema(
            description = "Receptor del email",
            example = "imasesorescji@gmail.com"
    )
    private String to;
    @Schema(
            description = "Asunto del email",
            example = "Probando mi API"
    )
    private String subject;
    @Schema(
            description = "Mensaje del email",
            example = "Este es un mensaje para probar el email de contacto en mi API"
    )
    private String message;
}
