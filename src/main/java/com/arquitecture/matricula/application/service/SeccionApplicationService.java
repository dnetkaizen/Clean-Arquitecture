package com.arquitecture.matricula.application.service;

import com.arquitecture.matricula.application.port.in.CreateSeccionUseCase;
import com.arquitecture.matricula.application.port.in.FindSeccionesByCursoUseCase;
import com.arquitecture.matricula.application.port.in.FindSeccionesDisponiblesUseCase;
import com.arquitecture.matricula.application.port.out.EmailNotificationPort;
import com.arquitecture.matricula.application.dto.command.CreateSeccionCommand;
import com.arquitecture.matricula.application.dto.query.FindSeccionesByCursoQuery;
import com.arquitecture.matricula.application.dto.query.FindSeccionesDisponiblesQuery;
import com.arquitecture.matricula.application.dto.response.SeccionResponse;
import com.arquitecture.matricula.application.mapper.SeccionMapper;
import com.arquitecture.matricula.domain.model.Seccion;
import com.arquitecture.matricula.domain.model.Curso;
import com.arquitecture.matricula.domain.model.Profesor;
import com.arquitecture.matricula.domain.model.valueobjects.CursoId;
import com.arquitecture.matricula.domain.model.valueobjects.ProfesorId;
import com.arquitecture.matricula.domain.repository.SeccionRepository;
import com.arquitecture.matricula.domain.repository.CursoRepository;
import com.arquitecture.matricula.domain.repository.ProfesorRepository;
import com.arquitecture.matricula.domain.service.SeccionDomainService;
import com.arquitecture.matricula.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class SeccionApplicationService implements CreateSeccionUseCase, FindSeccionesByCursoUseCase, FindSeccionesDisponiblesUseCase {

    private final SeccionRepository seccionRepository;
    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;
    private final SeccionDomainService seccionDomainService;
    private final EmailNotificationPort emailNotificationPort;
    private final SeccionMapper seccionMapper;

    public SeccionApplicationService(SeccionRepository seccionRepository,
                                    CursoRepository cursoRepository,
                                    ProfesorRepository profesorRepository,
                                    SeccionDomainService seccionDomainService,
                                    EmailNotificationPort emailNotificationPort,
                                    SeccionMapper seccionMapper) {
        this.seccionRepository = seccionRepository;
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
        this.seccionDomainService = seccionDomainService;
        this.emailNotificationPort = emailNotificationPort;
        this.seccionMapper = seccionMapper;
    }

    @Override
    public SeccionResponse create(CreateSeccionCommand command) {
        // Validar que el curso existe
        Curso curso = cursoRepository.findById(CursoId.of(command.getCursoId()))
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Validar que el profesor existe
        Profesor profesor = profesorRepository.findById(ProfesorId.of(command.getProfesorId()))
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        // Validar que no existe otra sección con el mismo código en el mismo período
        if (seccionRepository.existsByCursoCodigoPeriodo(curso.getId(), command.getCodigo(), command.getPeriodoAcademico())) {
            throw new RuntimeException("Ya existe una sección con este código en el período académico");
        }

        // Crear la sección - CORREGIDO: usar el orden correcto de parámetros
        Seccion seccion = Seccion.create(
                curso.getId(),              // Primero cursoId
                profesor.getId(),           // Luego profesorId
                command.getCodigo(),        // codigo
                command.getCapacidadMaxima(), // capacidadMaxima
                command.getAula(),          // aula
                command.getHorario(),       // horario
                command.getDias(),          // dias
                command.getPeriodoAcademico(), // periodoAcademico
                command.getFechaInicio(),   // fechaInicio
                command.getFechaFin()       // fechaFin
        );

        // Validar disponibilidad del aula y horario (si tienes estos métodos)
        // seccionDomainService.validateDisponibilidadAula(seccion);
        // seccionDomainService.validateDisponibilidadProfesor(seccion);

        // Guardar la sección
        Seccion savedSeccion = seccionRepository.save(seccion);

        // Enviar notificación al profesor
        emailNotificationPort.sendSeccionCreated(
                profesor.getEmail().getValue(),
                curso.getNombre(),
                savedSeccion.getCodigo(),
                savedSeccion.getPeriodoAcademico()
        );

        // Convertir a DTO de respuesta
        return enrichSeccionResponse(savedSeccion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeccionResponse> handle(FindSeccionesByCursoQuery query) {
        List<Seccion> secciones = seccionRepository.findByCurso(CursoId.of(query.getCursoId()));

        return secciones.stream()
                .filter(seccion -> shouldIncludeSeccion(seccion, query.getPeriodoAcademico(), 
                         query.getProfesorId(), query.getActiva()))
                .map(this::enrichSeccionResponse)
                .collect(Collectors.toList());
    }

    private boolean shouldIncludeSeccion(Seccion seccion, String periodoAcademicoFilter, 
                                        String profesorIdFilter, Boolean activaFilter) {
        // Filtro por período académico
        if (periodoAcademicoFilter != null && !periodoAcademicoFilter.isEmpty() &&
            !seccion.getPeriodoAcademico().equals(periodoAcademicoFilter)) {
            return false;
        }
        
        // Filtro por profesor
        if (profesorIdFilter != null && !profesorIdFilter.isEmpty() &&
            !seccion.getProfesorId().toString().equals(profesorIdFilter)) {
            return false;
        }
        
        // Filtro por estado activo
        if (activaFilter != null && seccion.isActivo() != activaFilter) {
            return false;
        }
        
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeccionResponse> handle(FindSeccionesDisponiblesQuery query) {
        // Necesitarías agregar un método al repository para buscar por período
        // Por ahora, filtramos manualmente
        List<Seccion> secciones = seccionRepository.findAll().stream()
                .filter(seccion -> seccion.getPeriodoAcademico().equals(query.getPeriodoAcademico()))
                .collect(Collectors.toList());

        return secciones.stream()
                .filter(Seccion::isActivo)
                .filter(seccion -> seccion.tieneCuposDisponibles(getEstudiantesInscritos(seccion))) // CORREGIDO
                .filter(seccion -> query.getCursoId() == null || 
                         seccion.getCursoId().toString().equals(query.getCursoId()))
                .filter(seccion -> query.getCapacidadMinima() == null || 
                         seccion.getCapacidadMaxima() >= query.getCapacidadMinima())
                .map(this::enrichSeccionResponse)
                .collect(Collectors.toList());
    }

    private int getEstudiantesInscritos(Seccion seccion) {
        // Necesitarías implementar este método en el repository
        // Por ahora, retornamos 0 como placeholder
        return 0; // seccionRepository.countEstudiantesInscritosBySeccionId(seccion.getId());
    }

    private SeccionResponse enrichSeccionResponse(Seccion seccion) {
        Curso curso = cursoRepository.findById(seccion.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Profesor profesor = profesorRepository.findById(seccion.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        int estudiantesInscritos = getEstudiantesInscritos(seccion);

        return seccionMapper.toResponse(seccion, curso.getNombre(), profesor.getNombreCompleto(), estudiantesInscritos);
    }
}