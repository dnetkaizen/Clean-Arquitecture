package com.arquitecture.matricula.application.dto.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterCarreraCommand {

    @NotNull(message = "Facultad ID es requerido")
    private String facultadId;

    @NotBlank(message = "Nombre es requerido")
    private String nombre;

    private String descripcion;

    @Min(value = 1, message = "Duración de semestres debe ser mayor a 0")
    private int duracionSemestres;

    private String tituloOtorgado;

    public RegisterCarreraCommand() {}

    public RegisterCarreraCommand(String facultadId, String nombre, String descripcion,
                                  int duracionSemestres, String tituloOtorgado) {
        this.facultadId = facultadId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionSemestres = duracionSemestres;
        this.tituloOtorgado = tituloOtorgado;
    }

    public String getFacultadId() { return facultadId; }
    public void setFacultadId(String facultadId) { this.facultadId = facultadId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getDuracionSemestres() { return duracionSemestres; }
    public void setDuracionSemestres(int duracionSemestres) { this.duracionSemestres = duracionSemestres; }

    public String getTituloOtorgado() { return tituloOtorgado; }
    public void setTituloOtorgado(String tituloOtorgado) { this.tituloOtorgado = tituloOtorgado; }
}
