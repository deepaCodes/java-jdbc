package jdbc.assignment.vo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaxMinAvg {

	public static void main(String[] args) {
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";

		Sales[] sArray = new Sales[500];
		int iterator = 0;

		try {
			// Loading Drivers

			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try {
			// Connecting to Server

			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			int loopcount = 0;
			while (rs.next()) {

				/*
				 * Reading the first row of the table and saving all details in
				 * an array Sales
				 */

				if (iterator == 0) {
					Sales sb = new Sales();

					sb.setProduct(rs.getString("prod"));
					sb.setCustomer(rs.getString("cust"));
					sb.setMaxQ(rs.getInt("quant"));
					sb.setMinQ(rs.getInt("quant"));
					sb.setMinState(rs.getString("state"));
					sb.setMaxState(rs.getString("state"));
					sb.setMaxDate(rs.getInt("month") + "/" + rs.getInt("day")
							+ "/" + rs.getInt("year"));
					sb.setMinDate(rs.getInt("month") + "/" + rs.getInt("day")
							+ "/" + rs.getInt("year"));
					sb.setSum(rs.getInt("quant"));
					sb.setCount();

					sArray[loopcount++] = sb;

					iterator++;
					continue;
				}

				/*
				 * Flag checks if the Name-Product pair is already in the array
				 * or not
				 */

				boolean flag = false;

				/*
				 * For subsequent rows accessed from the table, first check if
				 * it exists in Sales array : if yes, update MAX, MIN, SUM and
				 * Dates. if No, insert into Sales array
				 */

				for (int i = 0; i < sArray.length; i++) {

					Sales s = sArray[i];
					if (s == null) {
						break;
					}

					if (rs.getString("cust").equals(s.getCustomer())
							&& rs.getString("prod").equals(s.getProduct())) {

						s.setSum(rs.getInt("quant"));
						s.setCount();

						if (rs.getInt("quant") > s.getMaxQ()) {

							s.setMaxQ(rs.getInt("quant"));
							s.setMaxDate(rs.getInt("month") + "/"
									+ rs.getInt("day") + "/"
									+ rs.getInt("year"));
							s.setMaxState(rs.getString("state"));

						}
						if (rs.getInt("quant") < s.getMinQ()) {

							s.setMinQ(rs.getInt("quant"));
							s.setMinDate(rs.getInt("month") + "/"
									+ rs.getInt("day") + "/"
									+ rs.getInt("year"));
							s.setMinState(rs.getString("state"));

						}
						flag = true;
					}

				}

				/*
				 * If the Name-Product pair is not already in Sales array,
				 * insert into the array and get all details
				 */

				if (!flag) {
					Sales sb = new Sales();
					sb.setSum(rs.getInt("quant"));
					sb.setCount();

					sb.setProduct(rs.getString("prod"));
					sb.setCustomer(rs.getString("cust"));
					sb.setMaxQ(rs.getInt("quant"));
					sb.setMinQ(rs.getInt("quant"));
					sb.setMinState(rs.getString("state"));
					sb.setMaxState(rs.getString("state"));
					sb.setMaxDate(rs.getInt("month") + "/" + rs.getInt("day")
							+ "/" + rs.getInt("year"));
					sb.setMinDate(rs.getInt("month") + "/" + rs.getInt("day")
							+ "/" + rs.getInt("year"));

					sArray[loopcount++] = sb;

				}

			}

			System.out.format("%1s%12s%12s%12s%12s%13s%12s%13s%14s",
					"CUSTOMER", "PRODUCT", "MAX_Q", "DATE", "ST", "MIN_Q",
					"DATE", "ST", "AVG\n");
			System.out.format("%1s%12s%12s%14s%11s%13s%13s%12s%15s",
					"========", "=======", "======", "=========", "====",
					"======", "=========", "====", "======\n");

			// Formatting date
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

			// Printing all the fields to be displayed

			for (int i = 0; i < sArray.length; i++) {
				Sales s = sArray[i];
				if (s == null) {
					break;
				}
				s.setAverage();

				System.out.format("%-5s%9s", s.getCustomer(), "");
				System.out.format("%-10s%2s", s.getProduct(), "");
				System.out.format("%5d%6s", s.getMaxQ(), "");
				System.out.format("%-12s%4s",
						df.format(new Date(s.getMaxDate())), "");
				System.out.format("%1s%8s", s.getMaxState(), "");
				System.out.format("%5s%5s", s.getMinQ(), "");
				System.out.format("%11s%8s",
						df.format(new Date(s.getMinDate())), "");
				System.out.format("%1s%9s", s.getMinState(), "");
				// type casting Average result to integer
				System.out.format("%5d", new Double(s.getAverage()).intValue());
				System.out.println();

			}

		}

		catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
