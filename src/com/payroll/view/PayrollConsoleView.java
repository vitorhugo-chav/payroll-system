package com.payroll.view;

import com.payroll.application.dto.EmployeeResponse;
import com.payroll.application.dto.PaySlipResponse;
import java.util.List;

public class PayrollConsoleView {

    public void printPayStub(PaySlipResponse response) {
        System.out.println("\n========================================");
        System.out.println("           HOLERITE                     ");
        System.out.println("========================================");
        System.out.printf("Funcionário: %-25s%n", response.employeeName());
        System.out.printf("Cargo:       %-25s%n", response.employeeRole());
        System.out.println("----------------------------------------");

        System.out.printf("(+) Salário Base:      R$ %10.2f%n", response.baseSalary());
        System.out.printf("(+) Hora Extra (1.5x): R$ %10.2f%n", response.overtimeValue());
        System.out.println("----------------------------------------");

        System.out.printf("    SALÁRIO BRUTO:     R$ %10.2f%n", response.grossSalary());
        System.out.printf("(-) INSS:              R$ %10.2f%n", response.inssDeduction());
        System.out.printf("(-) IRRF:              R$ %10.2f%n", response.irrfDeduction());
        System.out.println("----------------------------------------");

        System.out.printf("(=) SALÁRIO LÍQUIDO:   R$ %10.2f%n", response.netSalary());
        System.out.println("========================================\n");
    }

    public void printEmployeeList(List<EmployeeResponse> employees) {
        if (employees.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.\n");
            return;
        }
        System.out.println("\n=== Funcionários Cadastrados ===");
        for (int i = 0; i < employees.size(); i++) {
            EmployeeResponse emp = employees.get(i);
            System.out.printf("%d. %-20s | %s%n", i + 1, emp.name(), emp.role());
        }
        System.out.println();
    }

    public void printMenu() {
        System.out.println("=== Sistema de Folha de Pagamento ===");
        System.out.println("1. Processar novo funcionário");
        System.out.println("2. Listar funcionários");
        System.out.println("3. Sair");
        System.out.print("Escolha: ");
    }
}
