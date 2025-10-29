package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.query.FindSeccionesDisponiblesQuery;
import com.arquitecture.matricula.application.dto.response.SeccionResponse;

import java.util.List;

public interface FindSeccionesDisponiblesUseCase {
    List<SeccionResponse> handle(FindSeccionesDisponiblesQuery query);
}
