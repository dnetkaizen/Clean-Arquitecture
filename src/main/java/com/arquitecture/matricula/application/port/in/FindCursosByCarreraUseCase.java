package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.query.FindCursosByCarreraQuery;
import com.arquitecture.matricula.application.dto.response.CursoResponse;

import java.util.List;

public interface FindCursosByCarreraUseCase {
    List<CursoResponse> handle(FindCursosByCarreraQuery query);
}