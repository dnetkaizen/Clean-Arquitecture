package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.command.RegisterFacultadCommand;
import com.arquitecture.matricula.application.dto.response.FacultadResponse;

public interface RegisterFacultadUseCase {
    FacultadResponse register(RegisterFacultadCommand command);
}
