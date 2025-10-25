package com.bian.mail.controller;

import com.bian.mail.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendTestEmail() {
        return emailService.sendEmail(
                "Mi Nombre", "no-reply@test-q3enl6kq69r42vwr.mlsender.net",
                "Destinatario", "ljacomera@ufpso.edu.co",
                "Prueba Spring Boot",
                "Contenido de texto",
                "<p>Contenido HTML</p>"
        );
    }
}
