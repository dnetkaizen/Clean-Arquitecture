package com.arquitecture.matricula.application.dto.command;

import jakarta.validation.constraints.NotBlank;

public class RegisterFacultadCommand {

    @NotBlank(message = "Nombre es requerido")
    private String nombre;

    private String descripcion;

    private String ubicacion;

    private String decano;

    public RegisterFacultadCommand() {}

    public RegisterFacultadCommand(String nombre, String descripcion, String ubicacion, String decano) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.decano = decano;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getDecano() { return decano; }
    public void setDecano(String decano) { this.decano = decano; }
}
