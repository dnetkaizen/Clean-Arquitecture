package com.arquitecture.matricula.domain.service;

import com.arquitecture.matricula.domain.exception.CursoConflictException;
import com.arquitecture.matricula.domain.model.Curso;
import com.arquitecture.matricula.domain.repository.CursoRepository;
import com.arquitecture.matricula.shared.annotation.DomainService;

@DomainService
public class CursoDomainService {

    private final CursoRepository cursoRepository;

    public CursoDomainService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public void validateUniqueOnCreate(Curso curso) {
        // Validar que el código sea único
        if (cursoRepository.existsByCodigo(curso.getCodigo())) {
            throw new CursoConflictException("Ya existe un curso con el código: " + curso.getCodigo().getValue());
        }

        // Validar que no exista otro curso con el mismo nombre en la misma carrera
        if (cursoRepository.existsByNombreAndCarrera(curso.getNombre(), curso.getCarreraId())) {
            throw new CursoConflictException("Ya existe un curso con el nombre '" + curso.getNombre() + 
                                           "' en esta carrera");
        }
    }

    public void validateUniqueOnUpdate(Curso curso) {
        // Validar que el código sea único (excluyendo el curso actual)
        cursoRepository.findByCodigo(curso.getCodigo())
                .filter(existing -> !existing.getId().equals(curso.getId()))
                .ifPresent(existing -> { 
                    throw new CursoConflictException("Ya existe un curso con el código: " + curso.getCodigo().getValue()); 
                });

        // Validar que no exista otro curso con el mismo nombre en la misma carrera (excluyendo el curso actual)
        // Nota: Esto requeriría un método adicional en el repository para buscar por nombre y carrera excluyendo un ID
    }
}