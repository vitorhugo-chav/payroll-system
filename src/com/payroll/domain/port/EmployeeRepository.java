package com.payroll.domain.port;

import com.payroll.model.Employee;
import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);
    List<Employee> findAll();
}
