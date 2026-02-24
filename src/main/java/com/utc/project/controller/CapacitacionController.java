package com.utc.project.controller;

import com.utc.project.entity.Capacitacion;
import com.utc.project.repository.CapacitacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.utc.project.entity.AuthUser;
import com.utc.project.repository.AuthUserRepository;
import java.security.Principal;

import java.util.List;

@Controller
@RequestMapping("/capacitaciones")
public class CapacitacionController {

    @Autowired
    private CapacitacionRepository capacitacionRepository;

    @Autowired
    private AuthUserRepository userRepository;

    @ModelAttribute("nombreUsuario")
    public String getNombreUsuario(Principal principal) {
        if (principal != null) {
            AuthUser user = userRepository.findByCorreo(principal.getName());
            if (user != null) {
                return user.getNombre() + " " + user.getApellido();
            }
        }
        return "Usuario";
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        List<Capacitacion> lista = capacitacionRepository.findAll();
        model.addAttribute("capacitaciones", lista);
        return "capacitacion/lista";
    }

    // MOSTRAR FORMULARIO NUEVO
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("capacitacion", new Capacitacion());
        return "capacitacion/formulario";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Capacitacion capacitacion) {
        capacitacionRepository.save(capacitacion);
        return "redirect:/capacitaciones";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Capacitacion capacitacion = capacitacionRepository.findById(id).orElse(null);
        model.addAttribute("capacitacion", capacitacion);
        return "capacitacion/formulario";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        capacitacionRepository.deleteById(id);
        return "redirect:/capacitaciones";
    }
}