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

public class InventoryDAOImpl implements InventoryDAO {

	@Override
	public List<Inventory> findAll() {

		List<Inventory> inventoryList = new ArrayList();
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from inventory";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				int productId = resultSet.getInt(1);
				String productName = resultSet.getString(2);
				String productDescription = resultSet.getString(3);
				int quantity = resultSet.getInt(4);
				Inventory inventory = new Inventory(productId, productName, productDescription, quantity);
				inventoryList.add(inventory);

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
		return inventoryList;
	}

	@Override
	public int save(Inventory inventory) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String INSERT_SQL = "insert into inventory(product_Id,product_name,product_description,quantity) values(?,?,?,?)";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_SQL);
			preparedStatement.setInt(1, inventory.getProductId());
			preparedStatement.setString(2, inventory.getProductName());
			preparedStatement.setString(3, inventory.getProductDescription());
			preparedStatement.setInt(4, inventory.getQuantity());
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
	public int update(Inventory inventory) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String UPDATE_SQL = "update inventory set product_name=?,product_description=?,quantity=? where product_id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT product_id FROM inventory WHERE product_id = ?");
			checkStmt.setInt(1, inventory.getProductId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(UPDATE_SQL);
			preparedStatement.setString(1, inventory.getProductName());
			preparedStatement.setString(2, inventory.getProductDescription());
			preparedStatement.setInt(3, inventory.getQuantity());
			preparedStatement.setInt(4, inventory.getProductId());
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
	public int delete(Inventory inventory) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String DELETE_SQL = "delete from inventory where product_Id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
	        PreparedStatement checkStmt = connection.prepareStatement("SELECT product_Id FROM inventory WHERE product_Id = ?");
	        checkStmt.setInt(1, inventory.getProductId());
	        ResultSet checkResult = checkStmt.executeQuery();
	        if (!checkResult.next()) {
	            System.out.println("Entered ID does not exist");
	            return 0;
	        }
			preparedStatement = connection.prepareStatement(DELETE_SQL);
			preparedStatement.setInt(1, inventory.getProductId());
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
	public Inventory findById(int productId) {
		Inventory inventory = null;
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from inventory where product_Id = ?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			prepStmt.setInt(1, productId); 
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				int Id = resultSet.getInt(1);
				String productName = resultSet.getString(2);
				String productDescription = resultSet.getString(3);
				int quantity = resultSet.getInt(4);
				inventory = new Inventory(productId, productName, productDescription, quantity);
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
		return inventory;
	}

}