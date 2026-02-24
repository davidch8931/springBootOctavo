package com.utc.project.controller;

import com.utc.project.dto.UserRegistrationDto;
import com.utc.project.entity.AuthUser;
import com.utc.project.repository.AuthUserRepository;
import com.utc.project.service.EmailService;
import com.utc.project.service.VerificationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final VerificationService verificationService;

    public AuthController(AuthUserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, VerificationService verificationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.verificationService = verificationService;
    }

    @GetMapping("/iniciar-sesion")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new UserRegistrationDto());
        }
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute UserRegistrationDto dto, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        if (userRepository.existsByCorreo(dto.getCorreo())) {
            redirectAttributes.addFlashAttribute("errorCorreo", true);
            redirectAttributes.addFlashAttribute("dto", dto);
            return "redirect:/registro";
        }
        if (userRepository.existsByTelefono(dto.getTelefono())) {
            redirectAttributes.addFlashAttribute("errorTelefono", true);
            redirectAttributes.addFlashAttribute("dto", dto);
            return "redirect:/registro";
        }

        AuthUser user = new AuthUser();
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setCorreo(dto.getCorreo());
        user.setTelefono(dto.getTelefono());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCuentaVerificada(false);
        userRepository.save(user);
        
        String codigo = String.valueOf(new java.util.Random().nextInt(900000) + 100000);
        verificationService.saveCode(user.getCorreo(), codigo);
        emailService.enviarCorreoVerificacion(user.getCorreo(), codigo);
        
        return "redirect:/verificar?correo=" + user.getCorreo();
    }

    @GetMapping("/verificar")
    public String mostrarVerificacion() {
        return "auth/verificar";
    }

    @PostMapping("/verificar")
    public String procesarVerificacion(@RequestParam String correo, @RequestParam String codigo) {
        boolean isValid = verificationService.verifyCode(correo, codigo);
        
        if (isValid) {
            AuthUser user = userRepository.findByCorreo(correo);
            if (user != null) {
                user.setCuentaVerificada(true);
                userRepository.save(user);
                return "redirect:/iniciar-sesion?verificado";
            }
        }
        // Se añade el correo a la redirección para no perderlo de la vista
        return "redirect:/verificar?error&correo=" + correo;
    }

    @GetMapping("/olvide-password")
    public String mostrarOlvidePassword() {
        return "auth/olvide-password";
    }

    @PostMapping("/olvide-password")
    public String procesarOlvidePassword(@RequestParam String correo) {
        AuthUser user = userRepository.findByCorreo(correo);
        if (user != null) {
            // Genera un número aleatorio entre 100000 y 999999
            String codigo = String.valueOf(new java.util.Random().nextInt(900000) + 100000);
            verificationService.saveCode(correo, codigo);
            emailService.enviarCorreoRestablecimiento(correo, codigo);
        }
        return "redirect:/restablecer-password?correo=" + correo;
    }

    @GetMapping("/restablecer-password")
    public String mostrarRestablecerPassword() {
        return "auth/restablecer-password";
    }

    @PostMapping("/restablecer-password")
    public String procesarRestablecerPassword(@RequestParam String correo, @RequestParam String codigo, @RequestParam String password) {
        boolean isValid = verificationService.verifyCode(correo, codigo);
        if (isValid) {
            AuthUser user = userRepository.findByCorreo(correo);
            if (user != null) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                return "redirect:/iniciar-sesion?restablecido";
            }
        }
        return "redirect:/restablecer-password?error&correo=" + correo;
    }
}