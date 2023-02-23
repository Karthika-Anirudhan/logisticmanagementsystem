package com.project.DAO;

import java.util.List;
import com.project.entity.Shipment;


public interface ShipmentDAO {
	List<Shipment>findAll();
	int save(Shipment shipment);
    int update(Shipment shipment);
    int delete(Shipment shipment);
    Shipment findById(String shipmentId);
}
