package com.arquitecture.matricula.domain.service;

import com.arquitecture.matricula.domain.exception.ProfesorConflictException;
import com.arquitecture.matricula.domain.model.Profesor;
import com.arquitecture.matricula.domain.model.valueobjects.Dni;
import com.arquitecture.matricula.domain.model.valueobjects.Email;
import com.arquitecture.matricula.domain.repository.ProfesorRepository;
import com.arquitecture.matricula.shared.annotation.DomainService;

@DomainService
public class ProfesorDomainService {

    private final ProfesorRepository profesorRepository;

    public ProfesorDomainService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public void validateUniqueOnCreate(Profesor profesor) {
        Email email = profesor.getEmail();
        Dni dni = profesor.getDni();
        if (profesorRepository.existsByEmail(email)) {
            throw new ProfesorConflictException("Email ya está registrado: " + email.getValue());
        }
        if (profesorRepository.existsByDni(dni)) {
            throw new ProfesorConflictException("DNI ya está registrado: " + dni.getValue());
        }
    }

    public void validateUniqueOnUpdate(Profesor profesor) {
        profesorRepository.findByEmail(profesor.getEmail())
                .filter(existing -> !existing.getId().equals(profesor.getId()))
                .ifPresent(existing -> { throw new ProfesorConflictException("Email ya está registrado: " + profesor.getEmail().getValue()); });

        profesorRepository.findByDni(profesor.getDni())
                .filter(existing -> !existing.getId().equals(profesor.getId()))
                .ifPresent(existing -> { throw new ProfesorConflictException("DNI ya está registrado: " + profesor.getDni().getValue()); });
    }
}
