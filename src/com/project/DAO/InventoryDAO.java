package com.project.DAO;

import java.util.List;

import com.project.entity.Inventory;

public interface InventoryDAO {
	List<Inventory>findAll();
	Inventory findById(int productId);
	int save(Inventory inventory);
	int update(Inventory inventory );
	int delete(Inventory inventory);
	

}
