package com.payroll.calculator;

import com.payroll.model.Employee;
import com.payroll.model.vo.Hours;
import com.payroll.model.vo.Money;
import com.payroll.strategy.OvertimeCalculation;

public class OvertimeCalculator implements OvertimeCalculation {

    private static final double OVERTIME_FACTOR = 1.5;

    public Money calculate(Employee employee) {
        Hours actual = employee.actualWorkedHours();
        Hours contract = employee.contract().monthlyContractHours();

        if (!actual.isGreaterThan(contract)) return Money.ZERO;

        double extraHours = actual.subtract(contract);
        return employee.contract().hourlyRate().times(extraHours * OVERTIME_FACTOR);
    }
}
