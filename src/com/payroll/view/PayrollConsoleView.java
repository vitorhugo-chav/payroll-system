package com.payroll.view;

import com.payroll.application.dto.PayrollResponse;

public class PayrollConsoleView {

    public void printPayStub(PayrollResponse response) {
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
}
