# Payroll System - Agent Guide

## Project Overview
Simple Java 21 console application for payroll calculation. No build tool (Maven/Gradle) — layered architecture with manual DI.

## Commands

**Compile (with JDK):**
```bash
javac -d bin src/com/payroll/presentation/PayrollApplication.java src/com/payroll/calculator/*.java src/com/payroll/model/*.java src/com/payroll/model/vo/*.java src/com/payroll/service/*.java src/com/payroll/strategy/*.java src/com/payroll/view/*.java src/com/payroll/domain/port/*.java src/com/payroll/application/dto/*.java src/com/payroll/application/usecase/*.java src/com/payroll/application/mapper/*.java src/com/payroll/infrastructure/persistence/*.java src/com/payroll/infrastructure/input/*.java src/com/payroll/infrastructure/mapper/*.java
```

**Run:**
```bash
java -cp bin com.payroll.presentation.PayrollApplication
```

**Compile tests:**
```bash
javac -cp "test-lib/junit-platform-console-standalone-1.11.0.jar:bin" -d bin test/com/payroll/calculator/*.java test/com/payroll/service/*.java test/com/payroll/application/usecase/*.java test/com/payroll/infrastructure/mapper/*.java
```

**Run tests:**
```bash
java -cp "test-lib/junit-platform-console-standalone-1.11.0.jar:bin" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Layers & Structure

```
src/com/payroll/
├── presentation/                 # Entry point (composition root)
│   └── PayrollApplication.java

├── domain/                       # Domain Layer — business rules only
│   └── port/
│       └── EmployeeRepository.java  # Output port interface

├── application/                  # Application Layer — use cases, DTOs
│   ├── dto/
│   │   ├── PayrollInput.java
│   │   ├── PaySlipResponse.java          # Builder pattern
│   │   └── EmployeeResponse.java          # Builder pattern
│   ├── mapper/
│   │   └── PayrollMapper.java             # Mapper interface
│   └── usecase/
│       ├── ProcessPayrollUseCase.java      # Input port interface
│       └── ProcessPayrollUseCaseImpl.java  # Use case implementation

├── infrastructure/               # Infrastructure Layer — adapters
│   ├── input/
│   │   └── ConsoleEmployeeInput.java      # Reads console input
│   ├── mapper/
│   │   └── PayrollMapperImpl.java         # Mapper implementation
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

└── view/                         # Presentation output (adapter)
    └── PayrollConsoleView.java

test/com/payroll/                 # Unit tests
├── calculator/
│   ├── InssCalculatorTest.java
│   ├── IrrfCalculatorTest.java
│   └── OvertimeCalculatorTest.java
├── service/
│   └── PayrollServiceTest.java
├── application/usecase/
│   └── ProcessPayrollUseCaseImplTest.java
└── infrastructure/mapper/
    └── PayrollMapperImplTest.java
```

## Architecture Diagram

```
┌──────────────────────────────────────────────────────┐
│  Presentation Layer                                  │
│  PayrollApplication.java  +  PayrollConsoleView      │
│  (ConsoleEmployeeInput)                              │
├──────────────────────────────────────────────────────┤
│  Application Layer                                   │
│  ProcessPayrollUseCaseImpl  ←→  PayrollInput         │
│                              →  PayrollResponse      │
│  ─── depends on ───> PayrollService + Repository     │
│  ─── uses ───> PayrollMapper                        │
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
- **View** receives DTO (`PayrollResponse`), never touches domain entities

## Key Patterns
- **Hexagonal Architecture** (ports & adapters)
- **Strategy** for INSS discount (`Discount` interface)
- **Manual Dependency Injection** in `PayrollApplication.java`
- **Single Responsibility** — each class has one job
- **Value Objects** — primitives wrapped in domain-meaningful types
- **Immutability** — `Employee` and `PayrollRecord` are immutable
- **DTO Pattern** — `PayrollInput` / `PaySlipResponse` / `EmployeeResponse` shield domain from external layers
- **Builder Pattern** — fluent builders on `PaySlipResponse` and `EmployeeResponse`
- **Mapper Pattern** — `PayrollMapper` interface (`application`) + `PayrollMapperImpl` (`infrastructure`)

## Notes
- 27 unit tests covering calculators, service, use case, and mapper
- No linting/formatting tools configured
- `.class` and `test-lib/` files are gitignored; compile fresh after changes
- Java 21 required
- JUnit 5.11 required for tests (in `test-lib/`)
