package com.arquitecture.matricula.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class ProfesorId {
    private final UUID value;

    public ProfesorId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("ProfesorId cannot be null");
        }
        this.value = value;
    }

    public static ProfesorId generate() {
        return new ProfesorId(UUID.randomUUID());
    }

    public static ProfesorId of(String value) {
        return new ProfesorId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfesorId)) return false;
        ProfesorId that = (ProfesorId) o;
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
