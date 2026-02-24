package com.utc.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "capacitacion")
public class Capacitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_cap;

    @NotBlank
    @Size(max = 500)
    private String area_cap;

    @NotBlank
    @Size(max = 500)
    private String nombre_curso_cap;

    @NotBlank
    @Size(max = 500)
    private String instructor_cap;

    @NotBlank
    @Size(max = 500)
    private String tipo_cap;

    @NotNull
    @Positive
    private Integer duracion_cap;

    @NotNull
    @Positive
    private Integer numero_participantes_cap;

    @NotNull
    @Positive
    private Float costo_cap;

    @Size(max = 500)
    private String temario_cap;

    @Size(max = 500)
    private String evaluacion_plan_accion_cap;

    @Size(max = 500)
    private String lista_asistentes_cap;

    private Long fk_codigo_est;

    private String fecha_hora_link_cap;

    @NotBlank
    private String fecha_cap;

    // Constructor vacío
    public Capacitacion() {}

    // Getters y Setters

    public Long getCodigo_cap() {
        return codigo_cap;
    }

    public void setCodigo_cap(Long codigo_cap) {
        this.codigo_cap = codigo_cap;
    }

    public String getArea_cap() {
        return area_cap;
    }

    public void setArea_cap(String area_cap) {
        this.area_cap = area_cap;
    }

    public String getNombre_curso_cap() {
        return nombre_curso_cap;
    }

    public void setNombre_curso_cap(String nombre_curso_cap) {
        this.nombre_curso_cap = nombre_curso_cap;
    }

    public String getInstructor_cap() {
        return instructor_cap;
    }

    public void setInstructor_cap(String instructor_cap) {
        this.instructor_cap = instructor_cap;
    }

    public String getTipo_cap() {
        return tipo_cap;
    }

    public void setTipo_cap(String tipo_cap) {
        this.tipo_cap = tipo_cap;
    }

    public Integer getDuracion_cap() {
        return duracion_cap;
    }

    public void setDuracion_cap(Integer duracion_cap) {
        this.duracion_cap = duracion_cap;
    }

    public Integer getNumero_participantes_cap() {
        return numero_participantes_cap;
    }

    public void setNumero_participantes_cap(Integer numero_participantes_cap) {
        this.numero_participantes_cap = numero_participantes_cap;
    }

    public Float getCosto_cap() {
        return costo_cap;
    }

    public void setCosto_cap(Float costo_cap) {
        this.costo_cap = costo_cap;
    }

    public String getTemario_cap() {
        return temario_cap;
    }

    public void setTemario_cap(String temario_cap) {
        this.temario_cap = temario_cap;
    }

    public String getEvaluacion_plan_accion_cap() {
        return evaluacion_plan_accion_cap;
    }

    public void setEvaluacion_plan_accion_cap(String evaluacion_plan_accion_cap) {
        this.evaluacion_plan_accion_cap = evaluacion_plan_accion_cap;
    }

    public String getLista_asistentes_cap() {
        return lista_asistentes_cap;
    }

    public void setLista_asistentes_cap(String lista_asistentes_cap) {
        this.lista_asistentes_cap = lista_asistentes_cap;
    }

    public Long getFk_codigo_est() {
        return fk_codigo_est;
    }

    public void setFk_codigo_est(Long fk_codigo_est) {
        this.fk_codigo_est = fk_codigo_est;
    }

    public String getFecha_hora_link_cap() {
        return fecha_hora_link_cap;
    }

    public void setFecha_hora_link_cap(String fecha_hora_link_cap) {
        this.fecha_hora_link_cap = fecha_hora_link_cap;
    }

    public String getFecha_cap() {
        return fecha_cap;
    }

    public void setFecha_cap(String fecha_cap) {
        this.fecha_cap = fecha_cap;
    }
}