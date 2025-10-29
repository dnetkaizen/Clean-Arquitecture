package com.arquitecture.matricula.application.port.in;

import com.arquitecture.matricula.application.dto.query.FindCarrerasByFacultadQuery;
import com.arquitecture.matricula.application.dto.response.CarreraResponse;

import java.util.List;

public interface FindCarrerasByFacultadUseCase {
    List<CarreraResponse> handle(FindCarrerasByFacultadQuery query);
}
