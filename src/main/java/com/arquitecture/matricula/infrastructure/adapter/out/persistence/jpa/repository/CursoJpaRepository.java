package com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository;

import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoJpaRepository extends JpaRepository<CursoEntity, String> {
    Optional<CursoEntity> findByCodigo(String codigo);
    List<CursoEntity> findByCarreraId(String carreraId);
    List<CursoEntity> findByNivelSemestre(int nivelSemestre);
    List<CursoEntity> findByCarreraIdAndNivelSemestre(String carreraId, int nivelSemestre);
    List<CursoEntity> findByActivoTrue();
    boolean existsByCodigo(String codigo);
    boolean existsByNombreAndCarreraId(String nombre, String carreraId);
}
