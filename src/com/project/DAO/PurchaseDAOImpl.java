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
import com.project.entity.Vendors;

public class PurchaseDAOImpl implements PurchaseDAO {

	@Override
	public List<Purchase> findAll() {
		List<Purchase> purchaseList = new ArrayList();
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from purchase";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				String purchaseId = resultSet.getString(1);
				int productId =resultSet.getInt(2);
				int quantity = resultSet.getInt(3);
				int purchaseAmount = resultSet.getInt(4);
				int unitAmount =resultSet.getInt(5);

				Inventory inventory = new Inventory();
				inventory.setProductId(productId);
		
				Purchase purchase= new Purchase(purchaseId, inventory,quantity,purchaseAmount,unitAmount);
				purchaseList.add(purchase);

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
		return purchaseList;
	
	}

	@Override
	public int save(Purchase purchase) {
		String INSERT_SQL = "insert into purchase(purchase_id,product_id,quantity,purchase_amount,unit_amount)"
				+ " values(?,?,?,?,?)";
		PreparedStatement preparedStatement = null;	
		Connection connection = null;

		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(INSERT_SQL);
			preparedStatement.setString(1, purchase.getPurchaseId());
			preparedStatement.setInt(2, purchase.getInventory().getProductId());
			preparedStatement.setInt(3, purchase.getQuantity());
			preparedStatement.setInt(4, purchase.getPurchaseAmount());
			preparedStatement.setInt(5, purchase.getUnitAmount());
			
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
	public int update(Purchase purchase) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String UPDATE_SQL = "update purchase set product_id=?,quantity=?,purchase_amount=?,unit_amount=? where purchase_id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT purchase_id FROM purchase WHERE purchase_id = ?");
			checkStmt.setString(1, purchase.getPurchaseId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(UPDATE_SQL);	
			preparedStatement.setInt(1, purchase.getInventory().getProductId());
			preparedStatement.setInt(2, purchase.getQuantity());
			preparedStatement.setInt(3, purchase.getPurchaseAmount());
			preparedStatement.setInt(4, purchase.getUnitAmount());
			preparedStatement.setString(5, purchase.getPurchaseId());
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
	public int delete(Purchase purchase) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String DELETE_SQL = "delete from purchase where purchase_id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT purchase_id FROM purchase WHERE purchase_id = ?");
			checkStmt.setString(1, purchase.getPurchaseId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(DELETE_SQL);
			preparedStatement.setString(1, purchase.getPurchaseId());
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
	public Purchase findById(String purchaseId) {
		Purchase purchase = null;
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from purchase where purchase_id = ?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			prepStmt.setString(1, purchaseId);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				int productId =resultSet.getInt(2);
				int quantity = resultSet.getInt(3);
				int purchaseAmount = resultSet.getInt(4);
				int unitAmount =resultSet.getInt(5);

				Inventory inventory = new Inventory();
				inventory.setProductId(productId);
				purchase= new Purchase(purchaseId, inventory,quantity,purchaseAmount,unitAmount);
				
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
		return purchase;
	}

	}

		
