package com.arquitecture.matricula.domain.service;

import com.arquitecture.matricula.domain.exception.CarreraConflictException;
import com.arquitecture.matricula.domain.model.Carrera;
import com.arquitecture.matricula.domain.repository.CarreraRepository;
import com.arquitecture.matricula.shared.annotation.DomainService;

@DomainService
public class CarreraDomainService {

    private final CarreraRepository carreraRepository;

    public CarreraDomainService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public void validateUniqueOnCreate(Carrera carrera) {
        String nombre = carrera.getNombre();
        if (carreraRepository.existsByNombre(nombre)) {
            throw new CarreraConflictException("Ya existe una carrera con el nombre: " + nombre);
        }
    }

    public void validateUniqueOnUpdate(Carrera carrera) {
        carreraRepository.findByNombre(carrera.getNombre())
                .filter(existing -> !existing.getId().equals(carrera.getId()))
                .ifPresent(existing -> { 
                    throw new CarreraConflictException("Ya existe una carrera con el nombre: " + carrera.getNombre()); 
                });
    }
}