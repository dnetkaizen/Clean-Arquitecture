package com.arquitecture.matricula.application.dto.response;

public class CarreraResponse {
    private String id;
    private String nombre;
    private String descripcion;
    private int duracionSemestres;
    private String tituloOtorgado;
    private String facultadId;
    private String facultadNombre;
    private int cantidadCursos;
    private boolean activa;

    public CarreraResponse() {}

    public CarreraResponse(String id, String nombre, String descripcion, int duracionSemestres,
                          String tituloOtorgado, String facultadId, String facultadNombre,
                          int cantidadCursos, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionSemestres = duracionSemestres;
        this.tituloOtorgado = tituloOtorgado;
        this.facultadId = facultadId;
        this.facultadNombre = facultadNombre;
        this.cantidadCursos = cantidadCursos;
        this.activa = activa;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getDuracionSemestres() { return duracionSemestres; }
    public void setDuracionSemestres(int duracionSemestres) { this.duracionSemestres = duracionSemestres; }

    public String getTituloOtorgado() { return tituloOtorgado; }
    public void setTituloOtorgado(String tituloOtorgado) { this.tituloOtorgado = tituloOtorgado; }

    public String getFacultadId() { return facultadId; }
    public void setFacultadId(String facultadId) { this.facultadId = facultadId; }

    public String getFacultadNombre() { return facultadNombre; }
    public void setFacultadNombre(String facultadNombre) { this.facultadNombre = facultadNombre; }

    public int getCantidadCursos() { return cantidadCursos; }
    public void setCantidadCursos(int cantidadCursos) { this.cantidadCursos = cantidadCursos; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}