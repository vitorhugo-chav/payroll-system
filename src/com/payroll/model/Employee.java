package com.payroll.model;

import com.payroll.model.vo.*;

public class Employee {

    private final PersonalInfo personalInfo;
    private final Contract contract;
    private final Hours actualWorkedHours;
    private final Dependents dependents;

    public Employee(Name name, Role role, Hours monthlyContractHours, Money baseSalary, Hours actualWorkedHours, Dependents dependents) {
        this.personalInfo = new PersonalInfo(name, role);
        this.contract = new Contract(monthlyContractHours, baseSalary);
        this.actualWorkedHours = actualWorkedHours;
        this.dependents = dependents;
    }

    public PersonalInfo personalInfo() { return personalInfo; }
    public Contract contract() { return contract; }
    public Hours actualWorkedHours() { return actualWorkedHours; }
    public Dependents dependents() { return dependents; }
}
