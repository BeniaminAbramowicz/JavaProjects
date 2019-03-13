package com.kostka.dao;

import com.kostka.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee addEmployee(Employee employee);

    List<Employee> getEmployees();

}
