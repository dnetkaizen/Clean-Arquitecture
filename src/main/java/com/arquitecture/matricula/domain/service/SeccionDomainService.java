package com.arquitecture.matricula.domain.service;

import com.arquitecture.matricula.domain.exception.SeccionConflictException;
import com.arquitecture.matricula.domain.model.Seccion;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.domain.repository.SeccionRepository;
import com.arquitecture.matricula.shared.annotation.DomainService;

@DomainService
public class SeccionDomainService {

    private final SeccionRepository seccionRepository;

    public SeccionDomainService(SeccionRepository seccionRepository) {
        this.seccionRepository = seccionRepository;
    }

    public void validateUniqueOnCreate(Seccion seccion) {
        CursoId cursoId = seccion.getCursoId();
        String codigo = seccion.getCodigo();
        String periodo = seccion.getPeriodoAcademico();
        if (seccionRepository.existsByCursoCodigoPeriodo(cursoId, codigo, periodo)) {
            throw new SeccionConflictException("Ya existe una sección con el mismo curso, código y período");
        }
    }

    public void validateUniqueOnUpdate(Seccion seccion) {
        seccionRepository.findByCursoCodigoPeriodo(seccion.getCursoId(), seccion.getCodigo(), seccion.getPeriodoAcademico())
                .filter(existing -> !existing.getId().equals(seccion.getId()))
                .ifPresent(existing -> { throw new SeccionConflictException("Ya existe una sección con el mismo curso, código y período"); });
    }
}
