package com.arquitecture.matricula.application.mapper;

import com.arquitecture.matricula.application.dto.response.CursoResponse;
import com.arquitecture.matricula.domain.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso, String carreraNombre, int cantidadSecciones) {
        CursoResponse response = new CursoResponse();
        response.setId(curso.getId().toString());
        response.setCodigo(curso.getCodigo().getValue());
        response.setNombre(curso.getNombre());
        response.setDescripcion(curso.getDescripcion());
        response.setCreditos(curso.getCreditos());
        response.setNivelSemestre(curso.getNivelSemestre());
        response.setCarreraId(curso.getCarreraId().toString());
        response.setCarreraNombre(carreraNombre);
        response.setCantidadSecciones(cantidadSecciones);
        response.setActivo(curso.isActivo());
        return response;
    }
}