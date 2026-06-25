package com.payroll.calculator;

import com.payroll.model.Employee;
import com.payroll.model.vo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IrrfCalculatorTest {

    private final IrrfCalculator calculator = new IrrfCalculator();

    @Test
    void shouldReturnZeroWhenBaseIsBelowFirstTier() {
        Employee emp = employeeWithDependents(0);
        Money result = calculator.calculate(emp, new Money(2428.80), Money.ZERO);
        assertEquals(0, result.value());
    }

    @Test
    void shouldApplySecondTier() {
        Employee emp = employeeWithDependents(0);
        Money result = calculator.calculate(emp, new Money(2826.65), Money.ZERO);
        assertEquals(29.84, result.value(), 0.01);
    }

    @Test
    void shouldApplyThirdTier() {
        Employee emp = employeeWithDependents(0);
        Money result = calculator.calculate(emp, new Money(3751.05), Money.ZERO);
        assertEquals(168.50, result.value(), 0.01);
    }

    @Test
    void shouldApplyFourthTier() {
        Employee emp = employeeWithDependents(0);
        Money result = calculator.calculate(emp, new Money(4664.68), Money.ZERO);
        assertEquals(374.06, result.value(), 0.01);
    }

    @Test
    void shouldApplyTopTier() {
        Employee emp = employeeWithDependents(0);
        Money result = calculator.calculate(emp, new Money(5000), Money.ZERO);
        assertEquals(466.27, result.value(), 0.01);
    }

    @Test
    void shouldDeductDependents() {
        Employee emp = employeeWithDependents(2);
        double calculationBase = 5000 - 0 - (2 * 189.59);
        double expected = calculationBase * 0.225 - 675.49;
        Money result = calculator.calculate(emp, new Money(5000), Money.ZERO);
        assertEquals(expected, result.value(), 0.01);
    }

    private Employee employeeWithDependents(int count) {
        return new Employee(
            new Name("Teste"),
            new Role("Dev"),
            new Hours(220),
            new Money(5000),
            new Hours(220),
            new Dependents(count)
        );
    }
}
