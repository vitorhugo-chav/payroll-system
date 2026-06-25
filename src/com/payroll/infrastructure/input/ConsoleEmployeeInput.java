package com.payroll.infrastructure.input;

import com.payroll.application.dto.PayrollInput;
import java.util.Scanner;

public class ConsoleEmployeeInput {

    private final Scanner scanner;

    public ConsoleEmployeeInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public PayrollInput read() {
        System.out.println("--- Ficha do Funcionário ---");

        String name = readLine("Nome: ");
        String role = readLine("Cargo: ");
        double contractHours = readDouble("Horas Contratuais Mensais: ");
        double baseSalary = readDouble("Salário Base: ");
        double workedHours = readDouble("Total de Horas Trabalhadas: ");
        int dependents = (int) readDouble("Número de Dependentes: ");

        return new PayrollInput(name, role, contractHours, baseSalary, workedHours, dependents);
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        return readSafeDouble();
    }

    private double readSafeDouble() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Tente novamente: ");
            }
        }
    }
}
