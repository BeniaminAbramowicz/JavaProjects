package com.kostka.repository;

import com.kostka.enums.ContractType;
import com.kostka.model.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class EmployeeRepository {

    public static List<Employee> employeeList(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1, "franek", "noob", "43535", new BigDecimal(1231), ContractType.UoP);
        Employee employee2 = new Employee(2, "kostka", "osiem", "657768", new BigDecimal(5365), ContractType.UoP);
        Employee employee3 = new Employee(3, "Jan", "Nowak", "234675", new BigDecimal(150), ContractType.B2B);
        Employee employee4 = new Employee(4, "Paweł", "Korek", "235666666", new BigDecimal(200), ContractType.B2B);

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);

        System.out.println(employeeList);

        return employeeList;
    }

}
