package com.payroll;

import com.payroll.calculator.InssCalculator;
import com.payroll.calculator.IrrfCalculator;
import com.payroll.calculator.OvertimeCalculator;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.model.vo.*;
import com.payroll.service.PayrollService;
import com.payroll.strategy.Discount;
import com.payroll.strategy.InssDiscount;
import com.payroll.view.PayrollConsoleView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Employee employee = readEmployeeData(scanner);
            PayrollRecord finalRecord = processPayroll(employee);
            displayPayStub(finalRecord);

        } catch (IllegalArgumentException e) {
            System.err.println("\nErro de Validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nErro Inesperado: " + e.getMessage());
        }
    }

    private static Employee readEmployeeData(Scanner scanner) {
        System.out.println("--- Ficha do Funcionário ---");

        Name name = readName(scanner);
        Role role = readRole(scanner);
        Hours contractHours = readContractHours(scanner);
        Money baseSalary = readBaseSalary(scanner);
        Hours workedHours = readWorkedHours(scanner);
        Dependents dependents = readDependents(scanner);

        return new Employee(name, role, contractHours, baseSalary, workedHours, dependents);
    }

    private static Name readName(Scanner scanner) {
        System.out.print("Nome: ");
        String input = scanner.nextLine();
        return new Name(input);
    }

    private static Role readRole(Scanner scanner) {
        System.out.print("Cargo: ");
        String input = scanner.nextLine();
        return new Role(input);
    }

    private static Hours readContractHours(Scanner scanner) {
        System.out.print("Horas Contratuais Mensais: ");
        double value = readSafeDouble(scanner, "Valor inválido. Tente novamente: ");
        return new Hours(value);
    }

    private static Money readBaseSalary(Scanner scanner) {
        System.out.print("Salário Base: ");
        double value = readSafeDouble(scanner, "Valor inválido. Tente novamente: ");
        return new Money(value);
    }

    private static Hours readWorkedHours(Scanner scanner) {
        System.out.print("Total de Horas Trabalhadas: ");
        double value = readSafeDouble(scanner, "Valor inválido. Tente novamente: ");
        return new Hours(value);
    }

    private static Dependents readDependents(Scanner scanner) {
        System.out.print("Número de Dependentes: ");
        int value = (int) readSafeDouble(scanner, "Valor inválido. Tente novamente: ");
        return new Dependents(value);
    }

    private static PayrollRecord processPayroll(Employee employee) {
        InssCalculator inssCalc = new InssCalculator();
        Discount inssDiscount = new InssDiscount(inssCalc);
        OvertimeCalculator overtimeCalc = new OvertimeCalculator();
        IrrfCalculator irrfCalc = new IrrfCalculator();

        PayrollService payrollService = new PayrollService(overtimeCalc, inssDiscount, irrfCalc);
        return payrollService.process(employee);
    }

    private static void displayPayStub(PayrollRecord record) {
        PayrollConsoleView view = new PayrollConsoleView();
        view.printPayStub(record);
    }

    private static double readSafeDouble(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.print(errorMessage);
            }
        }
    }
}
