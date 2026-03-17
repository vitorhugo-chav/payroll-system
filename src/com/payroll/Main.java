package com.payroll;

import com.payroll.calculator.InssCalculator;
import com.payroll.calculator.IrrfCalculator;
import com.payroll.calculator.OvertimeCalculator;
import com.payroll.model.Employee;
import com.payroll.model.PayrollRecord;
import com.payroll.service.PayrollService;
import com.payroll.strategy.Discount;
import com.payroll.strategy.InssDiscount;
import com.payroll.view.PayrollConsoleView;

import java.util.Scanner;

/**
 * The entry point of the application.
 * Responsible for gathering user input and wiring the dependencies together.
 */
public class Main {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("--- Employee Payroll Entry ---");

            System.out.print("Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Role: ");
            String role = scanner.nextLine().trim();

            System.out.print("Monthly Contract Hours: ");
            double contractHours = readSafeDouble(scanner, "Invalid hours. Try again: ");

            System.out.print("Base Salary: ");
            double baseSalary = readSafeDouble(scanner, "Invalid salary. Try again: ");

            System.out.print("Total Hours Worked: ");
            double workedHours = readSafeDouble(scanner, "Invalid hours. Try again: ");

            System.out.print("Number of Dependents: ");
            int dependents = (int) readSafeDouble(scanner, "");

            // 1. Create the Domain Entity
            Employee employee = new Employee(name, role, contractHours, baseSalary, workedHours, dependents);

            // 2. Setup Dependencies
            InssCalculator inssCalc = new InssCalculator();
            Discount inssDiscount = new InssDiscount(inssCalc);
            OvertimeCalculator overtimeCalc = new OvertimeCalculator();
            IrrfCalculator irrfCalc = new IrrfCalculator();

            // 3. Instantiante the Sevice eith inject dependecies
            PayrollService payrollService = new PayrollService(overtimeCalc, inssDiscount, irrfCalc);

            // 4. Process the rules and get the immutable result
            PayrollRecord finalRecord = payrollService.process(employee);
            
            // 5. Process the rules and get the immutable result
            PayrollConsoleView view = new PayrollConsoleView();
            view.printPayStub(finalRecord);

        } catch (IllegalArgumentException e) {
            System.err.println("\nValidation Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nUnexpected System Error: " + e.getMessage());
        }
    }

    /**
     * Helper method to safely read double values from the console.
     */
    private static double readSafeDouble(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print(errorMessage);
            }
        }
    }
}
