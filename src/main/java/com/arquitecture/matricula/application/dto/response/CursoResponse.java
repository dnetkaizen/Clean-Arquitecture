package com.arquitecture.matricula.application.dto.response;

public class CursoResponse {
    private String id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int nivelSemestre;
    private String carreraId;
    private String carreraNombre;
    private int cantidadSecciones;
    private boolean activo;

    public CursoResponse() {}

    public CursoResponse(String id, String codigo, String nombre, String descripcion,
                        int creditos, int nivelSemestre, String carreraId, String carreraNombre,
                        int cantidadSecciones, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.nivelSemestre = nivelSemestre;
        this.carreraId = carreraId;
        this.carreraNombre = carreraNombre;
        this.cantidadSecciones = cantidadSecciones;
        this.activo = activo;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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

    public String getCarreraId() { return carreraId; }
    public void setCarreraId(String carreraId) { this.carreraId = carreraId; }

    public String getCarreraNombre() { return carreraNombre; }
    public void setCarreraNombre(String carreraNombre) { this.carreraNombre = carreraNombre; }

    public int getCantidadSecciones() { return cantidadSecciones; }
    public void setCantidadSecciones(int cantidadSecciones) { this.cantidadSecciones = cantidadSecciones; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}