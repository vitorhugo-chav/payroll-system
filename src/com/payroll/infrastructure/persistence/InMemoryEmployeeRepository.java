package com.payroll.infrastructure.persistence;

import com.payroll.domain.port.EmployeeRepository;
import com.payroll.model.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void save(Employee employee) {
        employees.add(employee);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
