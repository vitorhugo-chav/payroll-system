package com.payroll.strategy;

import com.payroll.calculator.InssCalculator;
import com.payroll.model.Employee;
import com.payroll.model.vo.Money;
import org.springframework.stereotype.Component;

@Component
public class InssDiscount implements Discount {

    private final InssCalculator calculator;

    public InssDiscount(InssCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Money calculate(Employee employee, Money grossSalary) {
        return calculator.calculate(grossSalary);
    }
}
