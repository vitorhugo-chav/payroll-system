package com.payroll.model;

/**
 * Represents a company emplyee with salary and contract details.
 * This class ensure data integrity through constructor validation.
 */
public class Employee {

    private final String name;
    private final String role;
    private final double monthlyContractHours;
    private final double baseSalary;
    private final double actualWorkedHours;
    private final int dependents;

    /**
     * Constructs a new Employee with mandatory field validation.
     */
    public Employee(String name, String role, double monthlyContractHours, double baseSalary, double actualWorkedHours, int dependents) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Emplyee name is required.");
        }
        if (baseSalary <= 0) {
            throw new IllegalArgumentException("Base salary must be greater than zero.");
        }
        if (monthlyContractHours <= 0) {
            throw new IllegalArgumentException("Contract hours must be greater than zero.");
        }
        if (actualWorkedHours < 0) {
            throw new IllegalArgumentException("Worked hours cannot be negative.");
        }
        if (dependents < 0) {
            throw new IllegalArgumentException("Dependents cannot be negative.");
        }

        this.name = name.trim();
        this.role = role.trim();
        this.monthlyContractHours = monthlyContractHours;
        this.baseSalary = baseSalary;
        this.actualWorkedHours = actualWorkedHours;
        this.dependents = dependents;
    }

    // Getters
    public String getName() { return name; }
    public String getRole() { return role; }
    public double getMonthlyContractHours() { return monthlyContractHours; }
    public double getBaseSalary() { return baseSalary; }
    public double getActualWorkedHours() { return actualWorkedHours; }
    public int getDependents() { return dependents; }

    /**
     * Calculates the value of a single work hour based on contract.
     * @return The hourly rate.
     */
    public double getHourlyRate() {
        return baseSalary / monthlyContractHours;
    }
}