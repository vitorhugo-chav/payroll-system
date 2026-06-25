package com.payroll.application.usecase;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;
import com.payroll.application.mapper.PayrollMapper;
import com.payroll.domain.port.EmployeeRepository;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.service.PayrollService;

public class ProcessPayrollUseCaseImpl implements ProcessPayrollUseCase {

    private final PayrollService payrollService;
    private final EmployeeRepository employeeRepository;
    private final PayrollMapper mapper;

    public ProcessPayrollUseCaseImpl(PayrollService payrollService, EmployeeRepository employeeRepository, PayrollMapper mapper) {
        this.payrollService = payrollService;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public PayrollResponse execute(PayrollInput input) {
        Employee employee = mapper.toEmployee(input);
        employeeRepository.save(employee);
        PayrollRecord record = payrollService.process(employee);
        return mapper.toResponse(employee, record);
    }
}
