package com.utc.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participante_externo")
public class ParticipanteExterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_parext;

    @ManyToOne
    @JoinColumn(name = "fk_codigo_cap")
    private Capacitacion capacitacion;

    private String identificacion_parext;
    private String apellido_parext;
    private String nombre_parext;
    private String telefono_parext;
    private String email_parext;
    
    @Column(name = "fecha_creacion_parext")
    private LocalDateTime fechaCreacionParext;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacionParext = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getCodigo_parext() { return codigo_parext; }
    public void setCodigo_parext(Long codigo_parext) { this.codigo_parext = codigo_parext; }

    public Capacitacion getCapacitacion() { return capacitacion; }
    public void setCapacitacion(Capacitacion capacitacion) { this.capacitacion = capacitacion; }

    public String getIdentificacion_parext() { return identificacion_parext; }
    public void setIdentificacion_parext(String identificacion_parext) { this.identificacion_parext = identificacion_parext; }

    public String getApellido_parext() { return apellido_parext; }
    public void setApellido_parext(String apellido_parext) { this.apellido_parext = apellido_parext; }

    public String getNombre_parext() { return nombre_parext; }
    public void setNombre_parext(String nombre_parext) { this.nombre_parext = nombre_parext; }

    public String getTelefono_parext() { return telefono_parext; }
    public void setTelefono_parext(String telefono_parext) { this.telefono_parext = telefono_parext; }

    public String getEmail_parext() { return email_parext; }
    public void setEmail_parext(String email_parext) { this.email_parext = email_parext; }

    public LocalDateTime getFechaCreacionParext() { return fechaCreacionParext; }
    public void setFechaCreacionParext(LocalDateTime fechaCreacionParext) { this.fechaCreacionParext = fechaCreacionParext; }
}