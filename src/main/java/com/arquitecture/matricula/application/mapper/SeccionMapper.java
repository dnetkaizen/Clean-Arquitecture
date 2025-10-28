package com.arquitecture.matricula.application.mapper;

import com.arquitecture.matricula.application.dto.response.SeccionResponse;
import com.arquitecture.matricula.domain.model.Seccion;
import org.springframework.stereotype.Component;

@Component
public class SeccionMapper {

    public SeccionResponse toResponse(Seccion seccion,
                                      String cursoNombre,
                                      String profesorNombre,
                                      int estudiantesInscritos) {
        SeccionResponse response = new SeccionResponse();
        response.setId(seccion.getId().toString());
        response.setCodigo(seccion.getCodigo());
        response.setCapacidadMaxima(seccion.getCapacidadMaxima());
        response.setEstudiantesInscritos(estudiantesInscritos);
        response.setAula(seccion.getAula());
        response.setHorario(seccion.getHorario());
        response.setDias(seccion.getDias());
        response.setPeriodoAcademico(seccion.getPeriodoAcademico());
        response.setFechaInicio(seccion.getFechaInicio());
        response.setFechaFin(seccion.getFechaFin());
        response.setCursoId(seccion.getCursoId().toString());
        response.setCursoNombre(cursoNombre);
        response.setProfesorId(seccion.getProfesorId().toString());
        response.setProfesorNombre(profesorNombre);
        response.setActiva(seccion.isActivo());
        // tieneCuposDisponibles se actualiza automáticamente al setear estudiantes
        return response;
    }
}
