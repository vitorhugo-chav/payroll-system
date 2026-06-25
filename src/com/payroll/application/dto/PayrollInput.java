package com.payroll.application.dto;

public record PayrollInput(
    String name,
    String role,
    double contractHours,
    double baseSalary,
    double workedHours,
    int dependents
) {}
