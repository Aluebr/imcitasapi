package com.cji.citas.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMessage(String to, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String[] toAddresses = {to, "imasesorescji@gmail.com"};

        helper.setTo(toAddresses);
        helper.setText(message, true);
        helper.setSubject(subject);
        helper.setFrom("imasesorescji@gmail.com");

        mailSender.send(mimeMessage);
    }
    public void sendRegisterMessage(String to, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setTo(to);
        helper.setText(message, true);
        helper.setSubject(subject);
        helper.setFrom("imasesorescji@gmail.com");

        mailSender.send(mimeMessage);
    }
}
