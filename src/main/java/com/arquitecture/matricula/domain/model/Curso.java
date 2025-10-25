package com.arquitecture.matricula.domain.model;

import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.CodigoCurso;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;

import java.time.Instant;
import java.util.Objects;

public class Curso {
    private final CursoId id;
    private CarreraId carreraId;
    private CodigoCurso codigo;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int nivelSemestre;
    private final Instant fechaRegistro;
    private boolean activo;

    public Curso(CursoId id, CarreraId carreraId, CodigoCurso codigo, String nombre, 
                 String descripcion, int creditos, int nivelSemestre, Instant fechaRegistro, boolean activo) {
        this.id = Objects.requireNonNull(id, "Curso ID cannot be null");
        this.setCarreraId(carreraId);
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.descripcion = descripcion;
        this.setCreditos(creditos);
        this.setNivelSemestre(nivelSemestre);
        this.fechaRegistro = fechaRegistro == null ? Instant.now() : fechaRegistro;
        this.activo = activo;
    }

    public static Curso create(CarreraId carreraId, CodigoCurso codigo, String nombre, 
                               String descripcion, int creditos, int nivelSemestre) {
        return new Curso(CursoId.generate(), carreraId, codigo, nombre, descripcion, 
                        creditos, nivelSemestre, Instant.now(), true);
    }

    public void setCarreraId(CarreraId carreraId) {
        this.carreraId = Objects.requireNonNull(carreraId, "CarreraId cannot be null");
    }

    public void setCodigo(CodigoCurso codigo) {
        this.codigo = Objects.requireNonNull(codigo, "Código cannot be null");
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre cannot be null or empty");
        }
        this.nombre = nombre.trim();
    }

    public void setCreditos(int creditos) {
        if (creditos <= 0) {
            throw new IllegalArgumentException("Créditos must be greater than 0");
        }
        this.creditos = creditos;
    }

    public void setNivelSemestre(int nivelSemestre) {
        if (nivelSemestre <= 0) {
            throw new IllegalArgumentException("Nivel semestre must be greater than 0");
        }
        this.nivelSemestre = nivelSemestre;
    }

    public void activate() {
        this.activo = true;
    }

    public void deactivate() {
        this.activo = false;
    }

    // Getters
    public CursoId getId() { return id; }
    public CarreraId getCarreraId() { return carreraId; }
    public CodigoCurso getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getCreditos() { return creditos; }
    public int getNivelSemestre() { return nivelSemestre; }
    public Instant getFechaRegistro() { return fechaRegistro; }
    public boolean isActivo() { return activo; }

    // Setters
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}