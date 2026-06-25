package com.payroll.service;

import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.Money;
import com.payroll.strategy.Discount;
import com.payroll.strategy.IrrfCalculation;
import com.payroll.strategy.OvertimeCalculation;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    private final OvertimeCalculation overtimeCalculation;
    private final Discount inssDiscount;
    private final IrrfCalculation irrfCalculation;

    public PayrollService(OvertimeCalculation overtimeCalculation, Discount inssDiscount, IrrfCalculation irrfCalculation) {
        this.overtimeCalculation = overtimeCalculation;
        this.inssDiscount = inssDiscount;
        this.irrfCalculation = irrfCalculation;
    }

    public PayrollRecord process(Employee employee) {
        Money overtimeValue = overtimeCalculation.calculate(employee);
        Money grossSalary = employee.contract().baseSalary().plus(overtimeValue);
        Money inssValue = inssDiscount.calculate(employee, grossSalary);
        Money irrfValue = irrfCalculation.calculate(employee, grossSalary, inssValue);
        Money netSalary = grossSalary.minus(inssValue).minus(irrfValue);

        return new PayrollRecord(
            employee,
            employee.contract().baseSalary(),
            overtimeValue,
            grossSalary,
            inssValue,
            irrfValue,
            netSalary
        );
    }
}
