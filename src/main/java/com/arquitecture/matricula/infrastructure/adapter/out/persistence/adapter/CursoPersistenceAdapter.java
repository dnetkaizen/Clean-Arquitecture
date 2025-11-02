package com.arquitecture.matricula.infrastructure.adapter.out.persistence.adapter;

import com.arquitecture.matricula.domain.model.Curso;
import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.CodigoCurso;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.domain.repository.CursoRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.CursoEntity;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository.CursoJpaRepository;
import com.arquitecture.matricula.infrastructure.adapter.out.persistence.mapper.CursoJpaMapper;
import com.arquitecture.matricula.shared.annotation.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
public class CursoPersistenceAdapter implements CursoRepository {

    private final CursoJpaRepository jpaRepository;
    private final CursoJpaMapper mapper;

    public CursoPersistenceAdapter(CursoJpaRepository jpaRepository, CursoJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Curso save(Curso curso) {
        CursoEntity saved = jpaRepository.save(mapper.toEntity(curso));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Curso> findById(CursoId id) {
        return jpaRepository.findById(id.toString()).map(mapper::toDomain);
    }

    @Override
    public Optional<Curso> findByCodigo(CodigoCurso codigo) {
        return jpaRepository.findByCodigo(codigo.getValue()).map(mapper::toDomain);
    }

    @Override
    public List<Curso> findByCarrera(CarreraId carreraId) {
        return jpaRepository.findByCarreraId(carreraId.toString()).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Curso> findByNivelSemestre(int nivelSemestre) {
        return jpaRepository.findByNivelSemestre(nivelSemestre).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Curso> findByCarreraAndNivel(CarreraId carreraId, int nivelSemestre) {
        return jpaRepository.findByCarreraIdAndNivelSemestre(carreraId.toString(), nivelSemestre)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Curso> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Curso> findActive() {
        return jpaRepository.findByActivoTrue().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(CursoId id) {
        jpaRepository.deleteById(id.toString());
    }

    @Override
    public boolean existsByCodigo(CodigoCurso codigo) {
        return jpaRepository.existsByCodigo(codigo.getValue());
    }

    @Override
    public boolean existsByNombreAndCarrera(String nombre, CarreraId carreraId) {
        return jpaRepository.existsByNombreAndCarreraId(nombre, carreraId.toString());
    }
}
