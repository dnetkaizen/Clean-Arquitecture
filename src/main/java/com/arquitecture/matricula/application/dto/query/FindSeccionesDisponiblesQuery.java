package com.arquitecture.matricula.application.dto.query;

import jakarta.validation.constraints.NotNull;

public class FindSeccionesDisponiblesQuery {

    @NotNull(message = "Curso ID es requerido")
    private String cursoId;

    @NotNull(message = "Periodo académico es requerido")
    private String periodoAcademico;

    private Integer capacidadMinima;

    public FindSeccionesDisponiblesQuery() {}

    public FindSeccionesDisponiblesQuery(String cursoId, String periodoAcademico, Integer capacidadMinima) {
        this.cursoId = cursoId;
        this.periodoAcademico = periodoAcademico;
        this.capacidadMinima = capacidadMinima;
    }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }

    public String getPeriodoAcademico() { return periodoAcademico; }
    public void setPeriodoAcademico(String periodoAcademico) { this.periodoAcademico = periodoAcademico; }

    public Integer getCapacidadMinima() { return capacidadMinima; }
    public void setCapacidadMinima(Integer capacidadMinima) { this.capacidadMinima = capacidadMinima; }
}