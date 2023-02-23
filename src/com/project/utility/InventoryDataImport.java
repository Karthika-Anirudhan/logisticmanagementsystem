package com.project.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.candella.dbconnectionpool.DBConnectionPool;
import com.project.entity.Inventory;

public class InventoryDataImport {

	public static void main(String[] args) {
		dataImport();
	}
		
		public static void dataImport(){
		List<Inventory> inventoryList = new ArrayList<>();
		Connection connection = null;
		DataSource ds = DBConnectionPool.getDataSource();
		int batchSize = 10;
		
			try {
				connection = ds.getConnection();
				connection.setAutoCommit(false); // Disable autocommit
			
			File file = new File("C:\\Users\\admin\\eclipse-workspace\\CCJP_LogisticManagement_Project\\Data\\inventory data.xlsx");
			
				FileInputStream inputStream = new FileInputStream(file);
				// Create the workbook and sheet objects
				Workbook workbook = new XSSFWorkbook(inputStream);

				Sheet firstSheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = firstSheet.iterator();

				// Define SQL statement to insert data into the inventory table
				String sql = "INSERT INTO inventory (product_id, product_name, product_description, quantity) VALUES (?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				int count = 0;

				rowIterator.next(); // skip the header row

				while (rowIterator.hasNext()) {
					Row nextRow = rowIterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();

					// Set the parameters for the SQL statement based on the values in each cell
					while (cellIterator.hasNext()) {
						Cell nextCell = cellIterator.next();
						int columnIndex = nextCell.getColumnIndex();

						switch (columnIndex) {
						case 0:
							int productId = (int) nextCell.getNumericCellValue();
							statement.setInt(1, productId);
							break;
						case 1:
							String productName = nextCell.getStringCellValue();
							statement.setString(2, productName);
							break;
						case 2:
							String productDescription = nextCell.getStringCellValue();
							statement.setString(3, productDescription);
							break;
						case 3:
							int quantity = (int) nextCell.getNumericCellValue();
							statement.setInt(4, quantity);
							break;
						}
					}

					statement.addBatch();
					count++;

					if (count % batchSize == 0) {
						statement.executeBatch();
					}
				}

				workbook.close();

				// execute the remaining queries
				statement.executeBatch();
				connection.commit();
				connection.close();
				System.out.printf("Data Imported successfully...");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
}
}

		