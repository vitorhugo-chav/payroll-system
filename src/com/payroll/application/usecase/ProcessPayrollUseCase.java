package com.payroll.application.usecase;

import com.payroll.application.dto.EmployeeResponse;
import com.payroll.application.dto.PaySlipResponse;
import com.payroll.application.dto.PayrollInput;
import java.util.List;

public interface ProcessPayrollUseCase {
    PaySlipResponse execute(PayrollInput input);
    List<EmployeeResponse> listEmployees();
}
