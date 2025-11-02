package com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper;

import com.arquitecture.matricula.domain.model.Curso;
import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.CodigoCurso;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.CursoEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CursoJpaMapper {

    public CursoEntity toEntity(Curso domain) {
        CursoEntity e = new CursoEntity();
        e.setId(domain.getId().toString());
        e.setCarreraId(domain.getCarreraId().toString());
        e.setCodigo(domain.getCodigo().getValue());
        e.setNombre(domain.getNombre());
        e.setDescripcion(domain.getDescripcion());
        e.setCreditos(domain.getCreditos());
        e.setNivelSemestre(domain.getNivelSemestre());
        e.setFechaRegistro(domain.getFechaRegistro());
        e.setActivo(domain.isActivo());
        return e;
    }

    public Curso toDomain(CursoEntity e) {
        return new Curso(
                CursoId.of(e.getId()),
                CarreraId.of(e.getCarreraId()),
                new CodigoCurso(e.getCodigo()),
                e.getNombre(),
                e.getDescripcion(),
                e.getCreditos(),
                e.getNivelSemestre(),
                e.getFechaRegistro() != null ? e.getFechaRegistro() : Instant.now(),
                e.isActivo()
        );
    }
}
