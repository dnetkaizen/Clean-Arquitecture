package com.arquitecture.matricula.application.service;

import com.arquitecture.matricula.application.port.in.RegisterCarreraUseCase;
import com.arquitecture.matricula.application.port.in.FindCarrerasByFacultadUseCase;
import com.arquitecture.matricula.application.dto.command.RegisterCarreraCommand;
import com.arquitecture.matricula.application.dto.query.FindCarrerasByFacultadQuery;
import com.arquitecture.matricula.application.dto.response.CarreraResponse;
import com.arquitecture.matricula.application.mapper.CarreraMapper;
import com.arquitecture.matricula.domain.model.Carrera;
import com.arquitecture.matricula.domain.model.Facultad;
import com.arquitecture.matricula.domain.model.valueobjects.FacultadId;
import com.arquitecture.matricula.domain.repository.CarreraRepository;
import com.arquitecture.matricula.domain.repository.FacultadRepository;
import com.arquitecture.matricula.domain.repository.CursoRepository;
import com.arquitecture.matricula.domain.service.CarreraDomainService;
import com.arquitecture.matricula.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class CarreraApplicationService implements RegisterCarreraUseCase, FindCarrerasByFacultadUseCase {

    private final CarreraRepository carreraRepository;
    private final FacultadRepository facultadRepository;
    private final CursoRepository cursoRepository;
    private final CarreraDomainService carreraDomainService;
    private final CarreraMapper carreraMapper;

    public CarreraApplicationService(CarreraRepository carreraRepository,
                                    FacultadRepository facultadRepository,
                                    CursoRepository cursoRepository,
                                    CarreraDomainService carreraDomainService,
                                    CarreraMapper carreraMapper) {
        this.carreraRepository = carreraRepository;
        this.facultadRepository = facultadRepository;
        this.cursoRepository = cursoRepository;
        this.carreraDomainService = carreraDomainService;
        this.carreraMapper = carreraMapper;
    }

    @Override
    public CarreraResponse register(RegisterCarreraCommand command) {
        // Validar que la facultad existe
        Facultad facultad = facultadRepository.findById(FacultadId.of(command.getFacultadId()))
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada"));

        // Crear la carrera
        Carrera carrera = Carrera.create(
                FacultadId.of(command.getFacultadId()),
                command.getNombre(),
                command.getDescripcion(),
                command.getDuracionSemestres(),
                command.getTituloOtorgado()
        );

        // Validar unicidad usando el Domain Service
        carreraDomainService.validateUniqueOnCreate(carrera);

        // Guardar la carrera
        Carrera savedCarrera = carreraRepository.save(carrera);

        // Obtener información adicional para la respuesta
        int cantidadCursos = cursoRepository.findByCarrera(savedCarrera.getId()).size();

        // Convertir a DTO de respuesta
        return carreraMapper.toResponse(savedCarrera, facultad.getNombre(), cantidadCursos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarreraResponse> handle(FindCarrerasByFacultadQuery query) {
        List<Carrera> carreras = carreraRepository.findByFacultad(FacultadId.of(query.getFacultadId()));

        return carreras.stream()
                .filter(carrera -> shouldIncludeCarrera(carrera, query.getActiva()))
                .map(this::enrichCarreraResponse)
                .collect(Collectors.toList());
    }

    private boolean shouldIncludeCarrera(Carrera carrera, Boolean activaFilter) {
        if (activaFilter == null) {
            // Si no se especifica filtro, incluir todas
            return true;
        }
        // Si se especifica filtro, incluir solo las que coincidan
        return carrera.isActivo() == activaFilter;
    }

    private CarreraResponse enrichCarreraResponse(Carrera carrera) {
        Facultad facultad = facultadRepository.findById(carrera.getFacultadId())
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada"));

        int cantidadCursos = cursoRepository.findByCarrera(carrera.getId()).size();

        return carreraMapper.toResponse(carrera, facultad.getNombre(), cantidadCursos);
    }
}