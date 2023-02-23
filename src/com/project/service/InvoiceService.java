package com.project.service;

import java.util.List;

import com.project.entity.Invoice;

public interface InvoiceService {
	List<Invoice> findAll();
	Invoice findById(String invoiceId);
    int save(Invoice invoice);
    void update(Invoice invoice);
    void delete(Invoice invoice);

}
