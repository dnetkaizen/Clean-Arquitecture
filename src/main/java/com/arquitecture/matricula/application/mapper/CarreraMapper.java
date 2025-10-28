package com.arquitecture.matricula.application.mapper;

import com.arquitecture.matricula.application.dto.response.CarreraResponse;
import com.arquitecture.matricula.domain.model.Carrera;
import org.springframework.stereotype.Component;

@Component
public class CarreraMapper {

    public CarreraResponse toResponse(Carrera carrera, String facultadNombre, int cantidadCursos) {
        CarreraResponse response = new CarreraResponse();
        response.setId(carrera.getId().toString());
        response.setNombre(carrera.getNombre());
        response.setDescripcion(carrera.getDescripcion());
        response.setDuracionSemestres(carrera.getDuracionSemestres());
        response.setTituloOtorgado(carrera.getTituloOtorgado());
        response.setFacultadId(carrera.getFacultadId().toString());
        response.setFacultadNombre(facultadNombre);
        response.setCantidadCursos(cantidadCursos);
        response.setActiva(carrera.isActivo());
        return response;
    }
}
