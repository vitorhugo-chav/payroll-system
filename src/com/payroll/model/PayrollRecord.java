package com.payroll.model;

import com.payroll.model.vo.Money;

public class PayrollRecord {

    private final Employee employee;
    private final Money baseSalary;
    private final Money overtimeValue;
    private final Money grossSalary;
    private final Money inssDeduction;
    private final Money irrfDeduction;
    private final Money netSalary;

    public PayrollRecord(Employee employee, Money baseSalary, Money overtimeValue, Money grossSalary, Money inssDeduction, Money irrfDeduction, Money netSalary) {
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.overtimeValue = overtimeValue;
        this.grossSalary = grossSalary;
        this.inssDeduction = inssDeduction;
        this.irrfDeduction = irrfDeduction;
        this.netSalary = netSalary;
    }

    public Employee employee() { return employee; }
    public Money baseSalary() { return baseSalary; }
    public Money overtimeValue() { return overtimeValue; }
    public Money grossSalary() { return grossSalary; }
    public Money inssDeduction() { return inssDeduction; }
    public Money irrfDeduction() { return irrfDeduction; }
    public Money netSalary() { return netSalary; }
}
