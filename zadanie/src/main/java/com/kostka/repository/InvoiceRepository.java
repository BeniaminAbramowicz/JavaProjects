package com.kostka.repository;

import com.kostka.dao.EmployeeDAO;
import com.kostka.dao.EmployeeDAOImpl;
import com.kostka.enums.ContractType;
import com.kostka.model.Employee;
import com.kostka.model.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class InvoiceRepository {


    public static List<Invoice> invoiceList(){
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice1 = new Invoice("3465343654", new BigDecimal(2000), "345445", LocalDate.of(2018, 1, 1), 20);
        Invoice invoice2 = new Invoice("35654356322", new BigDecimal(2777), "4545", LocalDate.of(2019, 2,2), 15);
        Invoice invoice3 = new Invoice("2324443654", new BigDecimal(2888), "879897895", LocalDate.of(2017,3,4), 18);

        invoiceList.add(invoice1);
        invoiceList.add(invoice2);
        invoiceList.add(invoice3);


        System.out.println(invoiceList);

        return invoiceList;
    }
}
