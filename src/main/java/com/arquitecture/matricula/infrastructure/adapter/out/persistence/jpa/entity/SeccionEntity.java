package com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
 
@Entity
@Table(name = "seccion")
public class SeccionEntity {

    @Id
    @Column(name = "seccion_id", length = 36, nullable = false)
    private String id;

    @Column(name = "curso_id", length = 36, nullable = false)
    private String cursoId;

    @Column(name = "profesor_id", length = 36, nullable = false)
    private String profesorId;

    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    @Column(name = "capacidad_maxima", nullable = false)
    private int capacidadMaxima;

    @Column(name = "aula", length = 50)
    private String aula;

    @Column(name = "horario", length = 100)
    private String horario;

    @Column(name = "dias", length = 50)
    private String dias;

    @Column(name = "periodo_academico", nullable = false, length = 20)
    private String periodoAcademico;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public String getProfesorId() { return profesorId; }
    public void setProfesorId(String profesorId) { this.profesorId = profesorId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
    public String getAula() { return aula; }
    public void setAula(String aula) { this.aula = aula; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getDias() { return dias; }
    public void setDias(String dias) { this.dias = dias; }
    public String getPeriodoAcademico() { return periodoAcademico; }
    public void setPeriodoAcademico(String periodoAcademico) { this.periodoAcademico = periodoAcademico; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public Instant getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Instant fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
