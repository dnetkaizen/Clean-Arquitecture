package com.arquitecture.matricula.domain.model;

import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;

import java.time.Instant;
import java.util.Objects;

public class Facultad {
    private final FacultadId id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String decano;
    private final Instant fechaRegistro;
    private boolean activo;

    public Facultad(FacultadId id, String nombre, String descripcion, String ubicacion, 
                   String decano, Instant fechaRegistro, boolean activo) {
        this.id = Objects.requireNonNull(id, "Facultad ID cannot be null");
        this.setNombre(nombre);
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.decano = decano;
        this.fechaRegistro = fechaRegistro == null ? Instant.now() : fechaRegistro;
        this.activo = activo;
    }

    public static Facultad create(String nombre, String descripcion, String ubicacion, String decano) {
        return new Facultad(FacultadId.generate(), nombre, descripcion, ubicacion, 
                           decano, Instant.now(), true);
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre cannot be null or empty");
        }
        this.nombre = nombre.trim();
    }

    public void activate() {
        this.activo = true;
    }

    public void deactivate() {
        this.activo = false;
    }

    // Getters
    public FacultadId getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getUbicacion() { return ubicacion; }
    public String getDecano() { return decano; }
    public Instant getFechaRegistro() { return fechaRegistro; }
    public boolean isActivo() { return activo; }

    // Setters
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setDecano(String decano) { this.decano = decano; }
}