package com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper;

import com.arquitecture.matricula.domain.model.Profesor;
import com.arquitecture.matricula.domain.model.valueobjects.Dni;
import com.arquitecture.matricula.domain.model.valueobjects.Email;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.ProfesorEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
 
@Component
public class ProfesorJpaMapper {

    public ProfesorEntity toEntity(Profesor domain) {
        ProfesorEntity e = new ProfesorEntity();
        e.setId(domain.getId().toString());
        e.setNombre(domain.getNombre());
        e.setApellido(domain.getApellido());
        e.setDni(domain.getDni().toString());
        e.setEmail(domain.getEmail().getValue());
        e.setTelefono(domain.getTelefono());
        e.setEspecialidad(domain.getEspecialidad());
        e.setTituloAcademico(domain.getTituloAcademico());
        e.setFechaRegistro(domain.getFechaRegistro());
        e.setActivo(domain.isActivo());
        return e;
    }

    public Profesor toDomain(ProfesorEntity e) {
        return new Profesor(
                ProfesorId.of(e.getId()),
                e.getNombre(),
                e.getApellido(),
                new Dni(e.getDni()),
                new Email(e.getEmail()),
                e.getTelefono(),
                e.getEspecialidad(),
                e.getTituloAcademico(),
                e.getFechaRegistro() != null ? e.getFechaRegistro() : Instant.now(),
                e.isActivo()
        );
    }
}
