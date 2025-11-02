package com.arquitecture.matricula.infrastructure.adapter.in.web;

import com.arquitecture.matricula.application.dto.command.RegisterCursoCommand;
import com.arquitecture.matricula.application.dto.query.FindCursosByCarreraQuery;
import com.arquitecture.matricula.application.dto.response.CursoResponse;
import com.arquitecture.matricula.application.port.in.RegisterCursoUseCase;
import com.arquitecture.matricula.application.port.in.FindCursosByCarreraUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final RegisterCursoUseCase registerCursoUseCase;
    private final FindCursosByCarreraUseCase findCursosByCarreraUseCase;

    public CursoController(RegisterCursoUseCase registerCursoUseCase,
                           FindCursosByCarreraUseCase findCursosByCarreraUseCase) {
        this.registerCursoUseCase = registerCursoUseCase;
        this.findCursosByCarreraUseCase = findCursosByCarreraUseCase;
    }

    @PostMapping
    public ResponseEntity<CursoResponse> register(@Valid @RequestBody RegisterCursoCommand command) {
        CursoResponse response = registerCursoUseCase.register(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/by-carrera")
    public ResponseEntity<List<CursoResponse>> findByCarrera(
            @RequestParam String carreraId,
            @RequestParam(required = false) Integer nivelSemestre,
            @RequestParam(required = false) Boolean activo
    ) {
        FindCursosByCarreraQuery query = new FindCursosByCarreraQuery(carreraId, nivelSemestre, activo);
        List<CursoResponse> result = findCursosByCarreraUseCase.handle(query);
        return ResponseEntity.ok(result);
    }
}
