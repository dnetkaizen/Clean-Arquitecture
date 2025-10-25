package com.arquitecture.matricula.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class CarreraId {
    private final UUID value;

    public CarreraId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("CarreraId cannot be null");
        }
        this.value = value;
    }

    public static CarreraId generate() {
        return new CarreraId(UUID.randomUUID());
    }

    public static CarreraId of(String value) {
        return new CarreraId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarreraId)) return false;
        CarreraId that = (CarreraId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}