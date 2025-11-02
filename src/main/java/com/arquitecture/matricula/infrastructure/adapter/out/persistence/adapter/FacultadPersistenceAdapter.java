package com.arquitecture.matricula.infrastructure.adapter.out.persistence.adapter;

import com.arquitecture.matricula.domain.model.Facultad;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;
import com.arquitecture.matricula.domain.repository.FacultadRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.FacultadEntity;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository.FacultadJpaRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper.FacultadJpaMapper;
import com.arquitecture.matricula.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class FacultadPersistenceAdapter implements FacultadRepository {

    private final FacultadJpaRepository jpaRepository;
    private final FacultadJpaMapper mapper;

    public FacultadPersistenceAdapter(FacultadJpaRepository jpaRepository, FacultadJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Facultad save(Facultad facultad) {
        FacultadEntity saved = jpaRepository.save(mapper.toEntity(facultad));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Facultad> findById(FacultadId id) {
        return jpaRepository.findById(id.toString()).map(mapper::toDomain);
    }

    @Override
    public Optional<Facultad> findByNombre(String nombre) {
        return jpaRepository.findByNombre(nombre).map(mapper::toDomain);
    }

    @Override
    public List<Facultad> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Facultad> findActive() {
        return jpaRepository.findByActivoTrue().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(FacultadId id) {
        jpaRepository.deleteById(id.toString());
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return jpaRepository.existsByNombre(nombre);
    }
}
