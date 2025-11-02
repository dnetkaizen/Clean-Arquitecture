package com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper;

import com.arquitecture.matricula.domain.model.Seccion;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;
import com.arquitecture.matricula.domain.model.valueobjects.SeccionId;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.SeccionEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SeccionJpaMapper {

    public SeccionEntity toEntity(Seccion domain) {
        SeccionEntity e = new SeccionEntity();
        e.setId(domain.getId().toString());
        e.setCursoId(domain.getCursoId().toString());
        e.setProfesorId(domain.getProfesorId().toString());
        e.setCodigo(domain.getCodigo());
        e.setCapacidadMaxima(domain.getCapacidadMaxima());
        e.setAula(domain.getAula());
        e.setHorario(domain.getHorario());
        e.setDias(domain.getDias());
        e.setPeriodoAcademico(domain.getPeriodoAcademico());
        e.setFechaInicio(domain.getFechaInicio());
        e.setFechaFin(domain.getFechaFin());
        e.setFechaRegistro(domain.getFechaRegistro());
        e.setActivo(domain.isActivo());
        return e;
    }

    public Seccion toDomain(SeccionEntity e) {
        return new Seccion(
                SeccionId.of(e.getId()),
                CursoId.of(e.getCursoId()),
                ProfesorId.of(e.getProfesorId()),
                e.getCodigo(),
                e.getCapacidadMaxima(),
                e.getAula(),
                e.getHorario(),
                e.getDias(),
                e.getPeriodoAcademico(),
                e.getFechaInicio(),
                e.getFechaFin(),
                e.getFechaRegistro() != null ? e.getFechaRegistro() : Instant.now(),
                e.isActivo()
        );
    }
}
