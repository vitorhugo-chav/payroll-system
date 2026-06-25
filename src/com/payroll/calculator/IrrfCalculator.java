package com.payroll.calculator;

import com.payroll.model.Employee;
import com.payroll.model.vo.Money;
import com.payroll.strategy.IrrfCalculation;
import org.springframework.stereotype.Component;

@Component
public class IrrfCalculator implements IrrfCalculation {

    private static final double DEPENDENT_DEDUCTION = 189.59;

    public Money calculate(Employee employee, Money grossSalary, Money inssValue) {
        double calculationBase = grossSalary.value() - inssValue.value()
            - (employee.dependents().value() * DEPENDENT_DEDUCTION);

        if (calculationBase <= 2428.80) return Money.ZERO;
        if (calculationBase <= 2826.65) return new Money(calculationBase * 0.075 - 182.16);
        if (calculationBase <= 3751.05) return new Money(calculationBase * 0.15 - 394.16);
        if (calculationBase <= 4664.68) return new Money(calculationBase * 0.225 - 675.49);
        return new Money(calculationBase * 0.275 - 908.73);
    }
}
