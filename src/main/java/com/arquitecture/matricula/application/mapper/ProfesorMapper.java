package com.arquitecture.matricula.application.mapper;

import com.arquitecture.matricula.application.dto.response.ProfesorResponse;
import com.arquitecture.matricula.domain.model.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public ProfesorResponse toResponse(Profesor profesor, int cantidadSecciones) {
        ProfesorResponse response = new ProfesorResponse();
        response.setId(profesor.getId().toString());
        response.setNombre(profesor.getNombre());
        response.setApellido(profesor.getApellido());
        response.setDni(profesor.getDni().toString());
        response.setEmail(profesor.getEmail().getValue());
        response.setTelefono(profesor.getTelefono());
        response.setEspecialidad(profesor.getEspecialidad());
        response.setTituloAcademico(profesor.getTituloAcademico());
        response.setCantidadSecciones(cantidadSecciones);
        response.setActivo(profesor.isActivo());
        return response;
    }
}
