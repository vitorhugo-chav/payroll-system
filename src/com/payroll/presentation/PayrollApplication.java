package com.payroll.presentation;

import com.payroll.application.dto.EmployeeResponse;
import com.payroll.application.dto.PaySlipResponse;
import com.payroll.application.dto.PayrollInput;
import com.payroll.application.mapper.PayrollMapper;
import com.payroll.application.usecase.ProcessPayrollUseCase;
import com.payroll.application.usecase.ProcessPayrollUseCaseImpl;
import com.payroll.calculator.InssCalculator;
import com.payroll.calculator.IrrfCalculator;
import com.payroll.calculator.OvertimeCalculator;
import com.payroll.domain.port.EmployeeRepository;
import com.payroll.infrastructure.input.ConsoleEmployeeInput;
import com.payroll.infrastructure.mapper.PayrollMapperImpl;
import com.payroll.infrastructure.persistence.InMemoryEmployeeRepository;
import com.payroll.service.PayrollService;
import com.payroll.strategy.Discount;
import com.payroll.strategy.InssDiscount;
import com.payroll.view.PayrollConsoleView;

import java.util.List;
import java.util.Scanner;

public class PayrollApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ProcessPayrollUseCase useCase = buildUseCase();
            ConsoleEmployeeInput input = new ConsoleEmployeeInput(scanner);
            PayrollConsoleView view = new PayrollConsoleView();

            boolean running = true;
            while (running) {
                view.printMenu();
                String option = scanner.nextLine().trim();
                switch (option) {
                    case "1" -> processEmployee(useCase, input, view);
                    case "2" -> listEmployees(useCase, view);
                    case "3" -> running = false;
                    default -> System.out.println("Opção inválida.\n");
                }
            }

        } catch (IllegalArgumentException e) {
            System.err.println("\nErro de Validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nErro Inesperado: " + e.getMessage());
        }
    }

    private static void processEmployee(ProcessPayrollUseCase useCase, ConsoleEmployeeInput input, PayrollConsoleView view) {
        PayrollInput data = input.read();
        PaySlipResponse response = useCase.execute(data);
        view.printPayStub(response);
    }

    private static void listEmployees(ProcessPayrollUseCase useCase, PayrollConsoleView view) {
        List<EmployeeResponse> employees = useCase.listEmployees();
        view.printEmployeeList(employees);
    }

    private static ProcessPayrollUseCase buildUseCase() {
        InssCalculator inssCalc = new InssCalculator();
        Discount inssDiscount = new InssDiscount(inssCalc);
        OvertimeCalculator overtimeCalc = new OvertimeCalculator();
        IrrfCalculator irrfCalc = new IrrfCalculator();

        PayrollService payrollService = new PayrollService(overtimeCalc, inssDiscount, irrfCalc);
        EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
        PayrollMapper mapper = new PayrollMapperImpl();

        return new ProcessPayrollUseCaseImpl(payrollService, employeeRepository, mapper);
    }
}
