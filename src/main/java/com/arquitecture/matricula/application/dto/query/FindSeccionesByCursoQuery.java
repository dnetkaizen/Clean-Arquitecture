package com.arquitecture.matricula.application.dto.query;

import jakarta.validation.constraints.NotNull;

public class FindSeccionesByCursoQuery {

    @NotNull(message = "Curso ID es requerido")
    private String cursoId;

    private String periodoAcademico;
    private String profesorId;
    private Boolean activa;

    public FindSeccionesByCursoQuery() {}

    public FindSeccionesByCursoQuery(String cursoId, String periodoAcademico, String profesorId, Boolean activa) {
        this.cursoId = cursoId;
        this.periodoAcademico = periodoAcademico;
        this.profesorId = profesorId;
        this.activa = activa;
    }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }

    public String getPeriodoAcademico() { return periodoAcademico; }
    public void setPeriodoAcademico(String periodoAcademico) { this.periodoAcademico = periodoAcademico; }

    public String getProfesorId() { return profesorId; }
    public void setProfesorId(String profesorId) { this.profesorId = profesorId; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
}