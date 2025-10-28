package com.arquitecture.matricula.application.dto.response;


public class ProfesorResponse {
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String telefono;
    private String especialidad;
    private String tituloAcademico;
    private int cantidadSecciones;
    private boolean activo;

    public ProfesorResponse() {}

    public ProfesorResponse(String id, String nombre, String apellido, String dni, String email,
                           String telefono, String especialidad, String tituloAcademico,
                           int cantidadSecciones, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.tituloAcademico = tituloAcademico;
        this.cantidadSecciones = cantidadSecciones;
        this.activo = activo;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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

    public int getCantidadSecciones() { return cantidadSecciones; }
    public void setCantidadSecciones(int cantidadSecciones) { this.cantidadSecciones = cantidadSecciones; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}