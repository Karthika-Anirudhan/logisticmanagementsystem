package com.project.service;

import java.util.List;

import com.project.DAO.InventoryDAOImpl;
import com.project.entity.Inventory;

public class InventoryServiceImpl implements InventoryService {

	@Override
	public List<Inventory> findAll() {
		InventoryDAOImpl inventoryDAOImpl = new InventoryDAOImpl();
		List<Inventory> inventoryList = inventoryDAOImpl.findAll();
		return inventoryList;
	}

	@Override
	public void save(Inventory inventory) {
		InventoryDAOImpl inventoryDAOImpl = new InventoryDAOImpl();
		int row = inventoryDAOImpl.save(inventory);
		if (row == 1)
			System.out.println("INSERTED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("INSERTION FAILED!!!!!!!!!!!!");
	}

	@Override
	public void update(Inventory inventory) {
		InventoryDAOImpl inventoryDAOImpl = new InventoryDAOImpl();
		int row = inventoryDAOImpl.update(inventory);
		if (row == 1)
			System.out.println("UPDATED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("UPDATION FAILED!!!!!!!!!!!!");
	}

	@Override
	public void delete(Inventory inventory) {
		InventoryDAOImpl inventoryDAOImpl = new InventoryDAOImpl();
		int row = inventoryDAOImpl.delete(inventory);
		if (row != 0)
			System.out.println("DELETED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("DELETE FAILED!!!!!!!!!!!!");
	}

	@Override
	public Inventory findById(int productId) {
		InventoryDAOImpl inventoryDAOImpl = new InventoryDAOImpl();
		Inventory row = inventoryDAOImpl.findById(productId);
		
		return row ;
	}



	
	}

