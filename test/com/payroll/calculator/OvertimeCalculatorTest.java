package com.payroll.calculator;

import com.payroll.model.Employee;
import com.payroll.model.vo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OvertimeCalculatorTest {

    private final OvertimeCalculator calculator = new OvertimeCalculator();

    @Test
    void shouldReturnZeroWhenNoOvertime() {
        Employee emp = employee(220, 220);
        Money result = calculator.calculate(emp);
        assertEquals(0, result.value());
    }

    @Test
    void shouldReturnZeroWhenWorkedLessThanContract() {
        Employee emp = employee(220, 200);
        Money result = calculator.calculate(emp);
        assertEquals(0, result.value());
    }

    @Test
    void shouldCalculateOvertimeAtOnePointFiveRate() {
        Employee emp = employee(220, 230);
        double hourlyRate = 5500.0 / 220;
        double expected = 10 * hourlyRate * 1.5;
        Money result = calculator.calculate(emp);
        assertEquals(expected, result.value(), 0.01);
    }

    private Employee employee(double contract, double worked) {
        return new Employee(
            new Name("Teste"),
            new Role("Dev"),
            new Hours(contract),
            new Money(5500),
            new Hours(worked),
            new Dependents(0)
        );
    }
}
