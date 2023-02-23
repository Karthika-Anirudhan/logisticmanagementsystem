package com.project.service;

import java.util.List;

import com.project.entity.Inventory;


public interface InventoryService {
	List<Inventory> findAll();
	Inventory findById(int productId);
    void save(Inventory inventory);
    void update(Inventory inventory);
    void delete(Inventory inventory);

}
