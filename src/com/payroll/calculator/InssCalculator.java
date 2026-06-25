package com.payroll.calculator;

import com.payroll.model.vo.Money;

public class InssCalculator {

    private static final double MAX_LIMIT = 8475.55;

    private static final double TIER_1_LIMIT = 1621.00;
    private static final double TIER_2_LIMIT = 2902.84;
    private static final double TIER_3_LIMIT = 4354.27;

    private static final double RATE_1 = 0.075;
    private static final double RATE_2 = 0.09;
    private static final double RATE_3 = 0.12;
    private static final double RATE_4 = 0.14;

    private static final double DEDUCTION_2 = 24.32;
    private static final double DEDUCTION_3 = 111.40;
    private static final double DEDUCTION_4 = 198.49;

    public Money calculate(Money grossSalary) {
        if (!grossSalary.isGreaterThan(0)) return Money.ZERO;

        double taxableBase = Math.min(grossSalary.value(), MAX_LIMIT);

        if (taxableBase <= TIER_1_LIMIT) return new Money(taxableBase * RATE_1);
        if (taxableBase <= TIER_2_LIMIT) return new Money(taxableBase * RATE_2 - DEDUCTION_2);
        if (taxableBase <= TIER_3_LIMIT) return new Money(taxableBase * RATE_3 - DEDUCTION_3);
        return new Money(taxableBase * RATE_4 - DEDUCTION_4);
    }
}
