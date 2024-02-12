package com.cji.citas.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMessage(String from, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        // Aquí, asegúrate de que estás usando correctamente la dirección "to".
        // Esta dirección debe ser válida y no nula.
        helper.setTo("imasesorescji@gmail.com");
        helper.setText(message, true); // true indica que el mensaje puede contener HTML
        helper.setSubject(subject);
        helper.setFrom(from);

        mailSender.send(mimeMessage);
    }
}
