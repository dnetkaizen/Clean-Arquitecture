package com.arquitecture.matricula.domain.model;

import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;
import com.arquitecture.matricula.domain.model.valueobjects.SeccionId;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class Seccion {
    private final SeccionId id;
    private CursoId cursoId;
    private ProfesorId profesorId;
    private String codigo;
    private int capacidadMaxima;
    private String aula;
    private String horario;
    private String dias;
    private String periodoAcademico;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private final Instant fechaRegistro;
    private boolean activo;

    public Seccion(SeccionId id,
                   CursoId cursoId,
                   ProfesorId profesorId,
                   String codigo,
                   int capacidadMaxima,
                   String aula,
                   String horario,
                   String dias,
                   String periodoAcademico,
                   LocalDate fechaInicio,
                   LocalDate fechaFin,
                   Instant fechaRegistro,
                   boolean activo) {
        this.id = Objects.requireNonNull(id, "Seccion ID cannot be null");
        this.setCursoId(cursoId);
        this.setProfesorId(profesorId);
        this.setCodigo(codigo);
        this.setCapacidadMaxima(capacidadMaxima);
        this.aula = aula;
        this.horario = horario;
        this.dias = dias;
        this.setPeriodoAcademico(periodoAcademico);
        this.setFechas(fechaInicio, fechaFin);
        this.fechaRegistro = fechaRegistro == null ? Instant.now() : fechaRegistro;
        this.activo = activo;
    }

    public static Seccion create(CursoId cursoId,
                                 ProfesorId profesorId,
                                 String codigo,
                                 int capacidadMaxima,
                                 String aula,
                                 String horario,
                                 String dias,
                                 String periodoAcademico,
                                 LocalDate fechaInicio,
                                 LocalDate fechaFin) {
        return new Seccion(SeccionId.generate(), cursoId, profesorId, codigo, capacidadMaxima,
                aula, horario, dias, periodoAcademico, fechaInicio, fechaFin, Instant.now(), true);
    }

    public void setCursoId(CursoId cursoId) {
        this.cursoId = Objects.requireNonNull(cursoId, "CursoId cannot be null");
    }

    public void setProfesorId(ProfesorId profesorId) {
        this.profesorId = Objects.requireNonNull(profesorId, "ProfesorId cannot be null");
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo cannot be null or empty");
        }
        this.codigo = codigo.trim();
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("Capacidad maxima must be greater than 0");
        }
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        if (periodoAcademico == null || periodoAcademico.trim().isEmpty()) {
            throw new IllegalArgumentException("Periodo academico cannot be null or empty");
        }
        this.periodoAcademico = periodoAcademico.trim();
    }

    public void setFechas(LocalDate inicio, LocalDate fin) {
        if (inicio != null && fin != null && fin.isBefore(inicio)) {
            throw new IllegalArgumentException("Fecha fin cannot be before fecha inicio");
        }
        this.fechaInicio = inicio;
        this.fechaFin = fin;
    }

    public void activate() { this.activo = true; }
    public void deactivate() { this.activo = false; }

    public SeccionId getId() { return id; }
    public CursoId getCursoId() { return cursoId; }
    public ProfesorId getProfesorId() { return profesorId; }
    public String getCodigo() { return codigo; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public String getAula() { return aula; }
    public String getHorario() { return horario; }
    public String getDias() { return dias; }
    public String getPeriodoAcademico() { return periodoAcademico; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public Instant getFechaRegistro() { return fechaRegistro; }
    public boolean isActivo() { return activo; }

    public void setAula(String aula) { this.aula = aula; }
    public void setHorario(String horario) { this.horario = horario; }
    public void setDias(String dias) { this.dias = dias; }
}
