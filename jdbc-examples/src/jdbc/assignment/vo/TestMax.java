package jdbc.assignment.vo;

import java.sql.*;

public class TestMax {

	private static String cname;
	private static String cproduct;
	private static int cmaximum;
	private static int cdate;
	private static int cmonth;
	private static int cyear;
	private static String cstate;
	private static String mname;
	private static String mproduct;
	private static int mdate;
	private static int mmonth;
	private static int myear;
	private static String mstate;
	private static double sum;
	private static int count;
	private static double average;

	public static void main(String[] args) {
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";
		int compareQuant = 0;
		int maximum = 0;
		int minimum=1000000;
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
				compareQuant = rs.getInt("quant");
				if (name.equals("Helen") && product.equals("Pepsi")) {

					if (compareQuant > maximum) {
						maximum = compareQuant;
						
						cname=name;
						cproduct=product;
						//cmaximum=maximum;
						cdate=rs.getInt("day");
						cmonth=rs.getInt("month");
						cyear=rs.getInt("year");
						cstate=rs.getString("state");
					}
					if(compareQuant<minimum){
						minimum=compareQuant;
						mname=name;
						mproduct=product;
						mdate=rs.getInt("day");
						mmonth=rs.getInt("month");
						myear=rs.getInt("year");
						mstate=rs.getString("state");
					}
					sum+=compareQuant;
					count++;
					

				}
				

			}
			average=sum/count;
			System.out.println("count: "+  count);
			System.out.println("CUSTOMER\t PRODUCT\t MAX_Q\t\t DATE\t\t ST\t MIN_Q\t\t DATE\t\t ST\t AVG_Q");
			System.out.println("======== \t======== \t===== \t\t==========\t ==\t===== \t\t==========\t ==\t=====");
			System.out.println(cname +"\t\t "+  cproduct+"\t\t "+maximum+ "\t\t"+ cdate+ "/" + cmonth + "/"+ cyear+ "\t " +cstate
					+"\t" +minimum+ "\t\t"+ mdate+ "/" + mmonth + "/"+ myear+ "\t " +mstate + "\t"+ average);
			
			


		}

		catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
