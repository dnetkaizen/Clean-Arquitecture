package com.arquitecture.matricula.domain.model;

import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;

import java.time.Instant;
import java.util.Objects;

public class Carrera {
    private final CarreraId id;
    private FacultadId facultadId;
    private String nombre;
    private String descripcion;
    private int duracionSemestres;
    private String tituloOtorgado;
    private final Instant fechaRegistro;
    private boolean activo;

    public Carrera(CarreraId id, FacultadId facultadId, String nombre, String descripcion,
                  int duracionSemestres, String tituloOtorgado, Instant fechaRegistro, boolean activo) {
        this.id = Objects.requireNonNull(id, "Carrera ID cannot be null");
        this.setFacultadId(facultadId);
        this.setNombre(nombre);
        this.descripcion = descripcion;
        this.setDuracionSemestres(duracionSemestres);
        this.tituloOtorgado = tituloOtorgado;
        this.fechaRegistro = fechaRegistro == null ? Instant.now() : fechaRegistro;
        this.activo = activo;
    }

    public static Carrera create(FacultadId facultadId, String nombre, String descripcion,
                                int duracionSemestres, String tituloOtorgado) {
        return new Carrera(CarreraId.generate(), facultadId, nombre, descripcion,
                          duracionSemestres, tituloOtorgado, Instant.now(), true);
    }

    public void setFacultadId(FacultadId facultadId) {
        this.facultadId = Objects.requireNonNull(facultadId, "FacultadId cannot be null");
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre cannot be null or empty");
        }
        this.nombre = nombre.trim();
    }

    public void setDuracionSemestres(int duracionSemestres) {
        if (duracionSemestres <= 0) {
            throw new IllegalArgumentException("Duracion semestres must be greater than 0");
        }
        this.duracionSemestres = duracionSemestres;
    }

    public void activate() {
        this.activo = true;
    }

    public void deactivate() {
        this.activo = false;
    }

    // Getters
    public CarreraId getId() { return id; }
    public FacultadId getFacultadId() { return facultadId; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getDuracionSemestres() { return duracionSemestres; }
    public String getTituloOtorgado() { return tituloOtorgado; }
    public Instant getFechaRegistro() { return fechaRegistro; }
    public boolean isActivo() { return activo; }

    // Setters
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setTituloOtorgado(String tituloOtorgado) { this.tituloOtorgado = tituloOtorgado; }
}