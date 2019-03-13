package com.kostka.dao;

import com.kostka.model.Invoice;

import java.util.List;

public interface InvoiceDAO {

    Invoice addInvoice(Invoice invoice);

    List<Invoice> getInvoices();
}
