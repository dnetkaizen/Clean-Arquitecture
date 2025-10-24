package com.arquitecture.matricula.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class SeccionId {
    private final UUID value;

    public SeccionId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("SeccionId cannot be null");
        }
        this.value = value;
    }

    public static SeccionId generate() {
        return new SeccionId(UUID.randomUUID());
    }

    public static SeccionId of(String value) {
        return new SeccionId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeccionId)) return false;
        SeccionId that = (SeccionId) o;
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
