package com.kostka;

import com.kostka.enums.ContractType;
import com.kostka.model.Employee;
import com.kostka.model.Invoice;
import com.kostka.repository.EmployeeRepository;
import com.kostka.service.EmployeeActions;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee em = new Employee(5,"lol","lul", "345", new BigDecimal(234), ContractType.B2B);
        Invoice in = new Invoice("123", new BigDecimal(324), "435345", new LocalDate(2019, 03, 27));

        EmployeeActions.paySalary();
    }
}
