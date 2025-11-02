package com.arquitecture.matricula.infrastructure.adapter.out.persistence.adapter;

import com.arquitecture.matricula.domain.model.Profesor;
import com.arquitecture.matricula.domain.model.valueobjects.Dni;
import com.arquitecture.matricula.domain.model.valueobjects.Email;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;
import com.arquitecture.matricula.domain.repository.ProfesorRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.ProfesorEntity;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository.ProfesorJpaRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper.ProfesorJpaMapper;
import com.arquitecture.matricula.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class ProfesorPersistenceAdapter implements ProfesorRepository {

    private final ProfesorJpaRepository jpaRepository;
    private final ProfesorJpaMapper mapper;

    public ProfesorPersistenceAdapter(ProfesorJpaRepository jpaRepository, ProfesorJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Profesor save(Profesor profesor) {
        ProfesorEntity saved = jpaRepository.save(mapper.toEntity(profesor));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Profesor> findById(ProfesorId id) {
        return jpaRepository.findById(id.toString()).map(mapper::toDomain);
    }

    @Override
    public Optional<Profesor> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue()).map(mapper::toDomain);
    }

    @Override
    public Optional<Profesor> findByDni(Dni dni) {
        return jpaRepository.findByDni(dni.toString()).map(mapper::toDomain);
    }

    @Override
    public List<Profesor> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Profesor> findActive() {
        return jpaRepository.findByActivoTrue().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(ProfesorId id) {
        jpaRepository.deleteById(id.toString());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue()).isPresent();
    }

    @Override
    public boolean existsByDni(Dni dni) {
        return jpaRepository.findByDni(dni.toString()).isPresent();
    }
}
