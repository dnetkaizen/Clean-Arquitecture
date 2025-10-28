package com.arquitecture.matricula.application.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterProfesorCommand {

    @NotBlank(message = "Nombre es requerido")
    private String nombre;

    @NotBlank(message = "Apellido es requerido")
    private String apellido;

    @NotBlank(message = "DNI es requerido")
    private String dni;

    @NotBlank(message = "Email es requerido")
    @Email(message = "Formato de email inválido")
    private String email;

    private String telefono;

    private String especialidad;

    private String tituloAcademico;

    public RegisterProfesorCommand() {}

    public RegisterProfesorCommand(String nombre, String apellido, String dni, String email,
                                   String telefono, String especialidad, String tituloAcademico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.tituloAcademico = tituloAcademico;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTituloAcademico() { return tituloAcademico; }
    public void setTituloAcademico(String tituloAcademico) { this.tituloAcademico = tituloAcademico; }
}
