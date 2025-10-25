package com.arquitecture.matricula.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class FacultadId {
    private final UUID value;

    public FacultadId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("FacultadId cannot be null");
        }
        this.value = value;
    }

    public static FacultadId generate() {
        return new FacultadId(UUID.randomUUID());
    }

    public static FacultadId of(String value) {
        return new FacultadId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultadId)) return false;
        FacultadId that = (FacultadId) o;
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