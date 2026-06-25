package com.payroll.infrastructure.mapper;

import com.payroll.application.dto.EmployeeResponse;
import com.payroll.application.dto.PaySlipResponse;
import com.payroll.application.dto.PayrollInput;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.*;
import org.junit.jupiter.api.Test;
import java.util.List;
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
    void shouldMapEmployeeAndRecordToPaySlip() {
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

        PaySlipResponse response = mapper.toPaySlip(employee, record);

        assertEquals("Maria", response.employeeName());
        assertEquals("Analista", response.employeeRole());
        assertEquals(5500, response.baseSalary());
        assertEquals(0, response.overtimeValue());
        assertEquals(5500, response.grossSalary());
        assertEquals(500, response.inssDeduction());
        assertEquals(200, response.irrfDeduction());
        assertEquals(4800, response.netSalary());
    }

    @Test
    void shouldMapEmployeeToSummary() {
        Employee employee = new Employee(
            new Name("Carlos"),
            new Role("Dev"),
            new Hours(220),
            new Money(5000),
            new Hours(220),
            new Dependents(0)
        );

        EmployeeResponse summary = mapper.toSummary(employee);

        assertEquals("Carlos", summary.name());
        assertEquals("Dev", summary.role());
    }

    @Test
    void shouldMapEmployeeListToSummaryList() {
        List<Employee> employees = List.of(
            new Employee(new Name("A"), new Role("R1"), new Hours(220), new Money(3000), new Hours(220), new Dependents(0)),
            new Employee(new Name("B"), new Role("R2"), new Hours(200), new Money(4000), new Hours(200), new Dependents(1))
        );

        List<EmployeeResponse> summaries = mapper.toSummaryList(employees);

        assertEquals(2, summaries.size());
        assertEquals("A", summaries.get(0).name());
        assertEquals("R2", summaries.get(1).role());
    }

    @Test
    void shouldBuildPaySlipResponseWithBuilder() {
        PaySlipResponse response = PaySlipResponse.builder()
            .employeeName("Teste")
            .employeeRole("Dev")
            .baseSalary(5000)
            .overtimeValue(500)
            .grossSalary(5500)
            .inssDeduction(500)
            .irrfDeduction(200)
            .netSalary(4800)
            .build();

        assertEquals("Teste", response.employeeName());
        assertEquals(4800, response.netSalary());
    }

    @Test
    void shouldBuildEmployeeResponseWithBuilder() {
        EmployeeResponse response = EmployeeResponse.builder()
            .name("Joana")
            .role("Gerente")
            .build();

        assertEquals("Joana", response.name());
        assertEquals("Gerente", response.role());
    }
}
