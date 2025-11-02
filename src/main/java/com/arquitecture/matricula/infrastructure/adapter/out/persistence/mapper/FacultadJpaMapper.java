package com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper;

import com.arquitecture.matricula.domain.model.Facultad;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.FacultadEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FacultadJpaMapper {

    public FacultadEntity toEntity(Facultad domain) {
        FacultadEntity e = new FacultadEntity();
        e.setId(domain.getId().toString());
        e.setNombre(domain.getNombre());
        e.setDescripcion(domain.getDescripcion());
        e.setUbicacion(domain.getUbicacion());
        e.setDecano(domain.getDecano());
        e.setFechaRegistro(domain.getFechaRegistro());
        e.setActivo(domain.isActivo());
        return e;
    }

    public Facultad toDomain(FacultadEntity e) {
        return new Facultad(
                FacultadId.of(e.getId()),
                e.getNombre(),
                e.getDescripcion(),
                e.getUbicacion(),
                e.getDecano(),
                e.getFechaRegistro() != null ? e.getFechaRegistro() : Instant.now(),
                e.isActivo()
        );
    }
}
