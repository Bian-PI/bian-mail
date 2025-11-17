package com.bian.mail.controller;

import com.bian.mail.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send/{id}")
    public String sendTestEmail(@PathVariable Long id, @RequestParam String email) {
        String verificationLink = "http://localhost:8081/auth/verify?id=" + id;

        String htmlContent = """
        <div style="font-family: Arial, sans-serif; color: #333; background-color: #f4f4f4; padding: 20px;">
            <div style="max-width: 600px; margin: auto; background-color: white; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);">
                <div style="background-color: #ec1c21; color: white; padding: 16px; border-radius: 8px 8px 0 0;">
                    <h2 style="margin: 0;">BIAN - Bienestar Animal</h2>
                </div>
                <div style="padding: 24px;">
                    <p>Hola,</p>
                    <p>Gracias por registrarte en <strong>BIAN - Bienestar Animal</strong>.</p>
                    <p>Por favor, confirma tu correo electrónico haciendo clic en el siguiente botón:</p>
                    <p style="text-align: center;">
                        <a href="%s" style="display: inline-block; background-color: #ec1c21; color: white; padding: 12px 24px; text-decoration: none; border-radius: 6px; font-weight: bold;">
                            Verificar correo
                        </a>
                    </p>
                    <p>Si no creaste esta cuenta, puedes ignorar este mensaje.</p>
                    <p style="font-size: 12px; color: #888;">© 2025 BIAN - Todos los derechos reservados.</p>
                </div>
            </div>
        </div>
    """.formatted(verificationLink);

        return emailService.sendEmail(
                "BIAN - Bienestar Animal", "no-reply@test-65qngkd8d1dlwr12.mlsender.net",
                "Destinatario", email,
                "Verificación de Correo",
                "Por favor, verifica tu correo para continuar.",
                htmlContent
        );
    }
}