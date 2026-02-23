package com.utc.project.controller;

import com.utc.project.entity.AuthUser;
import com.utc.project.repository.AuthUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class HomeController {

    private final AuthUserRepository userRepository;

    public HomeController(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        if (principal != null) {
            AuthUser user = userRepository.findByCorreo(principal.getName());
            model.addAttribute("nombreUsuario", user.getNombre() + " " + user.getApellido());
        }
        return "index";
    }
}