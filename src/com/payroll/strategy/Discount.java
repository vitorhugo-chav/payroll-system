package com.payroll.strategy;

import com.payroll.model.Employee;

/**
 * Strategy interface for payroll deductions.
 * Any specific tax or discount must implement this method.
 */
public interface Discount {

    /**
     * Calculates the deduction amount.
     * @param employee The employee to whom the discount applies.
     * @param grossSalary The calculated gross salary (base + benefits).
     * @return The total value to be deducted.
     */
    double calculate(Employee employee, double grossSalary);
}
