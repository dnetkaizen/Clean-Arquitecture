package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.command.RegisterCarreraCommand;
import com.arquitecture.matricula.application.dto.response.CarreraResponse;

public interface RegisterCarreraUseCase {
    CarreraResponse register(RegisterCarreraCommand command);
}
