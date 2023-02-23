package com.project.DAO;

import java.util.List;
import com.project.entity.Orders;

public interface OrdersDAO {
	List<Orders>findAll();
	int save (Orders orders);
	int update (Orders orders);
	int delete (Orders orders);
	Orders findById(int orderId);

}
