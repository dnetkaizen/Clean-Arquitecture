package com.arquitecture.matricula.application.dto.query;

import jakarta.validation.constraints.NotNull;

public class FindCarrerasByFacultadQuery {

    @NotNull(message = "Facultad ID es requerido")
    private String facultadId;

    private Boolean activa;

    public FindCarrerasByFacultadQuery() {}

    public FindCarrerasByFacultadQuery(String facultadId, Boolean activa) {
        this.facultadId = facultadId;
        this.activa = activa;
    }

    public String getFacultadId() { return facultadId; }
    public void setFacultadId(String facultadId) { this.facultadId = facultadId; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
}