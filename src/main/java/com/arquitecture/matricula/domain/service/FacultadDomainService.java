package com.arquitecture.matricula.domain.service;

import com.arquitecture.matricula.domain.exception.FacultadConflictException;
import com.arquitecture.matricula.domain.model.Facultad;
import com.arquitecture.matricula.domain.repository.FacultadRepository;
import com.arquitecture.matricula.shared.annotation.DomainService;

@DomainService
public class FacultadDomainService {

    private final FacultadRepository facultadRepository;

    public FacultadDomainService(FacultadRepository facultadRepository) {
        this.facultadRepository = facultadRepository;
    }

    public void validateUniqueOnCreate(Facultad facultad) {
        String nombre = facultad.getNombre();
        if (facultadRepository.existsByNombre(nombre)) {
            throw new FacultadConflictException("Ya existe una facultad con el nombre: " + nombre);
        }
    }

    public void validateUniqueOnUpdate(Facultad facultad) {
        facultadRepository.findByNombre(facultad.getNombre())
                .filter(existing -> !existing.getId().equals(facultad.getId()))
                .ifPresent(existing -> { 
                    throw new FacultadConflictException("Ya existe una facultad con el nombre: " + facultad.getNombre()); 
                });
    }
}