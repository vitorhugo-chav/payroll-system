package com.payroll.application.usecase;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;
import com.payroll.domain.port.EmployeeRepository;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.*;
import com.payroll.service.PayrollService;

public class ProcessPayrollUseCaseImpl implements ProcessPayrollUseCase {

    private final PayrollService payrollService;
    private final EmployeeRepository employeeRepository;

    public ProcessPayrollUseCaseImpl(PayrollService payrollService, EmployeeRepository employeeRepository) {
        this.payrollService = payrollService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public PayrollResponse execute(PayrollInput input) {
        Employee employee = new Employee(
            new Name(input.name()),
            new Role(input.role()),
            new Hours(input.contractHours()),
            new Money(input.baseSalary()),
            new Hours(input.workedHours()),
            new Dependents(input.dependents())
        );

        employeeRepository.save(employee);

        PayrollRecord record = payrollService.process(employee);
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
