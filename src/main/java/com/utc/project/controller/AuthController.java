package com.utc.project.controller;

import com.utc.project.dto.UserRegistrationDto;
import com.utc.project.entity.AuthUser;
import com.utc.project.repository.AuthUserRepository;
import com.utc.project.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;

@Controller
public class AuthController {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthController(AuthUserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/iniciar-sesion")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute UserRegistrationDto dto) {
        AuthUser user = new AuthUser();
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setCorreo(dto.getCorreo());
        user.setTelefono(dto.getTelefono());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        
        String codigo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        user.setCodigoVerificacion(codigo);
        user.setCuentaVerificada(false);
        
        userRepository.save(user);
        emailService.enviarCorreoVerificacion(user.getCorreo(), codigo);
        
        return "redirect:/verificar?correo=" + user.getCorreo();
    }

    @GetMapping("/verificar")
    public String mostrarVerificacion() {
        return "auth/verificar";
    }

    @PostMapping("/verificar")
    public String procesarVerificacion(@RequestParam String correo, @RequestParam String codigo) {
        AuthUser user = userRepository.findByCorreo(correo);
        if (user != null && user.getCodigoVerificacion().equals(codigo)) {
            user.setCuentaVerificada(true);
            userRepository.save(user);
            return "redirect:/iniciar-sesion?verificado";
        }
        return "redirect:/verificar?error";
    }
}