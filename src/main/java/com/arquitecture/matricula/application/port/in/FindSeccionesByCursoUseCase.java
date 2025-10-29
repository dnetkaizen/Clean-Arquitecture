package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.query.FindSeccionesByCursoQuery;
import com.arquitecture.matricula.application.dto.response.SeccionResponse;

import java.util.List;

public interface FindSeccionesByCursoUseCase {
    List<SeccionResponse> handle(FindSeccionesByCursoQuery query);
}
