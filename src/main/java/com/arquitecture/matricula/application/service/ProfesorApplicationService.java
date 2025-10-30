package com.arquitecture.matricula.application.service;

import com.arquitecture.matricula.application.port.in.RegisterProfesorUseCase;
import com.arquitecture.matricula.application.port.in.FindProfesoresByEspecialidadUseCase;
import com.arquitecture.matricula.application.dto.command.RegisterProfesorCommand;
import com.arquitecture.matricula.application.dto.query.FindProfesoresByEspecialidadQuery;
import com.arquitecture.matricula.application.dto.response.ProfesorResponse;
import com.arquitecture.matricula.application.mapper.ProfesorMapper;
import com.arquitecture.matricula.domain.model.Profesor;
import com.arquitecture.matricula.domain.model.valueobjects.Dni;
import com.arquitecture.matricula.domain.model.valueobjects.Email;
import com.arquitecture.matricula.domain.repository.ProfesorRepository;
import com.arquitecture.matricula.domain.repository.SeccionRepository;
import com.arquitecture.matricula.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class ProfesorApplicationService implements RegisterProfesorUseCase, FindProfesoresByEspecialidadUseCase {

    private final ProfesorRepository profesorRepository;
    private final SeccionRepository seccionRepository;
    private final ProfesorMapper profesorMapper;

    public ProfesorApplicationService(ProfesorRepository profesorRepository,
                                     SeccionRepository seccionRepository,
                                     ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.seccionRepository = seccionRepository;
        this.profesorMapper = profesorMapper;
    }

    @Override
    public ProfesorResponse register(RegisterProfesorCommand command) {
        // Crear Dni y Email - CORREGIDO: usar constructores directos
        Dni dni = new Dni(command.getDni());
        Email email = new Email(command.getEmail());

        // Validar que no existe otro profesor con el mismo DNI
        if (profesorRepository.existsByDni(dni)) {
            throw new RuntimeException("Ya existe un profesor con este DNI");
        }

        // Validar que no existe otro profesor con el mismo email
        if (profesorRepository.existsByEmail(email)) {
            throw new RuntimeException("Ya existe un profesor con este email");
        }

        // Crear el profesor
        Profesor profesor = Profesor.create(
                command.getNombre(),
                command.getApellido(),
                dni,    // Usar objeto Dni creado
                email,  // Usar objeto Email creado
                command.getTelefono(),
                command.getEspecialidad(),
                command.getTituloAcademico()
        );

        // Guardar el profesor
        Profesor savedProfesor = profesorRepository.save(profesor);

        // Obtener información adicional para la respuesta
        int cantidadSecciones = seccionRepository.findByProfesor(savedProfesor.getId()).size();

        // Convertir a DTO de respuesta
        return profesorMapper.toResponse(savedProfesor, cantidadSecciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfesorResponse> handle(FindProfesoresByEspecialidadQuery query) {
        List<Profesor> profesores = profesorRepository.findAll();

        return profesores.stream()
                .filter(profesor -> shouldIncludeProfesor(profesor, query.getEspecialidad(), 
                         query.getTituloAcademico(), query.getActivo()))
                .map(this::enrichProfesorResponse)
                .collect(Collectors.toList());
    }

    private boolean shouldIncludeProfesor(Profesor profesor, String especialidadFilter, 
                                         String tituloAcademicoFilter, Boolean activoFilter) {
        // Filtro por especialidad
        if (especialidadFilter != null && !especialidadFilter.isEmpty() &&
            !profesor.getEspecialidad().equals(especialidadFilter)) {
            return false;
        }
        
        // Filtro por título académico
        if (tituloAcademicoFilter != null && !tituloAcademicoFilter.isEmpty() &&
            !profesor.getTituloAcademico().equals(tituloAcademicoFilter)) {
            return false;
        }
        
        // Filtro por estado activo
        if (activoFilter != null && profesor.isActivo() != activoFilter) {
            return false;
        }
        
        return true;
    }

    private ProfesorResponse enrichProfesorResponse(Profesor profesor) {
        int cantidadSecciones = seccionRepository.findByProfesor(profesor.getId()).size();
        return profesorMapper.toResponse(profesor, cantidadSecciones);
    }
}