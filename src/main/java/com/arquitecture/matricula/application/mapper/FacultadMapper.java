package com.arquitecture.matricula.application.mapper;

import com.arquitecture.matricula.application.dto.response.FacultadResponse;
import com.arquitecture.matricula.domain.model.Facultad;
import org.springframework.stereotype.Component;

@Component
public class FacultadMapper {

    public FacultadResponse toResponse(Facultad facultad, int cantidadCarreras) {
        FacultadResponse response = new FacultadResponse();
        response.setId(facultad.getId().toString());
        response.setNombre(facultad.getNombre());
        response.setDescripcion(facultad.getDescripcion());
        response.setUbicacion(facultad.getUbicacion());
        response.setDecano(facultad.getDecano());
        response.setCantidadCarreras(cantidadCarreras);
        response.setActiva(facultad.isActivo());
        return response;
    }
}
