package com.payroll.calculator;

import com.payroll.model.vo.Money;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InssCalculatorTest {

    private final InssCalculator calculator = new InssCalculator();

    @Test
    void shouldReturnZeroWhenSalaryIsZero() {
        Money result = calculator.calculate(new Money(0));
        assertEquals(0, result.value());
    }

    @Test
    void shouldReturnZeroWhenSalaryIsNegative() {
        Money result = calculator.calculate(new Money(-100));
        assertEquals(0, result.value());
    }

    @Test
    void shouldApplyFirstTierRate() {
        Money result = calculator.calculate(new Money(1621.00));
        assertEquals(121.575, result.value(), 0.01);
    }

    @Test
    void shouldApplySecondTierRate() {
        Money result = calculator.calculate(new Money(2500));
        assertEquals(200.68, result.value(), 0.01);
    }

    @Test
    void shouldApplyThirdTierRate() {
        Money result = calculator.calculate(new Money(4000));
        assertEquals(368.60, result.value(), 0.01);
    }

    @Test
    void shouldApplyFourthTierRate() {
        Money result = calculator.calculate(new Money(5875.00));
        assertEquals(624.01, result.value(), 0.01);
    }

    @Test
    void shouldCapAtMaxLimit() {
        Money result = calculator.calculate(new Money(10000));
        assertEquals(988.09, result.value(), 0.01);
    }
}
