package com.arquitecture.matricula.application.dto.query;

import jakarta.validation.constraints.NotNull;

public class FindCursosByCarreraQuery {

    @NotNull(message = "Carrera ID es requerido")
    private String carreraId;

    private Integer nivelSemestre;
    private Boolean activo;

    public FindCursosByCarreraQuery() {}

    public FindCursosByCarreraQuery(String carreraId, Integer nivelSemestre, Boolean activo) {
        this.carreraId = carreraId;
        this.nivelSemestre = nivelSemestre;
        this.activo = activo;
    }

    public String getCarreraId() { return carreraId; }
    public void setCarreraId(String carreraId) { this.carreraId = carreraId; }

    public Integer getNivelSemestre() { return nivelSemestre; }
    public void setNivelSemestre(Integer nivelSemestre) { this.nivelSemestre = nivelSemestre; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}