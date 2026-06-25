package com.payroll.application.dto;

public record PayrollResponse(
    String employeeName,
    String employeeRole,
    double baseSalary,
    double overtimeValue,
    double grossSalary,
    double inssDeduction,
    double irrfDeduction,
    double netSalary
) {}
