package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.command.RegisterProfesorCommand;
import com.arquitecture.matricula.application.dto.response.ProfesorResponse;

public interface RegisterProfesorUseCase {
    ProfesorResponse register(RegisterProfesorCommand command);
}
