package com.payroll.service;

import com.payroll.calculator.IrrfCalculator;
import com.payroll.calculator.OvertimeCalculator;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.strategy.Discount;

/**
 * The heart of the system. Prchestrates the payroll process.
 * It coordinates calculations and generates a final record.
 */
public class PayrollService {

    private final OvertimeCalculator overtimeCalculator;
    private final Discount inssDiscount;
    private final IrrfCalculator irrfCalculator;

    public PayrollService(OvertimeCalculator overtimeCalculator, Discount inssDiscount, IrrfCalculator irrfCalculator) {
        this.overtimeCalculator = overtimeCalculator;
        this.inssDiscount = inssDiscount;
        this.irrfCalculator = irrfCalculator;
    }

    /**
     * Process the payroll for a single employee.
     * @param employee The employee to process.
     * @return A PayrollRecord containing all final values.
     */
    public PayrollRecord process(Employee employee) {
        // 1. Calculate overtime
        double overtimeValue = overtimeCalculator.calculate(employee);

        // 2. Calculate gross salary (Base + Overtime)
        double grossSalary = employee.getBaseSalary() + overtimeValue;

        // 3. Calculate INSS deduction
        double inssValue = inssDiscount.calculate(employee, grossSalary);

        // 4. Calculate IRRF deduction
        double irrfValue = irrfCalculator.calculate(employee, grossSalary, inssValue);

        // 5. Calculate net salary
        double netSalary = grossSalary - inssValue;

        // 6. Return the DTO (Recibo) with all values - No re-calculation needed later!
        return new PayrollRecord(
            employee,
            employee.getBaseSalary(),
            overtimeValue,
            grossSalary,
            inssValue,
            irrfValue,
            netSalary
        );
    }
}