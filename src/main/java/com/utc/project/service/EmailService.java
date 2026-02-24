package com.utc.project.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCorreoVerificacion(String destino, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("controlapp6@gmail.com");
        mensaje.setTo(destino);
        mensaje.setSubject("Código de Verificación de Cuenta");
        mensaje.setText("Tu código de verificación es: " + codigo);
        mailSender.send(mensaje);
    }
    public void enviarCorreoRestablecimiento(String destino, String codigo) {
    org.springframework.mail.SimpleMailMessage mensaje = new org.springframework.mail.SimpleMailMessage();
    mensaje.setFrom("controlapp6@gmail.com");
    mensaje.setTo(destino);
    mensaje.setSubject("Restablecer Contraseña");
    mensaje.setText("Tu código para restablecer la contraseña es: " + codigo + "\nEste código expira en 15 minutos.");
    mailSender.send(mensaje);
}
}