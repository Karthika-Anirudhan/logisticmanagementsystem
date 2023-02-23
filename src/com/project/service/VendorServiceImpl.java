package com.project.service;

import java.util.List;
import com.project.DAO.VendorDAOImpl;
import com.project.entity.Vendors;

public class VendorServiceImpl implements VendorService {

	@Override
	public List<Vendors> findAll() {
		VendorDAOImpl vendorDAOImpl = new VendorDAOImpl();
		List<Vendors>VendorList = vendorDAOImpl.findAll();
		return VendorList;
	}

	@Override
	public void save(Vendors vendors) {
		VendorDAOImpl vendorDAOImpl = new VendorDAOImpl();
		int row = vendorDAOImpl.save(vendors);
		if (row == 1)
			System.out.println("INSERTED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("INSERTION FAILED!!!!!!!!!!!!");
	}
	@Override
	public void update(Vendors vendors) {
		VendorDAOImpl vendorDAOImpl = new VendorDAOImpl();
		int row = vendorDAOImpl.update(vendors);
		if (row == 1)
			System.out.println("UPDATED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("UPDATION FAILED!!!!!!!!!!!!");

}

	public void delete(Vendors vendors) {
		VendorDAOImpl vendorDAOImpl = new VendorDAOImpl();
		int row = vendorDAOImpl.delete(vendors);
		if (row !=0)
			System.out.println("DELETED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("DELETE FAILED!!!!!!!!!!!!");
	}

	@Override
	public Vendors findById(int vendorId) {
		VendorDAOImpl vendorDAOImpl = new VendorDAOImpl();
		Vendors vendors =vendorDAOImpl.findById(vendorId);
		return vendors;
	}
}
