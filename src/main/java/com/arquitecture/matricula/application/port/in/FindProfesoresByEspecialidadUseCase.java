package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.query.FindProfesoresByEspecialidadQuery;
import com.arquitecture.matricula.application.dto.response.ProfesorResponse;

import java.util.List;

public interface FindProfesoresByEspecialidadUseCase {
    List<ProfesorResponse> handle(FindProfesoresByEspecialidadQuery query);
}
