package com.arquitecture.matricula.application.port.out;

public interface EmailNotificationPort {

    void sendSeccionCreated(String email, String cursoNombre, String codigoSeccion, String periodoAcademico);

    void sendSeccionUpdated(String email, String cursoNombre, String codigoSeccion, String periodoAcademico);

    void sendSeccionCancelled(String email, String cursoNombre, String codigoSeccion, String periodoAcademico);
}
 