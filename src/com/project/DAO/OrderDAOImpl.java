package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.candella.dbconnectionpool.DBConnectionPool;
import com.project.entity.Inventory;
import com.project.entity.Invoice;
import com.project.entity.Orders;
import com.project.entity.Vendors;

public class OrderDAOImpl implements OrdersDAO {
	
	@Override
	public List<Orders> findAll() {
		
		List<Orders> orderList = new ArrayList();
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from orders";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				int orderId = resultSet.getInt(1);
				int vendorId = resultSet.getInt(2);
				String orderDate = resultSet.getString(3);
				int productId =resultSet.getInt(4);
				int orderQuantity = resultSet.getInt(5);
				Vendors vendors = new Vendors();
				vendors.setVendorId(vendorId);
				Inventory inventory = new Inventory();
				inventory.setProductId(productId);
				
				Orders orders= new Orders(orderId, vendors, orderDate, inventory,orderQuantity);
				orderList.add(orders);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderList;
	
	}

	@Override
	public int save(Orders orders) {
		String insertSQL = "insert into orders(order_id,vendor_id,order_date,product_id,order_quantity)"
				+ " values(?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
			
		Connection connection = null;

		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, orders.getOrderId());
			preparedStatement.setInt(2, orders.getVendors().getVendorId());
			preparedStatement.setString(3, orders.getOrderDate());
			preparedStatement.setInt(4, orders.getInventory().getProductId());
			preparedStatement.setInt(5, orders.getOrderQuantity());
			int row = preparedStatement.executeUpdate();
			return row;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}



	@Override
	public int update(Orders orders) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String UPDATE_SQL = "update orders set  vendor_id=?, order_date=?, product_id=?,order_quantity=? where order_id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT order_id FROM orders WHERE order_id = ?");
			checkStmt.setInt(1, orders.getOrderId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(UPDATE_SQL);
			preparedStatement.setInt(1, orders.getVendors().getVendorId());
			preparedStatement.setString(2, orders.getOrderDate());
			preparedStatement.setInt(3, orders.getInventory().getProductId());
			preparedStatement.setInt(4, orders.getOrderQuantity());
			preparedStatement.setInt(5, orders.getOrderId());
			int row = preparedStatement.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}


	@Override
	public int delete(Orders orders) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String DELETE_SQL = "delete from orders where order_Id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT order_id FROM orders WHERE order_id = ?");
			checkStmt.setInt(1, orders.getOrderId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(DELETE_SQL);
			preparedStatement.setInt(1, orders.getOrderId());
			int row = preparedStatement.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public Orders findById(int orderId) {
		Orders orders = null;
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from orders where order_id = ?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			prepStmt.setInt(1, orderId);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				int orderId1 = resultSet.getInt(1);
				int vendorId = resultSet.getInt(2);
				String orderDate = resultSet.getString(3);
				int productId =resultSet.getInt(4);
				int orderQuantity = resultSet.getInt(5);
				Vendors vendors = new Vendors();
				vendors.setVendorId(vendorId);
				Inventory inventory = new Inventory();
				inventory.setProductId(productId);
				
				orders= new Orders(orderId, vendors, orderDate, inventory,orderQuantity);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (prepStmt != null)
					prepStmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orders;
	}

	}

		
