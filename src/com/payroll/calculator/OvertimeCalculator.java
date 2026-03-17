package com.payroll.calculator;

import com.payroll.model.Employee;

/**
 * Specialist class for overtime pay calculations.
 */
public class OvertimeCalculator {

    private static final double OVERTIME_FACTOR = 1.5;

    /**
     * Calculates overtime pay based on hours worked beyond the contract.
     * @param employee The emplyee data.
     * @return The total overtime value in currency.
     */
    public double calculate(Employee employee) {
        if (employee.getActualWorkedHours() <= employee.getMonthlyContractHours()) {
            return 0.0;
        }

        double extraHours = employee.getActualWorkedHours() - employee.getMonthlyContractHours();
        return extraHours * employee.getHourlyRate() * OVERTIME_FACTOR;
    }
}
