package com.project.service;

import java.util.List;
import com.project.entity.Shipment;

public interface ShipmentService {
	List<Shipment> findAll();
    void save(Shipment shipment);
    void update(Shipment shipment);
    void delete(Shipment shipment);
    Shipment findById(String shipmentId);

}
