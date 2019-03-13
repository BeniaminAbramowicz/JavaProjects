package com.kostka.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {

    private String invoiceNumber;
    private BigDecimal value;
    private String NIP;
    private LocalDate date;
    private Integer daysWorked;

    public Invoice() {

    }

    public Invoice(String invoiceNumber, BigDecimal value, String NIP, LocalDate date, Integer daysWorked) {
        this.invoiceNumber = invoiceNumber;
        this.value = value;
        this.NIP = NIP;
        this.date = date;
        this.daysWorked = daysWorked;
    }

    public void setDaysWorked(Integer daysWorked) {
        this.daysWorked = daysWorked;
    }

    public Integer getDaysWorked() {
        return daysWorked;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getNIP() {
        return NIP;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", value=" + value +
                ", NIP='" + NIP + '\'' +
                ", date=" + date +
                '}';
    }
}

