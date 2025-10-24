package com.arquitecture.matricula.domain.repository;

import com.arquitecture.matricula.domain.model.Profesor;
import com.arquitecture.matricula.domain.model.valueobjects.Email;
import com.arquitecture.matricula.domain.model.valueobjects.Dni;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository {

    Profesor save(Profesor profesor);

    Optional<Profesor> findById(ProfesorId id);

    Optional<Profesor> findByEmail(Email email);

    Optional<Profesor> findByDni(Dni dni);

    List<Profesor> findAll();

    List<Profesor> findActive();

    void delete(ProfesorId id);

    boolean existsByEmail(Email email);

    boolean existsByDni(Dni dni);
}
