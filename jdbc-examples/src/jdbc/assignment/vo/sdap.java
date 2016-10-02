package jdbc.assignment.vo;

import java.sql.*;

public class sdap {

	public static void main(String[] args) {
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";
		Sales salesResult[] = new Sales[500];
		int i = 0;

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			//while (rs.next()) {

			/*	Sales sb = new Sales();
				sb.setCustomer(rs.getString("cust"));
				sb.setProduct(rs.getString("prod"));
				sb.setDay(rs.getInt("day"));
				sb.setMonth(rs.getInt("month"));
				sb.setYear(rs.getInt("year"));
				sb.setQuantity(rs.getInt("quant"));
				sb.setState(rs.getString("state"));
				salesResult[i] = sb;
				i++;
			}
			for (int j = 0; j < salesResult.length; j++) {
				Sales sales = salesResult[j];
				System.out.println(sales.getCustomer() + "\t"
						+ sales.getProduct() + "\t" + sales.getDay() + "/"
						+ sales.getMonth() + "/" + sales.getYear() + "\t"
						+ sales.getState() + "\t" + sales.getQuantity());
			}*/
		}

		catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
