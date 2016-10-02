package org.stevens.olap.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {

		protected Connection createConnection() {
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa"; // database

		try {
			// Loading Drivers

			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			// error connecting to database
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try {
			// Connecting to Server

			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");
			return conn;
		} catch (SQLException e) {
			// error
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
		return null;
	}

	// metadata function returns the datatype of each colname

	public Map<String, String> getMetaData() throws SQLException {
		Map<String, String> colType = new HashMap<String, String>();
		Connection conn = createConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
		ResultSetMetaData metaData = rs.getMetaData();

		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			switch (metaData.getColumnType(i)) {
			// 12,1 are codes for String values and 4 is code for int value
			case 12:
			case 1:
				colType.put(metaData.getColumnName(i), "String");
				break;
			case 4:
				colType.put(metaData.getColumnName(i), "int");
				break;
			}
		}
		stmt.close();
		conn.close();
		return colType;
	}
}
