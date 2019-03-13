package com.kostka.dao;

import com.kostka.model.Invoice;
import com.kostka.repository.InvoiceRepository;

import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {

    private List<Invoice> invoices = InvoiceRepository.invoiceList();

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoice;
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoices;
    }
}
