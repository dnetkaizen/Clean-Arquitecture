package com.arquitecture.matricula.infrastructure.adapter.in.web;

import com.arquitecture.matricula.application.dto.command.CreateSeccionCommand;
import com.arquitecture.matricula.application.dto.query.FindSeccionesByCursoQuery;
import com.arquitecture.matricula.application.dto.query.FindSeccionesDisponiblesQuery;
import com.arquitecture.matricula.application.dto.response.SeccionResponse;
import com.arquitecture.matricula.application.port.in.CreateSeccionUseCase;
import com.arquitecture.matricula.application.port.in.FindSeccionesByCursoUseCase;
import com.arquitecture.matricula.application.port.in.FindSeccionesDisponiblesUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secciones")
@CrossOrigin(origins = "*")
public class SeccionController {

    private final CreateSeccionUseCase createSeccionUseCase;
    private final FindSeccionesByCursoUseCase findSeccionesByCursoUseCase;
    private final FindSeccionesDisponiblesUseCase findSeccionesDisponiblesUseCase;

    public SeccionController(CreateSeccionUseCase createSeccionUseCase,
                             FindSeccionesByCursoUseCase findSeccionesByCursoUseCase,
                             FindSeccionesDisponiblesUseCase findSeccionesDisponiblesUseCase) {
        this.createSeccionUseCase = createSeccionUseCase;
        this.findSeccionesByCursoUseCase = findSeccionesByCursoUseCase;
        this.findSeccionesDisponiblesUseCase = findSeccionesDisponiblesUseCase;
    }

    @PostMapping
    public ResponseEntity<SeccionResponse> create(@Valid @RequestBody CreateSeccionCommand command) {
        SeccionResponse response = createSeccionUseCase.create(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/by-curso")
    public ResponseEntity<List<SeccionResponse>> findByCurso(
            @RequestParam String cursoId,
            @RequestParam(required = false) String periodoAcademico,
            @RequestParam(required = false) String profesorId,
            @RequestParam(required = false) Boolean activa
    ) {
        FindSeccionesByCursoQuery query = new FindSeccionesByCursoQuery(cursoId, periodoAcademico, profesorId, activa);
        List<SeccionResponse> result = findSeccionesByCursoUseCase.handle(query);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<SeccionResponse>> findDisponibles(
            @RequestParam String periodoAcademico,
            @RequestParam(required = false) String cursoId,
            @RequestParam(required = false) Integer capacidadMinima
    ) {
        FindSeccionesDisponiblesQuery query = new FindSeccionesDisponiblesQuery(periodoAcademico, cursoId, capacidadMinima);
        List<SeccionResponse> result = findSeccionesDisponiblesUseCase.handle(query);
        return ResponseEntity.ok(result);
    }
}
