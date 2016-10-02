package deepa.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	public void runSQL() {

		System.out.println("inside sql method");

		try {

			Class.forName("org.postgresql.Driver"); //loading drivers
			System.out.println("success loading driver");

			String password = "xyz";
			String url = "jdbc:postgresql://localhost:5432/deepa";
			String username = "postgres";
			Connection db = DriverManager.getConnection(url, username, password); //create connection
			System.out.println("success connecting");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("select * from sales");
			
			
			while (rs.next()) {
				
				System.out.println(rs.getString("cust") + " " + rs.getString("prod") + " " + rs.getString("day") + " " +
					   	rs.getString("month") + " " + rs.getString("year") + " " + rs.getString("quant"));
				//System.out.println("state="+rs.getString("state"));
				//System.out.println("product : "+ rs.getInt("quant"));
			}
			
			db.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
