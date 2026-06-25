package com.payroll.application.mapper;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;

public interface PayrollMapper {
    Employee toEmployee(PayrollInput input);
    PayrollResponse toResponse(Employee employee, PayrollRecord record);
}
