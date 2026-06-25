# Payroll System - Agent Guide

## Project Overview
Simple Java 21 console application for payroll calculation. No build tool (Maven/Gradle) — layered architecture with manual DI.

## Commands

**Compile (with JDK):**
```bash
javac -d . src/com/payroll/Main.java src/com/payroll/calculator/*.java src/com/payroll/model/*.java src/com/payroll/model/vo/*.java src/com/payroll/service/*.java src/com/payroll/strategy/*.java src/com/payroll/view/*.java src/com/payroll/domain/port/*.java src/com/payroll/application/dto/*.java src/com/payroll/application/usecase/*.java src/com/payroll/infrastructure/persistence/*.java src/com/payroll/infrastructure/input/*.java
```

**Run:**
```bash
java com.payroll.Main
```

## Layers & Structure

```
src/com/payroll/
├── Main.java                     # Composition root (presentation layer)

├── domain/                       # Domain Layer — business rules only
│   └── port/
│       └── EmployeeRepository.java  # Output port interface

├── application/                  # Application Layer — use cases, DTOs
│   ├── dto/
│   │   └── PayrollInput.java
│   └── usecase/
│       ├── ProcessPayrollUseCase.java      # Input port interface
│       └── ProcessPayrollUseCaseImpl.java  # Use case implementation

├── infrastructure/               # Infrastructure Layer — adapters
│   ├── input/
│   │   └── ConsoleEmployeeInput.java      # Reads console input
│   └── persistence/
│       └── InMemoryEmployeeRepository.java # In-memory repo

├── model/                        # Domain entities
│   ├── Employee.java
│   ├── PayrollRecord.java
│   └── vo/                       # Value Objects
│       ├── Name.java
│       ├── Role.java
│       ├── Hours.java
│       ├── Money.java
│       ├── Dependents.java
│       ├── PersonalInfo.java
│       └── Contract.java

├── calculator/                   # Domain calculation rules
│   ├── InssCalculator.java
│   ├── IrrfCalculator.java
│   └── OvertimeCalculator.java

├── service/                      # Domain service
│   └── PayrollService.java

├── strategy/                     # Strategy interfaces + adapters
│   ├── Discount.java
│   ├── InssDiscount.java
│   ├── IrrfCalculation.java
│   └── OvertimeCalculation.java

└── view/                         # Presentation output
    └── PayrollConsoleView.java
```

## Architecture Diagram

```
┌──────────────────────────────────────────────────────┐
│  Presentation Layer                                  │
│  Main.java  +  PayrollConsoleView                    │
│  (ConsoleEmployeeInput)                              │
├──────────────────────────────────────────────────────┤
│  Application Layer                                   │
│  ProcessPayrollUseCaseImpl                           │
│  ─── depends on ───> PayrollService + Repository     │
├──────────────────────────────────────────────────────┤
│  Domain Layer                                        │
│  Model / VOs / Service / Strategy / Calculator       │
│  EmployeeRepository (interface)                      │
├──────────────────────────────────────────────────────┤
│  Infrastructure Layer                                │
│  InMemoryEmployeeRepository                          │
│  ConsoleEmployeeInput (adapter)                      │
└──────────────────────────────────────────────────────┘
```

## Dependency Flow
- **Presentation** → **Application** (use case interface)
- **Application** → **Domain** (model, service) + **Domain Port** (repository interface)
- **Infrastructure** implements **Domain Port** (repository)
- **Domain** has zero dependencies on outside layers

## Key Patterns
- **Hexagonal Architecture** (ports & adapters)
- **Strategy** for INSS discount (`Discount` interface)
- **Manual Dependency Injection** in `Main.java`
- **Single Responsibility** — each class has one job
- **Value Objects** — primitives wrapped in domain-meaningful types
- **Immutability** — `Employee` and `PayrollRecord` are immutable

## Notes
- No tests exist in this project
- No linting/formatting tools configured
- `.class` files are gitignored; compile fresh after changes
- Java 21 required
