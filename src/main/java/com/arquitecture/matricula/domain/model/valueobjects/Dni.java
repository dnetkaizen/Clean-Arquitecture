package com.arquitecture.matricula.domain.model.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public class Dni {
    private static final Pattern DNI_PATTERN = Pattern.compile("^[A-Za-z0-9]{6,20}$");

    private final String value;

    public Dni(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI cannot be null or empty");
        }
        String v = value.trim();
        if (!DNI_PATTERN.matcher(v).matches()) {
            throw new IllegalArgumentException("Invalid DNI format: " + value);
        }
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dni)) return false;
        Dni dni = (Dni) o;
        return Objects.equals(value, dni.value);
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
