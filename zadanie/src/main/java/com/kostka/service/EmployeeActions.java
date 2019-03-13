package com.kostka.service;

import com.kostka.enums.ContractType;
import com.kostka.model.Employee;
import com.kostka.model.Invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;

public abstract class EmployeeActions {


    public void static paySalary(Employee employee, Invoice invoice, Integer delegationDays) {
        int id = employee.getIdEmp();
        int days = invoice.getDaysWorked();
        int dd = delegationDays;
        BigDecimal sal = employee.getSalary();
        ContractType ct = employee.getContractType();
        calculateBonus(id, days, dd, sal, ct);
        if(employee.getContractType() == ContractType.UoP){
            LocalDate today = LocalDate.now();
            LocalDate lastDay = YearMonth.now().atEndOfMonth();
            boolean check = today.equals(lastDay);
            if(check){
                System.out.println("Zapłacono");
            } else {
                System.out.println("Nie można wypłacić pensji dzisiejszego dnia");
            }
        } else {
            LocalDate four = invoice.getDate();
            LocalDate todayPlusFourteen = LocalDate.now().minusDays(14L);
            if(four.isAfter(todayPlusFourteen)){
                System.out.println("Nie można wypłacić");
            } else {
                System.out.println("Zapłacono");
            }
        }

    }

    public BigDecimal calculateBonus(int id, int days, int dd, BigDecimal sal, ContractType ct) {
        BigDecimal payment;
        BigDecimal salary = sal;
        BigDecimal bonus = new BigDecimal(dd * 0.05);
        if (ct == ContractType.UoP) {
            payment = salary.add(salary.multiply(bonus)).setScale(2, RoundingMode.HALF_UP);

        } else {
            BigDecimal payment2 = salary.multiply(new BigDecimal(days));
            payment = payment2.add(payment2.multiply(bonus));
        }
        return payment;
    }

}
