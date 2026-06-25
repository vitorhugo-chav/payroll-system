package com.payroll.view;

import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;

public class PayrollConsoleView {

    public void printPayStub(PayrollRecord record) {
        Employee emp = record.employee();

        System.out.println("\n========================================");
        System.out.println("           HOLERITE                     ");
        System.out.println("========================================");
        System.out.printf("Funcionário: %-25s%n", emp.personalInfo().name().value());
        System.out.printf("Cargo:       %-25s%n", emp.personalInfo().role().value());
        System.out.println("----------------------------------------");

        System.out.printf("(+) Salário Base:      R$ %10.2f%n", record.baseSalary().value());
        System.out.printf("(+) Hora Extra (1.5x): R$ %10.2f%n", record.overtimeValue().value());
        System.out.println("----------------------------------------");

        System.out.printf("    SALÁRIO BRUTO:     R$ %10.2f%n", record.grossSalary().value());
        System.out.printf("(-) INSS:              R$ %10.2f%n", record.inssDeduction().value());
        System.out.printf("(-) IRRF:              R$ %10.2f%n", record.irrfDeduction().value());
        System.out.println("----------------------------------------");

        System.out.printf("(=) SALÁRIO LÍQUIDO:   R$ %10.2f%n", record.netSalary().value());
        System.out.println("========================================\n");
    }
}
