package com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository;

import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.CarreraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarreraJpaRepository extends JpaRepository<CarreraEntity, String> {
    Optional<CarreraEntity> findByNombre(String nombre);
    List<CarreraEntity> findByFacultadId(String facultadId);
    List<CarreraEntity> findByActivoTrue();
    boolean existsByNombre(String nombre);
}
