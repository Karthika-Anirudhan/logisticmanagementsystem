package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.candella.dbconnectionpool.DBConnectionPool;

public class LoginDAO {
	Connection connection = null;
	PreparedStatement prepStmt = null;
	 String selectSQL = "SELECT * FROM login WHERE user_name = ? AND password = ?";
	 public boolean findByUsernameAndPassword(String userName, String password) throws SQLException {
		
				DataSource ds = DBConnectionPool.getDataSource();
				connection = ds.getConnection();
				prepStmt = connection.prepareStatement(selectSQL);
				prepStmt.setString(1, userName);
				prepStmt.setString(2, password);
				ResultSet resultSet = prepStmt.executeQuery();
			
		return resultSet.next();
		 
	 }
}
