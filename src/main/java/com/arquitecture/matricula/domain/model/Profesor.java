package com.arquitecture.matricula.domain.model;

import com.arquitecture.matricula.domain.model.valueobjects.Email;
import com.arquitecture.matricula.domain.model.valueobjects.Dni;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;

import java.time.Instant;
import java.util.Objects;

public class Profesor {
    private final ProfesorId id;
    private String nombre;
    private String apellido;
    private Dni dni;
    private Email email;
    private String telefono;
    private String especialidad;
    private String tituloAcademico;
    private final Instant fechaRegistro;
    private boolean activo;

    public Profesor(ProfesorId id, String nombre, String apellido, Dni dni, Email email,
                    String telefono, String especialidad, String tituloAcademico,
                    Instant fechaRegistro, boolean activo) {
        this.id = Objects.requireNonNull(id, "Profesor ID cannot be null");
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.dni = Objects.requireNonNull(dni, "DNI cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.tituloAcademico = tituloAcademico;
        this.fechaRegistro = fechaRegistro == null ? Instant.now() : fechaRegistro;
        this.activo = activo;
    }

    public static Profesor create(String nombre, String apellido, Dni dni, Email email,
                                  String telefono, String especialidad, String tituloAcademico) {
        return new Profesor(ProfesorId.generate(), nombre, apellido, dni, email,
                telefono, especialidad, tituloAcademico, Instant.now(), true);
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre cannot be null or empty");
        }
        this.nombre = nombre.trim();
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Apellido cannot be null or empty");
        }
        this.apellido = apellido.trim();
    }

    public void activate() {
        this.activo = true;
    }

    public void deactivate() {
        this.activo = false;
    }

    public ProfesorId getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Dni getDni() { return dni; }
    public Email getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getEspecialidad() { return especialidad; }
    public String getTituloAcademico() { return tituloAcademico; }
    public Instant getFechaRegistro() { return fechaRegistro; }
    public boolean isActivo() { return activo; }
    public String getNombreCompleto() { return nombre + " " + apellido;}

    public void setDni(Dni dni) { this.dni = Objects.requireNonNull(dni, "DNI cannot be null"); }
    public void setEmail(Email email) { this.email = Objects.requireNonNull(email, "Email cannot be null"); }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setTituloAcademico(String tituloAcademico) { this.tituloAcademico = tituloAcademico; }
}
