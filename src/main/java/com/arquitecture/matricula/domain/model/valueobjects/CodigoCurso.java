package com.arquitecture.matricula.domain.model.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public class CodigoCurso {
    private static final Pattern CODIGO_PATTERN = Pattern.compile("^[A-Za-z0-9]{2,20}$");

    private final String value;

    public CodigoCurso(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Código del curso cannot be null or empty");
        }
        String v = value.trim().toUpperCase();
        if (!CODIGO_PATTERN.matcher(v).matches()) {
            throw new IllegalArgumentException("Invalid código curso format: " + value);
        }
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodigoCurso)) return false;
        CodigoCurso that = (CodigoCurso) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}