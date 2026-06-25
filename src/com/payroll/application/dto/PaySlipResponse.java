package com.payroll.application.dto;

public record PaySlipResponse(
    String employeeName,
    String employeeRole,
    double baseSalary,
    double overtimeValue,
    double grossSalary,
    double inssDeduction,
    double irrfDeduction,
    double netSalary
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String employeeName;
        private String employeeRole;
        private double baseSalary;
        private double overtimeValue;
        private double grossSalary;
        private double inssDeduction;
        private double irrfDeduction;
        private double netSalary;

        public Builder employeeName(String employeeName) { this.employeeName = employeeName; return this; }
        public Builder employeeRole(String employeeRole) { this.employeeRole = employeeRole; return this; }
        public Builder baseSalary(double baseSalary) { this.baseSalary = baseSalary; return this; }
        public Builder overtimeValue(double overtimeValue) { this.overtimeValue = overtimeValue; return this; }
        public Builder grossSalary(double grossSalary) { this.grossSalary = grossSalary; return this; }
        public Builder inssDeduction(double inssDeduction) { this.inssDeduction = inssDeduction; return this; }
        public Builder irrfDeduction(double irrfDeduction) { this.irrfDeduction = irrfDeduction; return this; }
        public Builder netSalary(double netSalary) { this.netSalary = netSalary; return this; }
        public PaySlipResponse build() {
            return new PaySlipResponse(employeeName, employeeRole, baseSalary, overtimeValue,
                grossSalary, inssDeduction, irrfDeduction, netSalary);
        }
    }
}
