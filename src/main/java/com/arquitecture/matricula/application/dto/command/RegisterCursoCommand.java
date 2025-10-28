package com.arquitecture.matricula.application.dto.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterCursoCommand {

    @NotNull(message = "Carrera ID es requerido")
    private String carreraId;

    @NotBlank(message = "Código es requerido")
    private String codigo;

    @NotBlank(message = "Nombre es requerido")
    private String nombre;

    private String descripcion;

    @Min(value = 1, message = "Créditos debe ser mayor a 0")
    private int creditos;

    @Min(value = 1, message = "Nivel de semestre debe ser mayor a 0")
    private int nivelSemestre;

    public RegisterCursoCommand() {}

    public RegisterCursoCommand(String carreraId, String codigo, String nombre, String descripcion,
                                int creditos, int nivelSemestre) {
        this.carreraId = carreraId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.nivelSemestre = nivelSemestre;
    }

    public String getCarreraId() { return carreraId; }
    public void setCarreraId(String carreraId) { this.carreraId = carreraId; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public int getNivelSemestre() { return nivelSemestre; }
    public void setNivelSemestre(int nivelSemestre) { this.nivelSemestre = nivelSemestre; }
}
