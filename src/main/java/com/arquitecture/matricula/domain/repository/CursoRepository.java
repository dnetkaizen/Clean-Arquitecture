package com.arquitecture.matricula.domain.repository;

import com.arquitecture.matricula.domain.model.Curso;
import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.CodigoCurso;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;

import java.util.List;
import java.util.Optional;

public interface CursoRepository {

    Curso save(Curso curso);

    Optional<Curso> findById(CursoId id);

    Optional<Curso> findByCodigo(CodigoCurso codigo);

    List<Curso> findByCarrera(CarreraId carreraId);

    List<Curso> findByNivelSemestre(int nivelSemestre);

    List<Curso> findByCarreraAndNivel(CarreraId carreraId, int nivelSemestre);

    List<Curso> findAll();

    List<Curso> findActive();

    void delete(CursoId id);

    boolean existsByCodigo(CodigoCurso codigo);

    boolean existsByNombreAndCarrera(String nombre, CarreraId carreraId);
}