package com.payroll.model.vo;

public record Name(String value) {
    public Name {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Nome é obrigatório");
        value = value.trim();
    }
}
