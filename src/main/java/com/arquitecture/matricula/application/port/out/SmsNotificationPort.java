package com.arquitecture.matricula.application.port.out;

public interface SmsNotificationPort {
    void sendSeccionReminder(String phoneNumber, String message);
}
