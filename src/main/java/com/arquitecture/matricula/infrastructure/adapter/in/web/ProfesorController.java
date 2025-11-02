package com.arquitecture.matricula.infrastructure.adapter.in.web;

import com.arquitecture.matricula.application.dto.command.RegisterProfesorCommand;
import com.arquitecture.matricula.application.dto.query.FindProfesoresByEspecialidadQuery;
import com.arquitecture.matricula.application.dto.response.ProfesorResponse;
import com.arquitecture.matricula.application.port.in.RegisterProfesorUseCase;
import com.arquitecture.matricula.application.port.in.FindProfesoresByEspecialidadUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesores")
@CrossOrigin(origins = "*")
public class ProfesorController {

    private final RegisterProfesorUseCase registerProfesorUseCase;
    private final FindProfesoresByEspecialidadUseCase findProfesoresByEspecialidadUseCase;

    public ProfesorController(RegisterProfesorUseCase registerProfesorUseCase,
                              FindProfesoresByEspecialidadUseCase findProfesoresByEspecialidadUseCase) {
        this.registerProfesorUseCase = registerProfesorUseCase;
        this.findProfesoresByEspecialidadUseCase = findProfesoresByEspecialidadUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfesorResponse> register(@Valid @RequestBody RegisterProfesorCommand command) {
        ProfesorResponse response = registerProfesorUseCase.register(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProfesorResponse>> find(
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String tituloAcademico,
            @RequestParam(required = false) Boolean activo
    ) {
        FindProfesoresByEspecialidadQuery query = new FindProfesoresByEspecialidadQuery(
                especialidad, tituloAcademico, activo
        );
        List<ProfesorResponse> result = findProfesoresByEspecialidadUseCase.handle(query);
        return ResponseEntity.ok(result);
    }
}
