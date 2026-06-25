package com.payroll.model.vo;

public record Money(double value) {

    public static final Money ZERO = new Money(0);

    public Money plus(Money other) {
        return new Money(this.value + other.value);
    }

    public Money plus(double amount) {
        return new Money(this.value + amount);
    }

    public Money minus(Money other) {
        return new Money(this.value - other.value);
    }

    public Money times(double factor) {
        return new Money(this.value * factor);
    }

    public boolean isGreaterThan(double threshold) {
        return Double.compare(this.value, threshold) > 0;
    }
}
