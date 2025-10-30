package com.arquitecture.matricula.application.service;

import com.arquitecture.matricula.application.port.in.RegisterFacultadUseCase;
import com.arquitecture.matricula.application.dto.command.RegisterFacultadCommand;
import com.arquitecture.matricula.application.dto.response.FacultadResponse;
import com.arquitecture.matricula.application.mapper.FacultadMapper;
import com.arquitecture.matricula.domain.model.Facultad;
import com.arquitecture.matricula.domain.repository.FacultadRepository;
import com.arquitecture.matricula.domain.repository.CarreraRepository;
import com.arquitecture.matricula.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
public class FacultadApplicationService implements RegisterFacultadUseCase {

    private final FacultadRepository facultadRepository;
    private final CarreraRepository carreraRepository;
    private final FacultadMapper facultadMapper;

    public FacultadApplicationService(FacultadRepository facultadRepository,
                                     CarreraRepository carreraRepository,
                                     FacultadMapper facultadMapper) {
        this.facultadRepository = facultadRepository;
        this.carreraRepository = carreraRepository;
        this.facultadMapper = facultadMapper;
    }

    @Override
    public FacultadResponse register(RegisterFacultadCommand command) {
        // Validar que no existe otra facultad con el mismo nombre
        if (facultadRepository.existsByNombre(command.getNombre())) {
            throw new RuntimeException("Ya existe una facultad con este nombre");
        }

        // Crear la facultad
        Facultad facultad = Facultad.create(
                command.getNombre(),
                command.getDescripcion(),
                command.getUbicacion(),
                command.getDecano()
        );

        // Guardar la facultad
        Facultad savedFacultad = facultadRepository.save(facultad);

        // Obtener información adicional para la respuesta
        int cantidadCarreras = carreraRepository.findByFacultad(savedFacultad.getId()).size();

        // Convertir a DTO de respuesta
        return facultadMapper.toResponse(savedFacultad, cantidadCarreras);
    }
}