package com.payroll.strategy;

import com.payroll.model.Employee;
import com.payroll.model.vo.Money;

public interface IrrfCalculation {
    Money calculate(Employee employee, Money grossSalary, Money inssValue);
}
