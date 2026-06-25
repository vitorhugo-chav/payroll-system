package com.payroll.model.vo;

public record Contract(Hours monthlyContractHours, Money baseSalary) {
    public Contract {
        if (monthlyContractHours.value() <= 0)
            throw new IllegalArgumentException("Horas contratuais devem ser maiores que zero");
        if (baseSalary.value() <= 0)
            throw new IllegalArgumentException("Salário base deve ser maior que zero");
    }

    public Money hourlyRate() {
        return new Money(baseSalary.value() / monthlyContractHours.value());
    }
}
