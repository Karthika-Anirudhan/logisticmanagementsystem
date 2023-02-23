package com.project.DAO;

import java.util.List;
import com.project.entity.Vendors;

public interface VendorDAO {
	List<Vendors>findAll();
	int save (Vendors vendors);
	int update(Vendors vendors);
	int delete(Vendors vendors);
	Vendors findById(int vendorId);

}
