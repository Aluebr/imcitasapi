package com.cji.citas.controller;

import com.cji.citas.dto.ContactFormDTO;
import com.cji.citas.service.impl.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/contact")
    public ResponseEntity<String> sendContactEmail(@RequestBody ContactFormDTO contactForm) {
        try {
            emailService.sendContactMessage(contactForm.getFrom(), contactForm.getSubject(), contactForm.getMessage());
            return ResponseEntity.ok("Mensaje enviado con éxito");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al enviar el mensaje");
        }
    }
}
