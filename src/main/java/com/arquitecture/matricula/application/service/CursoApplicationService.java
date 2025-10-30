package com.arquitecture.matricula.application.service;

import com.arquitecture.matricula.application.port.in.RegisterCursoUseCase;
import com.arquitecture.matricula.application.port.in.FindCursosByCarreraUseCase;
import com.arquitecture.matricula.application.dto.command.RegisterCursoCommand;
import com.arquitecture.matricula.application.dto.query.FindCursosByCarreraQuery;
import com.arquitecture.matricula.application.dto.response.CursoResponse;
import com.arquitecture.matricula.application.mapper.CursoMapper;
import com.arquitecture.matricula.domain.model.Curso;
import com.arquitecture.matricula.domain.model.Carrera;
import com.arquitecture.matricula.domain.model.valueobjects.CarreraId;
import com.arquitecture.matricula.domain.model.valueobjects.CodigoCurso;
import com.arquitecture.matricula.domain.repository.CursoRepository;
import com.arquitecture.matricula.domain.repository.CarreraRepository;
import com.arquitecture.matricula.domain.repository.SeccionRepository;
import com.arquitecture.matricula.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class CursoApplicationService implements RegisterCursoUseCase, FindCursosByCarreraUseCase {

    private final CursoRepository cursoRepository;
    private final CarreraRepository carreraRepository;
    private final SeccionRepository seccionRepository;
    private final CursoMapper cursoMapper;

    public CursoApplicationService(CursoRepository cursoRepository,
                                  CarreraRepository carreraRepository,
                                  SeccionRepository seccionRepository,
                                  CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.carreraRepository = carreraRepository;
        this.seccionRepository = seccionRepository;
        this.cursoMapper = cursoMapper;
    }

    @Override
    public CursoResponse register(RegisterCursoCommand command) {
        // Validar que la carrera existe
        Carrera carrera = carreraRepository.findById(CarreraId.of(command.getCarreraId()))
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Crear CodigoCurso - CORREGIDO: usar constructor directo
        CodigoCurso codigoCurso = new CodigoCurso(command.getCodigo());

        // Validar que no existe otro curso con el mismo código
        if (cursoRepository.existsByCodigo(codigoCurso)) {
            throw new RuntimeException("Ya existe un curso con este código");
        }

        // Validar que no existe otro curso con el mismo nombre en la misma carrera
        if (cursoRepository.existsByNombreAndCarrera(command.getNombre(), carrera.getId())) {
            throw new RuntimeException("Ya existe un curso con este nombre en la carrera");
        }

        // Crear el curso - CORREGIDO: usar el orden correcto de parámetros
        Curso curso = Curso.create(
                carrera.getId(),    // Primero carreraId
                codigoCurso,        // Luego codigo
                command.getNombre(), // nombre
                command.getDescripcion(), // descripcion
                command.getCreditos(),    // creditos
                command.getNivelSemestre() // nivelSemestre
        );

        // Guardar el curso
        Curso savedCurso = cursoRepository.save(curso);

        // Obtener información adicional para la respuesta
        int cantidadSecciones = seccionRepository.findByCurso(savedCurso.getId()).size();

        // Convertir a DTO de respuesta
        return cursoMapper.toResponse(savedCurso, carrera.getNombre(), cantidadSecciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> handle(FindCursosByCarreraQuery query) {
        List<Curso> cursos = cursoRepository.findByCarrera(CarreraId.of(query.getCarreraId()));

        return cursos.stream()
                .filter(curso -> shouldIncludeCurso(curso, query.getActivo(), query.getNivelSemestre()))
                .map(this::enrichCursoResponse)
                .collect(Collectors.toList());
    }

    private boolean shouldIncludeCurso(Curso curso, Boolean activoFilter, Integer nivelSemestreFilter) {
        // Filtro por estado activo
        if (activoFilter != null && curso.isActivo() != activoFilter) {
            return false;
        }
        
        // Filtro por nivel de semestre
        if (nivelSemestreFilter != null && curso.getNivelSemestre() != nivelSemestreFilter) {
            return false;
        }
        
        return true;
    }

    private CursoResponse enrichCursoResponse(Curso curso) {
        Carrera carrera = carreraRepository.findById(curso.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        int cantidadSecciones = seccionRepository.findByCurso(curso.getId()).size();

        return cursoMapper.toResponse(curso, carrera.getNombre(), cantidadSecciones);
    }
}