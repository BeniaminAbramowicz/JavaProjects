package com.kostka.dao;

import com.kostka.model.Employee;
import com.kostka.repository.EmployeeRepository;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{

    private List<Employee> employees = EmployeeRepository.employeeList();

    @Override
    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }

}
