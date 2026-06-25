package com.payroll.service;

import com.payroll.calculator.InssCalculator;
import com.payroll.calculator.IrrfCalculator;
import com.payroll.calculator.OvertimeCalculator;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.*;
import com.payroll.strategy.Discount;
import com.payroll.strategy.InssDiscount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PayrollServiceTest {

    private final PayrollService service;

    PayrollServiceTest() {
        InssCalculator inssCalc = new InssCalculator();
        Discount inssDiscount = new InssDiscount(inssCalc);
        OvertimeCalculator overtimeCalc = new OvertimeCalculator();
        IrrfCalculator irrfCalc = new IrrfCalculator();
        service = new PayrollService(overtimeCalc, inssDiscount, irrfCalc);
    }

    @Test
    void shouldProcessFullPayroll() {
        Employee emp = new Employee(
            new Name("João"),
            new Role("Analista"),
            new Hours(220),
            new Money(5500),
            new Hours(230),
            new Dependents(2)
        );

        PayrollRecord record = service.process(emp);

        assertEquals(5500, record.baseSalary().value());
        assertEquals(375, record.overtimeValue().value(), 0.01);
        assertEquals(5875, record.grossSalary().value());
        assertTrue(record.inssDeduction().value() > 0);
        assertTrue(record.irrfDeduction().value() > 0);
        assertEquals(5875 - record.inssDeduction().value() - record.irrfDeduction().value(),
            record.netSalary().value(), 0.01);
    }

    @Test
    void shouldProcessPayrollWithoutOvertime() {
        Employee emp = new Employee(
            new Name("Maria"),
            new Role("Dev"),
            new Hours(220),
            new Money(3000),
            new Hours(220),
            new Dependents(0)
        );

        PayrollRecord record = service.process(emp);

        assertEquals(0, record.overtimeValue().value());
        assertEquals(3000, record.grossSalary().value());
    }

    @Test
    void shouldCalculateCorrectNetSalary() {
        Employee emp = new Employee(
            new Name("Teste"),
            new Role("Teste"),
            new Hours(220),
            new Money(5500),
            new Hours(230),
            new Dependents(2)
        );

        PayrollRecord record = service.process(emp);
        double expectedNet = record.grossSalary().value()
            - record.inssDeduction().value()
            - record.irrfDeduction().value();

        assertEquals(expectedNet, record.netSalary().value(), 0.01);
    }
}
