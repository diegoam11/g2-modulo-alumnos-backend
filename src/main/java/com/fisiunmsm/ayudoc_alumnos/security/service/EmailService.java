package com.fisiunmsm.ayudoc_alumnos.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public Mono<Void> sendResetCodeEmail(String email, String codigoReinicio) {
        return Mono.fromRunnable(() -> {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(sender);
                message.setTo(email);
                message.setSubject("Código de Reinicio de Contraseña");
                message.setText("Tu código de reinicio de contraseña es: " + codigoReinicio);
                javaMailSender.send(message);
            } catch (Exception e) {
                throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
            }
        });
    }
}
