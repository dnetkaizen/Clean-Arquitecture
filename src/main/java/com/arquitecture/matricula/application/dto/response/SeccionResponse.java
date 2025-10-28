package com.arquitecture.matricula.application.dto.response;

import java.time.LocalDate;

public class SeccionResponse {
    private String id;
    private String codigo;
    private int capacidadMaxima;
    private int estudiantesInscritos;
    private String aula;
    private String horario;
    private String dias;
    private String periodoAcademico;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String cursoId;
    private String cursoNombre;
    private String profesorId;
    private String profesorNombre;
    private boolean activa;
    private boolean tieneCuposDisponibles;

    public SeccionResponse() {}

    public SeccionResponse(String id, String codigo, int capacidadMaxima, int estudiantesInscritos,
                          String aula, String horario, String dias, String periodoAcademico,
                          LocalDate fechaInicio, LocalDate fechaFin, String cursoId, String cursoNombre,
                          String profesorId, String profesorNombre, boolean activa) {
        this.id = id;
        this.codigo = codigo;
        this.capacidadMaxima = capacidadMaxima;
        this.estudiantesInscritos = estudiantesInscritos;
        this.aula = aula;
        this.horario = horario;
        this.dias = dias;
        this.periodoAcademico = periodoAcademico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cursoId = cursoId;
        this.cursoNombre = cursoNombre;
        this.profesorId = profesorId;
        this.profesorNombre = profesorNombre;
        this.activa = activa;
        this.tieneCuposDisponibles = estudiantesInscritos < capacidadMaxima;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public int getEstudiantesInscritos() { return estudiantesInscritos; }
    public void setEstudiantesInscritos(int estudiantesInscritos) { 
        this.estudiantesInscritos = estudiantesInscritos;
        this.tieneCuposDisponibles = estudiantesInscritos < capacidadMaxima;
    }

    public String getAula() { return aula; }
    public void setAula(String aula) { this.aula = aula; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getDias() { return dias; }
    public void setDias(String dias) { this.dias = dias; }

    public String getPeriodoAcademico() { return periodoAcademico; }
    public void setPeriodoAcademico(String periodoAcademico) { this.periodoAcademico = periodoAcademico; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }

    public String getCursoNombre() { return cursoNombre; }
    public void setCursoNombre(String cursoNombre) { this.cursoNombre = cursoNombre; }

    public String getProfesorId() { return profesorId; }
    public void setProfesorId(String profesorId) { this.profesorId = profesorId; }

    public String getProfesorNombre() { return profesorNombre; }
    public void setProfesorNombre(String profesorNombre) { this.profesorNombre = profesorNombre; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    public boolean isTieneCuposDisponibles() { return tieneCuposDisponibles; }
    public void setTieneCuposDisponibles(boolean tieneCuposDisponibles) { 
        this.tieneCuposDisponibles = tieneCuposDisponibles; 
    }
}