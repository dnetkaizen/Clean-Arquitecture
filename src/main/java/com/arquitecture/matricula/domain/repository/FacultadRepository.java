package com.arquitecture.matricula.domain.repository;

import com.arquitecture.matricula.domain.model.Facultad;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;

import java.util.List;
import java.util.Optional;

public interface FacultadRepository {

    Facultad save(Facultad facultad);

    Optional<Facultad> findById(FacultadId id);

    Optional<Facultad> findByNombre(String nombre);

    List<Facultad> findAll();

    List<Facultad> findActive();

    void delete(FacultadId id);

    boolean existsByNombre(String nombre);
}