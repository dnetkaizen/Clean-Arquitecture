package com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository;

import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.FacultadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultadJpaRepository extends JpaRepository<FacultadEntity, String> {
    Optional<FacultadEntity> findByNombre(String nombre);
    List<FacultadEntity> findByActivoTrue();
    boolean existsByNombre(String nombre);
}
