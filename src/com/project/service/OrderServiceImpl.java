package com.project.service;

import java.util.List;
import com.project.DAO.OrderDAOImpl;
import com.project.entity.Orders;

public class OrderServiceImpl implements OrderService {

	@Override
	public List<Orders> findAll() {
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		List<Orders>orderList = orderDAOImpl.findAll();
		return orderList;
	}
	@Override
	public void save(Orders orders) {
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		int row = orderDAOImpl.save(orders);
		if (row == 1)
			System.out.println("INSERTED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("INSERTION FAILED!!!!!!!!!!!!");
	}
	@Override
	public void update(Orders orders) {
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		int row = orderDAOImpl.update(orders);
		if (row == 1)
			System.out.println("UPDATED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("UPDATION FAILED!!!!!!!!!!!!");	
	}

	@Override
	public void delete(Orders orders) {
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		int row = orderDAOImpl.delete(orders);
		if (row !=0 )
			System.out.println("DELETED SUCCESSFULLY!!!!!!!!!!!!");
		else
			System.out.println("DELETE FAILED!!!!!!!!!!!!");
	}
	@Override
	public Orders findById(int orderId) {
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		Orders orders = orderDAOImpl.findById(orderId);
		return orders ;
	}
		

}
