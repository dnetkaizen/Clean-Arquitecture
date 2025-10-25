package com.arquitecture.matricula.domain.repository;

import com.arquitecture.matricula.domain.model.Carrera;
import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;

import java.util.List;
import java.util.Optional;

public interface CarreraRepository {

    Carrera save(Carrera carrera);

    Optional<Carrera> findById(CarreraId id);

    Optional<Carrera> findByNombre(String nombre);

    List<Carrera> findByFacultad(FacultadId facultadId);

    List<Carrera> findAll();

    List<Carrera> findActive();

    void delete(CarreraId id);

    boolean existsByNombre(String nombre);
}