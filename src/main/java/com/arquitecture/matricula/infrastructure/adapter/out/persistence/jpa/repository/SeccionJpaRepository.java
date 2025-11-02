package com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository;

import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.SeccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeccionJpaRepository extends JpaRepository<SeccionEntity, String> {
    Optional<SeccionEntity> findByCursoIdAndCodigoAndPeriodoAcademico(String cursoId, String codigo, String periodoAcademico);
    List<SeccionEntity> findByCursoId(String cursoId);
    List<SeccionEntity> findByProfesorId(String profesorId);
    List<SeccionEntity> findByActivoTrue();
    boolean existsByCursoIdAndCodigoAndPeriodoAcademico(String cursoId, String codigo, String periodoAcademico);
}
