package com.arquitecture.matricula.domain.repository;

import com.arquitecture.matricula.domain.model.Seccion;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;
import com.arquitecture.matricula.domain.model.valueobjects.SeccionId;

import java.util.List;
import java.util.Optional;

public interface SeccionRepository {

    Seccion save(Seccion seccion);

    Optional<Seccion> findById(SeccionId id);

    Optional<Seccion> findByCursoCodigoPeriodo(CursoId cursoId, String codigo, String periodoAcademico);

    List<Seccion> findByCurso(CursoId cursoId);

    List<Seccion> findByProfesor(ProfesorId profesorId);

    List<Seccion> findAll();

    List<Seccion> findActive();

    void delete(SeccionId id);

    boolean existsByCursoCodigoPeriodo(CursoId cursoId, String codigo, String periodoAcademico);
}
