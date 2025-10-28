package com.arquitecture.matricula.application.dto.command;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateSeccionCommand {

    @NotNull(message = "Curso ID es requerido")
    private String cursoId;

    @NotNull(message = "Profesor ID es requerido")
    private String profesorId;

    @NotBlank(message = "Código es requerido")
    private String codigo;

    @Min(value = 1, message = "Capacidad máxima debe ser mayor a 0")
    private int capacidadMaxima;

    private String aula;

    private String horario;

    private String dias;

    @NotBlank(message = "Período académico es requerido")
    private String periodoAcademico;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    public CreateSeccionCommand() {}

    public CreateSeccionCommand(String cursoId, String profesorId, String codigo, int capacidadMaxima,
                                String aula, String horario, String dias, String periodoAcademico,
                                LocalDate fechaInicio, LocalDate fechaFin) {
        this.cursoId = cursoId;
        this.profesorId = profesorId;
        this.codigo = codigo;
        this.capacidadMaxima = capacidadMaxima;
        this.aula = aula;
        this.horario = horario;
        this.dias = dias;
        this.periodoAcademico = periodoAcademico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

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
}
