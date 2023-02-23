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

public class InvoiceDAOImpl implements invoiceDAO {

	@Override
	public List<Invoice> findAll() {
		List<Invoice> invoiceList = new ArrayList();
		Connection connection = null;
		PreparedStatement prepStmt = null;

		String selectSQL = "Select * from invoice";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				String invoiceId = resultSet.getString(1);
				int orderId = resultSet.getInt(2);
				int vendorId = resultSet.getInt(3);
				int invoiceAmount = resultSet.getInt(4);
				double amountPerUnit = resultSet.getDouble(5);
				String paymentDate = resultSet.getString(6);
				String paymentMode = resultSet.getString(7);
				
				Orders orders = new Orders();
				orders.setOrderId(orderId);
				Vendors vendors = new Vendors();
				vendors.setVendorId(vendorId);
	
				Invoice invoice = new Invoice(invoiceId, orders, vendors, invoiceAmount, amountPerUnit,
						paymentDate, paymentMode);
				invoiceList.add(invoice);

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
		return invoiceList;
	}
	

	@Override
	public int save(Invoice invoice) {
		String insertSQL = "insert into invoice(invoice_id,order_id,vendor_id,invoice_amount,amount_perunit,payment_date,payment_mode)"
				+ " values(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
			
		Connection connection = null;

		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, invoice.getInvoiceId());
			preparedStatement.setInt(2, invoice.getOrders().getOrderId());
			preparedStatement.setInt(3, invoice.getVendors().getVendorId());
			preparedStatement.setInt(4, invoice.getInvoiceAmount());
			preparedStatement.setDouble(5, invoice.getAmountPerUnit());
			preparedStatement.setString(6, invoice.getPaymentDate());
			preparedStatement.setString(7, invoice.getPaymentMode());
			
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
	public int update(Invoice invoice) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String UPDATE_SQL = "update invoice set  order_id=?,vendor_id=?,invoice_amount=?,amount_perunit=?,payment_date=?, payment_mode=?"+"where invoice_id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT invoice_id FROM invoice WHERE invoice_id = ?");
			checkStmt.setString(1, invoice.getInvoiceId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(UPDATE_SQL);	
			preparedStatement.setInt(1, invoice.getOrders().getOrderId());
			preparedStatement.setInt(2, invoice.getVendors().getVendorId());
			preparedStatement.setInt(3, invoice.getInvoiceAmount());
			preparedStatement.setDouble(4, invoice.getAmountPerUnit());
			preparedStatement.setString(5, invoice.getPaymentDate());
			preparedStatement.setString(6, invoice.getPaymentMode());
			preparedStatement.setString(7, invoice.getInvoiceId());
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
	public int delete(Invoice invoice) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String DELETE_SQL = "delete from invoice where invoice_Id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT invoice_id FROM invoice WHERE invoice_id = ?");
			checkStmt.setString(1, invoice.getInvoiceId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(DELETE_SQL);
			preparedStatement.setString(1, invoice.getInvoiceId());
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
	public Invoice findById(String invoiceId) {
		Invoice invoice = null;
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from invoice where invoice_Id = ?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			prepStmt.setString(1, invoiceId);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				int orderId = resultSet.getInt(2);
				int vendorId = resultSet.getInt(3);
				int invoiceAmount = resultSet.getInt(4);
				double amountPerUnit = resultSet.getDouble(5);
				String paymentDate = resultSet.getString(6);
				String paymentMode = resultSet.getString(7);

				Orders orders = new Orders();
				orders.setOrderId(orderId);
				Vendors vendors = new Vendors();
				vendors.setVendorId(vendorId);

				invoice = new Invoice(invoiceId, orders, vendors, invoiceAmount, amountPerUnit, paymentDate,
						paymentMode);
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
		return invoice;
	}

	}

		
