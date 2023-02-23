package com.project.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.candella.dbconnectionpool.DBConnectionPool;
import com.project.entity.Vendors;

public class VendorDAOImpl implements VendorDAO {
	
	@Override
	public List<Vendors> findAll() {
		List<Vendors> vendorList = new ArrayList();
		Connection connection = null;
		PreparedStatement prepStmt = null;

		String selectSQL = "Select * from vendors";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				int vendorId = resultSet.getInt(1);
				String vendorName = resultSet.getString(2);
				String vendorAddress = resultSet.getString(3);
				String mobileNumber = resultSet.getString(4);
				String mailId = resultSet.getString(5);
				
				Vendors vendors = new Vendors(vendorId, vendorName, vendorAddress, mobileNumber,mailId);
				vendorList.add(vendors);

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
		return vendorList;
	}

	@Override
	public int save(Vendors vendors) {
		String insertSQL = "insert into vendors(vendor_id, vendor_name, vendor_address, mob_number,mail_id)"
				+ " values(?,?,?,?,?)";
		PreparedStatement preparedStatement = null;	
		Connection connection = null;

		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, vendors.getVendorId());
			preparedStatement.setString(2, vendors.getVendorName());
			preparedStatement.setString(3, vendors.getVendorAddress());
			preparedStatement.setString(4, vendors.getMobileNumber());
			preparedStatement.setString(5, vendors.getMailId());
			
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
	public int update(Vendors vendors) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String UPDATE_SQL = "update vendors set  vendor_name=?, vendor_address=?, mob_number=?,mail_id=? where vendor_id=?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT vendor_id FROM vendors WHERE vendor_id = ?");
			checkStmt.setInt(1, vendors.getVendorId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(UPDATE_SQL);
			preparedStatement.setString(1, vendors.getVendorName());
			preparedStatement.setString(2, vendors.getVendorAddress());
			preparedStatement.setString(3, vendors.getMobileNumber());
			preparedStatement.setString(4, vendors.getMailId());
			preparedStatement.setInt(5, vendors.getVendorId());
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
	public int delete(Vendors vendors) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String DELETE_SQL = "delete from vendors where vendor_id =?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			PreparedStatement checkStmt = connection
					.prepareStatement("SELECT vendor_id FROM vendors WHERE vendor_id = ?");
			checkStmt.setInt(1, vendors.getVendorId());
			ResultSet checkResult = checkStmt.executeQuery();
			if (!checkResult.next()) {
				System.out.println("Entered ID does not exist");
				return 0;
			}
			preparedStatement = connection.prepareStatement(DELETE_SQL);
			preparedStatement.setInt(1, vendors.getVendorId());
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
	public Vendors findById(int vendorId) {
		Vendors vendors = null;
		Connection connection = null;
		PreparedStatement prepStmt = null;
		String selectSQL = "Select * from orders where vendor_id = ?";
		try {
			DataSource ds = DBConnectionPool.getDataSource();
			connection = ds.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			prepStmt.setInt(1, vendorId);
			ResultSet resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String vendorName = resultSet.getString(2);
				String vendorAddress = resultSet.getString(3);
				String mobileNumber = resultSet.getString(4);
				String mailId = resultSet.getString(5);
				vendors = new Vendors(vendorId, vendorName, vendorAddress, mobileNumber,mailId);
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
		return vendors;
	}

	}

		
