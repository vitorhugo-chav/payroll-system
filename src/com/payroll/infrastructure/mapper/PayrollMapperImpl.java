package com.payroll.infrastructure.mapper;

import com.payroll.application.dto.EmployeeResponse;
import com.payroll.application.dto.PaySlipResponse;
import com.payroll.application.dto.PayrollInput;
import com.payroll.application.mapper.PayrollMapper;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.*;
import java.util.List;

public class PayrollMapperImpl implements PayrollMapper {

    @Override
    public Employee toEmployee(PayrollInput input) {
        return new Employee(
            new Name(input.name()),
            new Role(input.role()),
            new Hours(input.contractHours()),
            new Money(input.baseSalary()),
            new Hours(input.workedHours()),
            new Dependents(input.dependents())
        );
    }

    @Override
    public PaySlipResponse toPaySlip(Employee employee, PayrollRecord record) {
        return PaySlipResponse.builder()
            .employeeName(employee.personalInfo().name().value())
            .employeeRole(employee.personalInfo().role().value())
            .baseSalary(record.baseSalary().value())
            .overtimeValue(record.overtimeValue().value())
            .grossSalary(record.grossSalary().value())
            .inssDeduction(record.inssDeduction().value())
            .irrfDeduction(record.irrfDeduction().value())
            .netSalary(record.netSalary().value())
            .build();
    }

    @Override
    public EmployeeResponse toSummary(Employee employee) {
        return EmployeeResponse.builder()
            .name(employee.personalInfo().name().value())
            .role(employee.personalInfo().role().value())
            .build();
    }

    @Override
    public List<EmployeeResponse> toSummaryList(List<Employee> employees) {
        return employees.stream()
            .map(this::toSummary)
            .toList();
    }
}
