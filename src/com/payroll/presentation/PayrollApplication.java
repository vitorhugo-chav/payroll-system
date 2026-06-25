package com.payroll.presentation;

import com.payroll.application.dto.PayrollInput;
import com.payroll.application.dto.PayrollResponse;
import com.payroll.application.usecase.ProcessPayrollUseCase;
import com.payroll.application.usecase.ProcessPayrollUseCaseImpl;
import com.payroll.calculator.InssCalculator;
import com.payroll.calculator.IrrfCalculator;
import com.payroll.calculator.OvertimeCalculator;
import com.payroll.domain.port.EmployeeRepository;
import com.payroll.infrastructure.input.ConsoleEmployeeInput;
import com.payroll.infrastructure.persistence.InMemoryEmployeeRepository;
import com.payroll.service.PayrollService;
import com.payroll.strategy.Discount;
import com.payroll.strategy.InssDiscount;
import com.payroll.view.PayrollConsoleView;

import java.util.Scanner;

public class PayrollApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ProcessPayrollUseCase useCase = buildUseCase();
            ConsoleEmployeeInput input = new ConsoleEmployeeInput(scanner);
            PayrollConsoleView view = new PayrollConsoleView();

            PayrollInput data = input.read();
            PayrollResponse response = useCase.execute(data);
            view.printPayStub(response);

        } catch (IllegalArgumentException e) {
            System.err.println("\nErro de Validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nErro Inesperado: " + e.getMessage());
        }
    }

    private static ProcessPayrollUseCase buildUseCase() {
        InssCalculator inssCalc = new InssCalculator();
        Discount inssDiscount = new InssDiscount(inssCalc);
        OvertimeCalculator overtimeCalc = new OvertimeCalculator();
        IrrfCalculator irrfCalc = new IrrfCalculator();

        PayrollService payrollService = new PayrollService(overtimeCalc, inssDiscount, irrfCalc);
        EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();

        return new ProcessPayrollUseCaseImpl(payrollService, employeeRepository);
    }
}
