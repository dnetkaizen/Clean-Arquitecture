package com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "curso")
public class CursoEntity {

    @Id
    @Column(name = "curso_id", length = 36, nullable = false)
    private String id;

    @Column(name = "carrera_id", length = 36, nullable = false)
    private String carreraId;

    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "creditos", nullable = false)
    private int creditos;

    @Column(name = "nivel_semestre", nullable = false)
    private int nivelSemestre;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
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
    public Instant getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Instant fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
