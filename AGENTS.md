# Payroll System - Agent Guide

## Project Overview
Simple Java 21 console application for payroll calculation. No build tool (Maven/Gradle) — uses raw `javac`/`java`.

## Commands

**Compile:**
```bash
javac -d . src/com/payroll/*.java src/com/payroll/calculator/*.java src/com/payroll/model/*.java src/com/payroll/model/vo/*.java src/com/payroll/service/*.java src/com/payroll/strategy/*.java src/com/payroll/view/*.java
```

**Run:**
```bash
java com.payroll.Main
```

## Structure
```
src/com/payroll/
├── Main.java                 # Entry point, wires dependencies manually
├── model/
│   ├── Employee.java         # Domain entity
│   ├── PayrollRecord.java    # Immutable result
│   └── vo/                   # Value Objects
│       ├── Name.java
│       ├── Role.java
│       ├── Hours.java
│       ├── Money.java
│       ├── Dependents.java
│       ├── PersonalInfo.java
│       └── Contract.java
├── calculator/
│   ├── InssCalculator.java
│   ├── IrrfCalculator.java
│   └── OvertimeCalculator.java
├── service/
│   └── PayrollService.java   # Orchestrates calculations
├── strategy/
│   ├── Discount.java         # Interface
│   ├── InssDiscount.java     # Strategy implementation
│   ├── IrrfCalculation.java  # Interface
│   └── OvertimeCalculation.java # Interface
└── view/
    └── PayrollConsoleView.java
```

## Key Patterns
- **Strategy** for INSS discount (`Discount` interface)
- **Manual Dependency Injection** in `Main.java`
- **Single Responsibility** — each calculator/service has one job
- `PayrollRecord` is immutable result object

## Notes
- No tests exist in this project
- No linting/formatting tools configured
- `.class` files are gitignored; compile fresh after changes
- Java 21 required