package com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.repository;

import com.arquitecture.matricula.infrastructure.adapter.out.persistence.jpa.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesorJpaRepository extends JpaRepository<ProfesorEntity, String> {
    Optional<ProfesorEntity> findByEmail(String email);
    Optional<ProfesorEntity> findByDni(String dni);
    List<ProfesorEntity> findByActivoTrue();
}
