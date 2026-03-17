# Sistema de Folha de Pagamento

Projeto universitário simples em Java para cálculo de folha de pagamento.

## Funcionalidades
- Cadastro de funcionário via console
- Cálculo de horas extras (fator 1.5×)
- Desconto INSS (tabela progressiva)
- Desconto IRRF (com dedução por dependentes)
- Exibição do contracheque

## Tecnologias
- Java 21
- Padrões: Strategy, Single Responsibility, Dependency Injection manual

## Estrutura do projeto
 .
└── 󰣞 src
    └──  com
        └──  payroll
            ├──  calculator
            │   ├──  InssCalculator.java
            │   ├──  IrrfCalculator.java
            │   └──  OvertimeCalculator.java
            ├──  Main.java
            ├──  model
            │   ├──  Employee.java
            │   └──  PayrollRecord.java
            ├──  service
            │   └──  PayrollService.java
            ├──  strategy
            │   ├──  Discount.java
            │   └──  InssDiscount.java
            └──  view
                └──  PayrollConsoleView.java


## Como executar

```bash
# Compilar (uma única vez ou quando alterar código)
javac -d . src/com/payroll/*.java src/com/payroll/calculator/*.java src/com/payroll/model/*.java src/com/payroll/service/*.java src/com/payroll/strategy/*.java src/com/payroll/view/*.java

# Rodar
java com.payroll.Main
