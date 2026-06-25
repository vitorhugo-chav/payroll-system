package com.payroll.strategy;

import com.payroll.model.Employee;
import com.payroll.model.vo.Money;

public interface OvertimeCalculation {
    Money calculate(Employee employee);
}
