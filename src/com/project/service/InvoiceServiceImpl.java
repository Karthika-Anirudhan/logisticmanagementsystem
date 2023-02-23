package com.project.service;

import java.util.List;

import com.project.DAO.InvoiceDAOImpl;

import com.project.entity.Invoice;

public class InvoiceServiceImpl implements InvoiceService {

	@Override
	public List<Invoice> findAll() {
		InvoiceDAOImpl invoiceDAOImpl = new InvoiceDAOImpl();
		List<Invoice>invoiceList = invoiceDAOImpl.findAll();
		return invoiceList;
	}

	@Override
	public int save(Invoice invoice) {
		InvoiceDAOImpl orderDAOImpl = new InvoiceDAOImpl();
		int row = orderDAOImpl.save(invoice);
		if (row == 1)
			System.out.println("INSERTED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("INSERTION FAILED!!!!!!!!!!!!");
		return 0;
	}

	@Override
	public void update(Invoice invoice) {
		InvoiceDAOImpl orderDAOImpl = new InvoiceDAOImpl();
		int row = orderDAOImpl.update(invoice);
		if (row == 1)
			System.out.println("UPDATED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("UPDATION FAILED!!!!!!!!!!!!");	
	}

	@Override
	public void delete(Invoice invoice) {
		InvoiceDAOImpl orderDAOImpl = new InvoiceDAOImpl();
		int row = orderDAOImpl.delete(invoice);
		if (row !=0 )
			System.out.println("DELETED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("DELETE FAILED!!!!!!!!!!!!");
	}

	@Override
	public Invoice findById(String invoiceId) {
		InvoiceDAOImpl invoiceDAOImpl = new InvoiceDAOImpl();
		Invoice invoice = invoiceDAOImpl.findById(invoiceId);
		return invoice ;
	}
		
	}


