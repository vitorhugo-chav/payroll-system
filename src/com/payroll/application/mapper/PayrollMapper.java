package com.payroll.application.mapper;

import com.payroll.application.dto.EmployeeResponse;
import com.payroll.application.dto.PaySlipResponse;
import com.payroll.application.dto.PayrollInput;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import java.util.List;

public interface PayrollMapper {
    Employee toEmployee(PayrollInput input);
    PaySlipResponse toPaySlip(Employee employee, PayrollRecord record);
    EmployeeResponse toSummary(Employee employee);
    List<EmployeeResponse> toSummaryList(List<Employee> employees);
}
