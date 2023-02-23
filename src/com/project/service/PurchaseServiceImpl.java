package com.project.service;

import java.util.List;

import com.project.DAO.PurchaseDAOImpl;

import com.project.entity.Purchase;

public class PurchaseServiceImpl implements PurchaseService {

	@Override
	public List<Purchase> findAll() {
		PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();
		List<Purchase> purchaseList = purchaseDAOImpl.findAll();
		return purchaseList;
	}

	@Override
	public void save(Purchase purchase) {
		PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();
		int row = purchaseDAOImpl.save(purchase);
		if (row == 1)
			System.out.println("INSERTED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("INSERTION FAILED!!!!!!!!!!!!");
	}

	@Override
	public void update(Purchase purchase) {
		PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();
		int row = purchaseDAOImpl.update(purchase);
		if (row == 1)
			System.out.println("UPDATED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("UPDATION FAILED!!!!!!!!!!!!");
	}

	@Override
	public void delete(Purchase purchase) {
		PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();
		int row = purchaseDAOImpl.delete(purchase);
		if (row != 0)
			System.out.println("DELETED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("DELETE FAILED!!!!!!!!!!!!");
	}

	@Override
	public Purchase findById(String purchaseId) {
		PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();
		Purchase purchase = purchaseDAOImpl.findById(purchaseId);
		return purchase;
	}
}
