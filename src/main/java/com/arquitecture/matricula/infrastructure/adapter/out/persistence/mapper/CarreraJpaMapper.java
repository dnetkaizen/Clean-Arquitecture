package com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper;

import com.arquitecture.matricula.domain.model.Carrera;
import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.CarreraEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CarreraJpaMapper {

    public CarreraEntity toEntity(Carrera domain) {
        CarreraEntity e = new CarreraEntity();
        e.setId(domain.getId().toString());
        e.setFacultadId(domain.getFacultadId().toString());
        e.setNombre(domain.getNombre());
        e.setDescripcion(domain.getDescripcion());
        e.setDuracionSemestres(domain.getDuracionSemestres());
        e.setTituloOtorgado(domain.getTituloOtorgado());
        e.setFechaRegistro(domain.getFechaRegistro());
        e.setActivo(domain.isActivo());
        return e;
    }

    public Carrera toDomain(CarreraEntity e) {
        return new Carrera(
                CarreraId.of(e.getId()),
                FacultadId.of(e.getFacultadId()),
                e.getNombre(),
                e.getDescripcion(),
                e.getDuracionSemestres(),
                e.getTituloOtorgado(),
                e.getFechaRegistro() != null ? e.getFechaRegistro() : Instant.now(),
                e.isActivo()
        );
    }
}
