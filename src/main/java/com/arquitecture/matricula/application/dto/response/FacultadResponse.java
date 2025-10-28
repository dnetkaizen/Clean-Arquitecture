package com.arquitecture.matricula.application.dto.response;

public class FacultadResponse {
    private String id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String decano;
    private int cantidadCarreras;
    private boolean activa;

    public FacultadResponse() {}

    public FacultadResponse(String id, String nombre, String descripcion, String ubicacion, 
                           String decano, int cantidadCarreras, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.decano = decano;
        this.cantidadCarreras = cantidadCarreras;
        this.activa = activa;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getDecano() { return decano; }
    public void setDecano(String decano) { this.decano = decano; }

    public int getCantidadCarreras() { return cantidadCarreras; }
    public void setCantidadCarreras(int cantidadCarreras) { this.cantidadCarreras = cantidadCarreras; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}