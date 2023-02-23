package com.project.service;

import java.util.List;

import com.project.entity.Vendors;


public interface VendorService {
	List<Vendors> findAll();

	void save(Vendors vendors);

	void update(Vendors vendors);

	void delete(Vendors vendors);

    Vendors findById(int vendorId);

}
