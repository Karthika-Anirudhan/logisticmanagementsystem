package com.project.DAO;

import java.util.List;
import com.project.entity.Purchase;
public interface PurchaseDAO {
	List<Purchase>findAll();
	int save(Purchase purchase);
	int update(Purchase purchase );
	int delete(Purchase purchase );
	Purchase findById(String purchaseId);
}
