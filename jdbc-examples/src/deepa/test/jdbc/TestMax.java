package deepa.test.jdbc;

import java.sql.*;

public class TestMax {

	private static String cname;
	private static String cproduct;
	private static int cmaximum;
	private static int cdate;
	private static int cmonth;
	private static int cyear;
	private static String cstate;

	public static void main(String[] args) {
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";
		int max = 0;
		int maximum = 0;
		String name = "";
		String product = "";

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
			while (rs.next()) {
				
				name = rs.getString("cust");
				product = rs.getString("prod");
				max = rs.getInt("quant");
				if (name.equals("Helen") && product.equals("Pepsi")) {

					if (max > maximum) {
						maximum = max;
						
						cname=name;
						cproduct=product;
						cmaximum=maximum;
						cdate=rs.getInt("day");
						cmonth=rs.getInt("month");
						cyear=rs.getInt("year");
						cstate=rs.getString("state");
					}

				}

			}
			System.out.println("CUSTOMER\t PRODUCT\t MAX_Q\t\t DATE\t\t ST\t");
			System.out.println("======== \t======== \t===== \t\t==========\t ==");
			System.out.println(cname +"\t\t "+  cproduct+"\t\t "+maximum+ "\t\t"+ cdate+ "/" + cmonth + "/"+ cyear+ "\t " +cstate);
			
			


		}

		catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
