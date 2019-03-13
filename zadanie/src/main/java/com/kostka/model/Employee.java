package com.kostka.model;

import com.kostka.enums.ContractType;

import java.math.BigDecimal;

public class Employee {

    private int idEmp;
    private String firstName;
    private String lastName;
    private String NIP;
    private BigDecimal salary;
    private ContractType contractType;

    public Employee() {

    }

    public Employee(int idEmp, String firstName, String lastName, String NIP, BigDecimal salary, ContractType contractType) {
        this.idEmp = idEmp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.NIP = NIP;
        this.salary = salary;
        this.contractType = contractType;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNIP() {
        return NIP;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmp=" + idEmp +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", NIP='" + NIP + '\'' +
                ", salary=" + salary +
                ", contractType=" + contractType +
                '}';
    }
}
