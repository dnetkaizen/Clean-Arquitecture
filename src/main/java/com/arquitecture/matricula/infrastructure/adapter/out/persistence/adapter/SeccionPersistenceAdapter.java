package com.arquitecture.matricula.infrastructure.adapter.out.persistence.adapter;

import com.arquitecture.matricula.domain.model.Seccion;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;
import com.arquitecture.matricula.domain.model.valueobjects.SeccionId;
import com.arquitecture.matricula.domain.repository.SeccionRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.SeccionEntity;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository.SeccionJpaRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper.SeccionJpaMapper;
import com.arquitecture.matricula.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class SeccionPersistenceAdapter implements SeccionRepository {

    private final SeccionJpaRepository jpaRepository;
    private final SeccionJpaMapper mapper;

    public SeccionPersistenceAdapter(SeccionJpaRepository jpaRepository, SeccionJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Seccion save(Seccion seccion) {
        SeccionEntity saved = jpaRepository.save(mapper.toEntity(seccion));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Seccion> findById(SeccionId id) {
        return jpaRepository.findById(id.toString()).map(mapper::toDomain);
    }

    @Override
    public Optional<Seccion> findByCursoCodigoPeriodo(CursoId cursoId, String codigo, String periodoAcademico) {
        return jpaRepository.findByCursoIdAndCodigoAndPeriodoAcademico(cursoId.toString(), codigo, periodoAcademico)
                .map(mapper::toDomain);
    }

    @Override
    public List<Seccion> findByCurso(CursoId cursoId) {
        return jpaRepository.findByCursoId(cursoId.toString()).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Seccion> findByProfesor(ProfesorId profesorId) {
        return jpaRepository.findByProfesorId(profesorId.toString()).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Seccion> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Seccion> findActive() {
        return jpaRepository.findByActivoTrue().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(SeccionId id) {
        jpaRepository.deleteById(id.toString());
    }

    @Override
    public boolean existsByCursoCodigoPeriodo(CursoId cursoId, String codigo, String periodoAcademico) {
        return jpaRepository.existsByCursoIdAndCodigoAndPeriodoAcademico(cursoId.toString(), codigo, periodoAcademico);
    }
}
