package com.project.service;

import java.util.List;
import com.project.entity.Orders;

public interface OrderService {
	List<Orders>findAll();
	void save (Orders orders);
	void update (Orders orders);
	void delete(Orders orders);
	Orders findById(int orderId);
}
