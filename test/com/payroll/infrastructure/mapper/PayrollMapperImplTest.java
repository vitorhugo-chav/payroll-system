package com.payroll.infrastructure.mapper;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PayrollMapperImplTest {

    private final PayrollMapperImpl mapper = new PayrollMapperImpl();

    @Test
    void shouldMapInputToEmployee() {
        PayrollInput input = new PayrollInput("João", "Dev", 220, 5000, 230, 2);
        Employee employee = mapper.toEmployee(input);

        assertEquals("João", employee.personalInfo().name().value());
        assertEquals("Dev", employee.personalInfo().role().value());
        assertEquals(220, employee.contract().monthlyContractHours().value());
        assertEquals(5000, employee.contract().baseSalary().value());
        assertEquals(230, employee.actualWorkedHours().value());
        assertEquals(2, employee.dependents().value());
    }

    @Test
    void shouldMapEmployeeAndRecordToResponse() {
        Employee employee = new Employee(
            new Name("Maria"),
            new Role("Analista"),
            new Hours(220),
            new Money(5500),
            new Hours(220),
            new Dependents(1)
        );

        PayrollRecord record = new PayrollRecord(
            employee,
            new Money(5500),
            Money.ZERO,
            new Money(5500),
            new Money(500),
            new Money(200),
            new Money(4800)
        );

        PayrollResponse response = mapper.toResponse(employee, record);

        assertEquals("Maria", response.employeeName());
        assertEquals("Analista", response.employeeRole());
        assertEquals(5500, response.baseSalary());
        assertEquals(0, response.overtimeValue());
        assertEquals(5500, response.grossSalary());
        assertEquals(500, response.inssDeduction());
        assertEquals(200, response.irrfDeduction());
        assertEquals(4800, response.netSalary());
    }
}
