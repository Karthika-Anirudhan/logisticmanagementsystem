package com.project.DAO;

import java.util.List;
import com.project.entity.Invoice;
public interface invoiceDAO {
	List<Invoice>findAll();
	Invoice findById(String invoiceId);
	int save(Invoice invoice);
	int update(Invoice invoice );
	int delete(Invoice invoice);
	

}
