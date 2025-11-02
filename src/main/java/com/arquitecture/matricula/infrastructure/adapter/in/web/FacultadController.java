package com.arquitecture.matricula.infrastructure.adapter.in.web;

import com.arquitecture.matricula.application.dto.command.RegisterFacultadCommand;
import com.arquitecture.matricula.application.dto.response.FacultadResponse;
import com.arquitecture.matricula.application.port.in.RegisterFacultadUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facultades")
@CrossOrigin(origins = "*")
public class FacultadController {

    private final RegisterFacultadUseCase registerFacultadUseCase;

    public FacultadController(RegisterFacultadUseCase registerFacultadUseCase) {
        this.registerFacultadUseCase = registerFacultadUseCase;
    }

    @PostMapping
    public ResponseEntity<FacultadResponse> register(@Valid @RequestBody RegisterFacultadCommand command) {
        FacultadResponse response = registerFacultadUseCase.register(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
