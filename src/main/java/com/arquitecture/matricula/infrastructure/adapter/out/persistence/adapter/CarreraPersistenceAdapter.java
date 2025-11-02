package com.arquitecture.matricula.infrastructure.adapter.out.persistence.adapter;

import com.arquitecture.matricula.domain.model.Carrera;
import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;
import com.arquitecture.matricula.domain.repository.CarreraRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.CarreraEntity;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository.CarreraJpaRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper.CarreraJpaMapper;
import com.arquitecture.matricula.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class CarreraPersistenceAdapter implements CarreraRepository {

    private final CarreraJpaRepository jpaRepository;
    private final CarreraJpaMapper mapper;

    public CarreraPersistenceAdapter(CarreraJpaRepository jpaRepository, CarreraJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Carrera save(Carrera carrera) {
        CarreraEntity saved = jpaRepository.save(mapper.toEntity(carrera));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Carrera> findById(CarreraId id) {
        return jpaRepository.findById(id.toString()).map(mapper::toDomain);
    }

    @Override
    public Optional<Carrera> findByNombre(String nombre) {
        return jpaRepository.findByNombre(nombre).map(mapper::toDomain);
    }

    @Override
    public List<Carrera> findByFacultad(FacultadId facultadId) {
        return jpaRepository.findByFacultadId(facultadId.toString()).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Carrera> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Carrera> findActive() {
        return jpaRepository.findByActivoTrue().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(CarreraId id) {
        jpaRepository.deleteById(id.toString());
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return jpaRepository.existsByNombre(nombre);
    }
}
