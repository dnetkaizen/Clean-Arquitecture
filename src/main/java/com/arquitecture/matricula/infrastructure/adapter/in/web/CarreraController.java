package com.arquitecture.matricula.infrastructure.adapter.in.web;

import com.arquitecture.matricula.application.dto.command.RegisterCarreraCommand;
import com.arquitecture.matricula.application.dto.query.FindCarrerasByFacultadQuery;
import com.arquitecture.matricula.application.dto.response.CarreraResponse;
import com.arquitecture.matricula.application.port.in.RegisterCarreraUseCase;
import com.arquitecture.matricula.application.port.in.FindCarrerasByFacultadUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carreras")
@CrossOrigin(origins = "*")
public class CarreraController {

    private final RegisterCarreraUseCase registerCarreraUseCase;
    private final FindCarrerasByFacultadUseCase findCarrerasByFacultadUseCase;

    public CarreraController(RegisterCarreraUseCase registerCarreraUseCase,
                             FindCarrerasByFacultadUseCase findCarrerasByFacultadUseCase) {
        this.registerCarreraUseCase = registerCarreraUseCase;
        this.findCarrerasByFacultadUseCase = findCarrerasByFacultadUseCase;
    }

    @PostMapping
    public ResponseEntity<CarreraResponse> register(@Valid @RequestBody RegisterCarreraCommand command) {
        CarreraResponse response = registerCarreraUseCase.register(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/by-facultad")
    public ResponseEntity<List<CarreraResponse>> findByFacultad(
            @RequestParam String facultadId,
            @RequestParam(required = false) Boolean activa
    ) {
        FindCarrerasByFacultadQuery query = new FindCarrerasByFacultadQuery(facultadId, activa);
        List<CarreraResponse> result = findCarrerasByFacultadUseCase.handle(query);
        return ResponseEntity.ok(result);
    }
}
