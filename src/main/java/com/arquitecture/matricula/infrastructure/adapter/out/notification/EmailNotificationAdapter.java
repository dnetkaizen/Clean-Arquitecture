package com.arquitecture.matricula.infrastructure.adapter.out.notification;

import com.arquitecture.matricula.application.port.out.EmailNotificationPort;
import com.arquitecture.matricula.shared.annotation.Adapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Adapter
public class EmailNotificationAdapter implements EmailNotificationPort {

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationAdapter.class);

    @Override
    public void sendSeccionCreated(String email, String cursoNombre, String codigoSeccion, String periodoAcademico) {
        log.info("[EMAIL] Seccion creada -> to={}, curso={}, seccion={}, periodo={}", email, cursoNombre, codigoSeccion, periodoAcademico);
    }

    @Override
    public void sendSeccionUpdated(String email, String cursoNombre, String codigoSeccion, String periodoAcademico) {
        log.info("[EMAIL] Seccion actualizada -> to={}, curso={}, seccion={}, periodo={}", email, cursoNombre, codigoSeccion, periodoAcademico);
    }

    @Override
    public void sendSeccionCancelled(String email, String cursoNombre, String codigoSeccion, String periodoAcademico) {
        log.info("[EMAIL] Seccion cancelada -> to={}, curso={}, seccion={}, periodo={}", email, cursoNombre, codigoSeccion, periodoAcademico);
    }
}
