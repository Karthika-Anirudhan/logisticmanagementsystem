package com.project.service;

import java.util.List;
import com.project.DAO.ShipmentDAOImpl;
import com.project.entity.Shipment;

public class ShipmentServiceImpl implements ShipmentService {

	@Override
	public List<Shipment> findAll() {
		ShipmentDAOImpl shipmentDAOImpl = new ShipmentDAOImpl();
		List<Shipment>shipmentList = shipmentDAOImpl.findAll();
		return shipmentList;
	}

	@Override
	public void save(Shipment shipment) {
		ShipmentDAOImpl shipmentDAOImpl = new ShipmentDAOImpl();
		int row = shipmentDAOImpl.save(shipment);
		if (row == 1)
			System.out.println("INSERTED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("INSERTION FAILED!!!!!!!!!!!!");
	}

	@Override
	public void update(Shipment shipment) {
		ShipmentDAOImpl shipmentDAOImpl = new ShipmentDAOImpl();
		int row = shipmentDAOImpl.update(shipment);
		if (row ==1 )
			System.out.println("UPDATED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("UPDATION FAILED!!!!!!!!!!!!");
	
	}

	@Override
	public void delete(Shipment shipment) {
		ShipmentDAOImpl shipmentDAOImpl = new ShipmentDAOImpl();
		int row = shipmentDAOImpl.delete(shipment);
		if (row != 0 )
			System.out.println("DELETED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("DELETE FAILED!!!!!!!!!!!!");
		
	}

	@Override
	public Shipment findById(String shipmentId) {
		ShipmentDAOImpl shipmentDAOImpl = new ShipmentDAOImpl();
		Shipment shipment= shipmentDAOImpl.findById(shipmentId);
		return shipment;
	}

}
