package com.utc.project.repository;

import com.utc.project.entity.ParticipanteExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteExternoRepository extends JpaRepository<ParticipanteExterno, Long> {
}