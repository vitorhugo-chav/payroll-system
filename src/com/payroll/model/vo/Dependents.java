package com.payroll.model.vo;

public record Dependents(int value) {
    public Dependents {
        if (value < 0)
            throw new IllegalArgumentException("Dependentes não podem ser negativos");
    }
}
