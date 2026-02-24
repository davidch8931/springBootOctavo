package com.utc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utc.project.entity.Capacitacion;

public interface CapacitacionRepository extends JpaRepository<Capacitacion, Long> {
    @org.springframework.data.jpa.repository.Query("SELECT c FROM Capacitacion c WHERE c.codigo_cap IN (SELECT MAX(c2.codigo_cap) FROM Capacitacion c2 GROUP BY c2.nombre_curso_cap)")
    java.util.List<Capacitacion> encontrarCursosUnicos();
}