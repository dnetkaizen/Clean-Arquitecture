package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.command.RegisterCursoCommand;
import com.arquitecture.matricula.application.dto.response.CursoResponse;

public interface RegisterCursoUseCase {
    CursoResponse register(RegisterCursoCommand command);
}
