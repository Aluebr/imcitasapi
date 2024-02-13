package com.cji.citas.controller;

import com.cji.citas.dto.ContactFormDTO;
import com.cji.citas.service.impl.EmailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "04 - API Email de contacto",
        description = "Enviar correos electrónicos automatizados"
)
@RestController
public class ContactController {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Operation(
            summary = "Email de contacto",
            description = "Enviar email desde el formulario de contacto"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @SecurityRequirement(name="bearerAuth")
    @PostMapping("/contact")
    public ResponseEntity<String> sendContactEmail(@RequestBody ContactFormDTO contactForm) {
        try {
            emailServiceImpl.sendContactMessage(contactForm.getTo(), contactForm.getSubject(), contactForm.getMessage());
            return ResponseEntity.ok("Mensaje enviado con éxito");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al enviar el mensaje");
        }
    }

    @Operation(
            summary = "Email de registro",
            description = "Enviar email de bienvenida tras registro"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @SecurityRequirement(name="bearerAuth")
    @PostMapping("/registerMail")
    public ResponseEntity<String> sendRegisterEmail(@RequestBody ContactFormDTO contactForm) {
        try {
            emailServiceImpl.sendRegisterMessage(contactForm.getTo(), contactForm.getSubject(), contactForm.getMessage());
            return ResponseEntity.ok("Mensaje enviado con éxito");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al enviar el mensaje");
        }
    }
}
