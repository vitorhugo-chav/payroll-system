package com.payroll.strategy;

import com.payroll.calculator.InssCalculator;
import com.payroll.model.Employee;

/**
 * Implementation of the Discount interface specifically for INSS.
 * This acts as an Adapter between the Discount contract and the InssCalculator.
 */
public class InssDiscount implements Discount {

    private final InssCalculator calculator;

    /**
     * Constructor that receivers the specific calculator.
     * This is Dependency Injection in action.
     */
    public InssDiscount(InssCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public double calculate(Employee employee, double grossSalary) {
        return calculator.calculate(grossSalary);
    }
}
