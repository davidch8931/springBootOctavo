package com.utc.project.controller;

import com.utc.project.entity.AuthUser;
import com.utc.project.entity.ParticipanteExterno;
import com.utc.project.repository.AuthUserRepository;
import com.utc.project.repository.CapacitacionRepository;
import com.utc.project.service.ParticipanteExternoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/participanteExterno")
public class ParticipanteExternoController {

    private final ParticipanteExternoService participanteService;
    private final CapacitacionRepository capacitacionRepository;
    private final AuthUserRepository userRepository;

    public ParticipanteExternoController(ParticipanteExternoService participanteService, 
                                         CapacitacionRepository capacitacionRepository,
                                         AuthUserRepository userRepository) {
        this.participanteService = participanteService;
        this.capacitacionRepository = capacitacionRepository;
        this.userRepository = userRepository;
    }

    // Este método asegura que 'nombreUsuario' esté disponible en TODAS las vistas de este controlador
    @ModelAttribute
    public void addAttributes(Model model, Principal principal) {
        if (principal != null) {
            AuthUser user = userRepository.findByCorreo(principal.getName());
            if (user != null) {
                model.addAttribute("nombreUsuario", user.getNombre() + " " + user.getApellido());
            }
        }
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("participantes", participanteService.listarTodos());
        return "participanteExterno/participanteIndex";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("participante", new ParticipanteExterno());
        model.addAttribute("capacitaciones", capacitacionRepository.findAll());
        return "participanteExterno/participanteForm";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ParticipanteExterno participante = participanteService.buscarPorId(id);
        if (participante == null) return "redirect:/participanteExterno";
        
        model.addAttribute("participante", participante);
        model.addAttribute("capacitaciones", capacitacionRepository.findAll());
        return "participanteExterno/participanteForm";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ParticipanteExterno participante) {
        participanteService.guardar(participante);
        return "redirect:/participanteExterno";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        participanteService.eliminar(id);
        return "redirect:/participanteExterno";
    }
}