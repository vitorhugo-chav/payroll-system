package com.payroll.view;

import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;

/**
 * Handles the display of payroll information to the console.
 * It has NO business logic, strictly adhering to the Single Responsibility Principle.
 */
public class PayrollConsoleView {
    
    /**
     * Prints the formatted pay stub to the standard output.
     * @param record The pre-calculated payroll data (DTO)
     */
    public void printPayStub(PayrollRecord record) {
        Employee emp = record.getEmployee();

        System.out.println("\n========================================");
        System.out.println("           PAYROLL PAY STUB             ");
        System.out.println("========================================");
        System.out.printf("Employee:    %-25s%n", emp.getName());
        System.out.printf("Role:        %-25s%n", emp.getRole());
        System.out.println("----------------------------------------");
        
        System.out.printf("(+) Base Salary:       R$ %10.2f%n", record.getBaseSalary());
        System.out.printf("(+) Overtime (1.5x):   R$ %10.2f%n", record.getOvertimeValue());
        System.out.println("----------------------------------------");
        
        System.out.printf("    GROSS SALARY:      R$ %10.2f%n", record.getGrossSalary());
        System.out.printf("(-) INSS Deduction:    R$ %10.2f%n", record.getInssDeduction());
        System.out.printf("(-) IRRF Deduction:    R$ %10.2f%n", record.getIrrfDeduction());
        System.out.println("----------------------------------------");
        
        System.out.printf("(=) NET SALARY:        R$ %10.2f%n", record.getNetSalary());
        System.out.println("========================================\n");
    }
}
