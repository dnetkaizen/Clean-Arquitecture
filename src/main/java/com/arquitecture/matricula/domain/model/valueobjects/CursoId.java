package com.arquitecture.matricula.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class CursoId {
    private final UUID value;

    public CursoId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("CursoId cannot be null");
        }
        this.value = value;
    }

    public static CursoId generate() {
        return new CursoId(UUID.randomUUID());
    }

    public static CursoId of(String value) {
        return new CursoId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursoId)) return false;
        CursoId that = (CursoId) o;
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
