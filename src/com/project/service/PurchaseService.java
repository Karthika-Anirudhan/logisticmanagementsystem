package com.project.service;

import java.util.List;
import com.project.entity.Purchase;

public interface PurchaseService {
	List<Purchase> findAll();
    void save(Purchase purchase);
    void update(Purchase purchase);
    void delete(Purchase purchase);
    Purchase findById(String purchaseId);


}
