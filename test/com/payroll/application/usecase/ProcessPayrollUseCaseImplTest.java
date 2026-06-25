package com.payroll.application.usecase;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;
import com.payroll.application.mapper.PayrollMapper;
import com.payroll.calculator.InssCalculator;
import com.payroll.calculator.IrrfCalculator;
import com.payroll.calculator.OvertimeCalculator;
import com.payroll.domain.port.EmployeeRepository;
import com.payroll.infrastructure.mapper.PayrollMapperImpl;
import com.payroll.infrastructure.persistence.InMemoryEmployeeRepository;
import com.payroll.service.PayrollService;
import com.payroll.strategy.Discount;
import com.payroll.strategy.InssDiscount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProcessPayrollUseCaseImplTest {

    private final ProcessPayrollUseCase useCase;
    private final PayrollMapper mapper;

    ProcessPayrollUseCaseImplTest() {
        InssCalculator inssCalc = new InssCalculator();
        Discount inssDiscount = new InssDiscount(inssCalc);
        OvertimeCalculator overtimeCalc = new OvertimeCalculator();
        IrrfCalculator irrfCalc = new IrrfCalculator();
        PayrollService payrollService = new PayrollService(overtimeCalc, inssDiscount, irrfCalc);
        EmployeeRepository repository = new InMemoryEmployeeRepository();
        mapper = new PayrollMapperImpl();
        useCase = new ProcessPayrollUseCaseImpl(payrollService, repository, mapper);
    }

    @Test
    void shouldExecuteUseCaseAndReturnResponse() {
        PayrollInput input = new PayrollInput("Ana", "Gerente", 220, 8000, 240, 1);
        PayrollResponse response = useCase.execute(input);

        assertEquals("Ana", response.employeeName());
        assertEquals("Gerente", response.employeeRole());
        assertTrue(response.netSalary() > 0);
    }

    @Test
    void shouldSaveEmployeeInRepository() {
        InMemoryEmployeeRepository repo = new InMemoryEmployeeRepository();
        InssCalculator inssCalc = new InssCalculator();
        Discount inssDiscount = new InssDiscount(inssCalc);
        OvertimeCalculator overtimeCalc = new OvertimeCalculator();
        IrrfCalculator irrfCalc = new IrrfCalculator();
        PayrollService payrollService = new PayrollService(overtimeCalc, inssDiscount, irrfCalc);
        ProcessPayrollUseCase useCaseWithRepo = new ProcessPayrollUseCaseImpl(payrollService, repo, mapper);

        useCaseWithRepo.execute(new PayrollInput("Carlos", "Dev", 220, 4000, 220, 0));

        assertEquals(1, repo.findAll().size());
        assertEquals("Carlos", repo.findAll().get(0).personalInfo().name().value());
    }
}
