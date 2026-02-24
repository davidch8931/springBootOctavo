package com.utc.project.service;

import com.utc.project.entity.ParticipanteExterno;
import com.utc.project.repository.ParticipanteExternoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipanteExternoService {

    private final ParticipanteExternoRepository repository;

    public ParticipanteExternoService(ParticipanteExternoRepository repository) {
        this.repository = repository;
    }

    public List<ParticipanteExterno> listarTodos() {
        return repository.findAll();
    }

    public ParticipanteExterno guardar(ParticipanteExterno participante) {
        return repository.save(participante);
    }

    public ParticipanteExterno buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}