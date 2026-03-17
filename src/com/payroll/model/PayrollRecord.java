package com.payroll.model;

/**
 * A Data Transfer Object (DTO) that holds the final results of
 * payroll calculations. This avoids re-calculating values multiples times.
 */
public class PayrollRecord {
    
    private final Employee employee;
    private final double baseSalary;
    private final double overtimeValue;
    private final double grossSalary;
    private final double inssDeduction;
    private final double irrfDeduction;
    private final double netSalary;

    /**
     * Constructs an immutable record with all calculated values.
     */
    public PayrollRecord(Employee employee, double baseSalary, double overtimeValue, double grossSalary, double inssDeduction, double irrfDeduction, double netSalary) {
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.overtimeValue = overtimeValue;
        this.grossSalary = grossSalary;
        this.inssDeduction = inssDeduction;
        this.irrfDeduction = irrfDeduction;
        this.netSalary = netSalary;
    }

    // Getters only - No logic allowed here!
    public Employee getEmployee() { return employee; }
    public double getBaseSalary() { return baseSalary; }
    public double getOvertimeValue() { return overtimeValue; }
    public double getGrossSalary() { return grossSalary; }
    public double getInssDeduction() { return inssDeduction; }
    public double getIrrfDeduction() { return irrfDeduction; }
    public double getNetSalary() { return netSalary; }
}
