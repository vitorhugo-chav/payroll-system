package com.payroll.model.vo;

public record Hours(double value) {
    public Hours {
        if (value < 0)
            throw new IllegalArgumentException("Horas não podem ser negativas");
    }

    public boolean isGreaterThan(Hours other) {
        return Double.compare(this.value, other.value) > 0;
    }

    public double subtract(Hours other) {
        return this.value - other.value;
    }
}
