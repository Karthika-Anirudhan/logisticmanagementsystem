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
import com.project.entity.Orders;
import com.project.entity.Purchase;
import com.project.entity.Shipment;
import com.project.entity.Vendors;

public class ShipmentDAOImpl implements ShipmentDAO {

	@Override
	public List<Shipment> findAll() {
		List<Shipment> shipmentList = new ArrayList();
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from shipment";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				String shipmentId = resultSet.getString(1);
				int orderId = resultSet.getInt(2);
				String shippingAddress = resultSet.getString(3);
				String trackingNumber =resultSet.getString(4);
				String estimateDeliveryDate = resultSet.getString(5);
				String status = resultSet.getString(6);
				Orders orders = new Orders();
				orders.setOrderId(orderId);
				Shipment shipment= new Shipment(shipmentId,orders, shippingAddress,trackingNumber,estimateDeliveryDate,status);
				shipmentList.add(shipment);

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
		return shipmentList;
	
	}

	@Override
	public int save(Shipment shipment) {
		String INSERT_SQL = "insert into shipment(shipment_id,order_id,shipping_address,tracking_number,estimate_deliverydate,status)"
				+ " values(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;	
		Connection connection = null;

		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(INSERT_SQL);
			preparedStatement.setString(1, shipment.getShipmentId());
			preparedStatement.setInt(2, shipment.getOrders().getOrderId());
			preparedStatement.setString(3, shipment.getShippingAddress());
			preparedStatement.setString(4, shipment.getTrackingNumber());
			preparedStatement.setString(5, shipment.getEstimatedDeliveryDate());
			preparedStatement.setString(6, shipment.getStatus());
			
			
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
	public int update(Shipment shipment) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String UPDATE_SQL = "update shipment set order_id=?,shipping_address=?,tracking_number=?,estimate_deliverydate=?,status=?"+"where shipment_id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT shipment_id FROM shipment WHERE shipment_id = ?");
			checkStmt.setString(1, shipment.getShipmentId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(UPDATE_SQL);	
			preparedStatement.setInt(1, shipment.getOrders().getOrderId());
			preparedStatement.setString(2, shipment.getShippingAddress());
			preparedStatement.setString(3, shipment.getTrackingNumber());
			preparedStatement.setString(4, shipment.getEstimatedDeliveryDate());
			preparedStatement.setString(5, shipment.getStatus());
			preparedStatement.setString(6, shipment.getShipmentId());
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
	public int delete(Shipment shipment) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String DELETE_SQL = "delete from shipment where shipment_Id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT shipment_id FROM shipment WHERE shipment_id = ?");
			checkStmt.setString(1, shipment.getShipmentId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(DELETE_SQL);
			preparedStatement.setString(1, shipment.getShipmentId());
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
	public Shipment findById(String shipmentId) {
		Shipment shipment = null;
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from shipment where shipment_id = ?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			prepStmt.setString(1, shipmentId);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				String shipId = resultSet.getString(1);
				int orderId = resultSet.getInt(2);
				String shippingAddress = resultSet.getString(3);
				String trackingNumber =resultSet.getString(4);
				String estimateDeliveryDate = resultSet.getString(5);
				String status = resultSet.getString(6);
				Orders orders = new Orders();
				orders.setOrderId(orderId);
				shipment= new Shipment(shipmentId,orders, shippingAddress,trackingNumber,estimateDeliveryDate,status);
				
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
		return shipment;
	}

	}

		
