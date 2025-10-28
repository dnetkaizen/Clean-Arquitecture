package com.arquitecture.matricula.application.dto.query;


public class FindProfesoresByEspecialidadQuery {

    private String especialidad;
    private String tituloAcademico;
    private Boolean activo;

    public FindProfesoresByEspecialidadQuery() {}

    public FindProfesoresByEspecialidadQuery(String especialidad, String tituloAcademico, Boolean activo) {
        this.especialidad = especialidad;
        this.tituloAcademico = tituloAcademico;
        this.activo = activo;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTituloAcademico() { return tituloAcademico; }
    public void setTituloAcademico(String tituloAcademico) { this.tituloAcademico = tituloAcademico; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}