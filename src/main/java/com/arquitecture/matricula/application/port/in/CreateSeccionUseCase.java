package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.command.CreateSeccionCommand;
import com.arquitecture.matricula.application.dto.response.SeccionResponse;

public interface CreateSeccionUseCase {
    SeccionResponse create(CreateSeccionCommand command);
}
