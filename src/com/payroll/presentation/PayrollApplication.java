package com.payroll.presentation;

import com.payroll.application.config.AppConfig;
import com.payroll.application.dto.EmployeeResponse;
import com.payroll.application.dto.PaySlipResponse;
import com.payroll.application.dto.PayrollInput;
import com.payroll.application.usecase.ProcessPayrollUseCase;
import com.payroll.infrastructure.input.ConsoleEmployeeInput;
import com.payroll.view.PayrollConsoleView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class PayrollApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            ProcessPayrollUseCase useCase = context.getBean(ProcessPayrollUseCase.class);
            ConsoleEmployeeInput input = new ConsoleEmployeeInput(scanner);
            PayrollConsoleView view = context.getBean(PayrollConsoleView.class);

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
}
