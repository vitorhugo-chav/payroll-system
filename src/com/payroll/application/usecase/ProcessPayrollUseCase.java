package com.payroll.application.usecase;

import com.payroll.application.dto.PayrollInput;
import com.payroll.model.PayrollRecord;

public interface ProcessPayrollUseCase {
    PayrollRecord execute(PayrollInput input);
}
