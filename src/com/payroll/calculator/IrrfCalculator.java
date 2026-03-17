package com.payroll.calculator;

import com.payroll.model.Employee;

/**
 * Standard IRRF Calculator (Income Tax)
 */
public class IrrfCalculator {
    
    private static final double DEPENDENT_DEDUCTION = 189.59;

    public double calculate(Employee employee, double grossSalary, double inssValue) {
        // 1. A base de cálculo correta
        double calculationBase = grossSalary - inssValue - (employee.getDependents() * DEPENDENT_DEDUCTION);

        // 2. Verificação das faixas (atenção aos valores)
        if (calculationBase <= 2428.80) {
            return 0.0;
        } else if (calculationBase <= 2826.65) {
            return (calculationBase * 0.075) - 182.16;
        } else if (calculationBase <= 3751.05) {
            return (calculationBase * 0.15) - 394.16;
        } else if (calculationBase <= 4664.68) {
            return (calculationBase * 0.225) - 675.49;
        } else {
            return (calculationBase * 0.275) - 908.73;
        }
    }
}
