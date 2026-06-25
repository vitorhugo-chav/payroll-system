package com.payroll.application.usecase;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;

public interface ProcessPayrollUseCase {
    PayrollResponse execute(PayrollInput input);
}
