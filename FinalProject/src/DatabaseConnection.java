
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;



public class DatabaseConnection {

	public static void main(String[] args) {
		String usr = "postgres"; 
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa"; // database connection url

		try {
			// Loading Drivers

			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			//error connecting to database
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try {
			// Connecting to Server

			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			//create statement and execute sql query
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
			ResultSetMetaData metaData = rs.getMetaData();
			
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				//System.out.print(metaData.getColumnName(i) + "---");
				System.out.println(metaData.getColumnType(i));
				
				
				if(metaData.getColumnType(i) == Types.INTEGER){
					//System.out.println("Interger");
				}
				//System.out.println(metaData.getColumnTypeName(i));
				
				
				
			}
			
			
			
		}
		catch (SQLException e) {
			//error
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
		
	
	}
}
