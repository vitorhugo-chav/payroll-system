package com.payroll.infrastructure.mapper;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;
import com.payroll.application.mapper.PayrollMapper;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.*;

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
    public PayrollResponse toResponse(Employee employee, PayrollRecord record) {
        return new PayrollResponse(
            employee.personalInfo().name().value(),
            employee.personalInfo().role().value(),
            record.baseSalary().value(),
            record.overtimeValue().value(),
            record.grossSalary().value(),
            record.inssDeduction().value(),
            record.irrfDeduction().value(),
            record.netSalary().value()
        );
    }
}
