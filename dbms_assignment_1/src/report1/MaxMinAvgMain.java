/* 
 * @author Deepa Aswathaiah - CWID 10405689
1. unzip dbms_assignment_1.rar file
2. Java files are compiled using java version "1.8.0_65"
3. Import unzipped project folder (dbms_assignment_1) into eclipse workspace
4. Change connection details in report1/MaxMinAvgMain.java 
		String usr = "user_name_here"; 
		String pwd = "password_here";
		String url = "connection_url_here";
5. Report-1: run report1/MaxMinAvgMain.java file 


 * Psuedocode: 
 * while(rs.next()){
 * Insert the first row into Sales array
 * check if the next row is already in Sales array{
 * 		if yes{
 * 				compare quantities and update if conditions satisfy
 * 				increment the count for average calculation
 * 			}
 * 		if no{
 * 				Add all the details in the row into Sales array
 * 			}
 * }
 * }
 * print out the Sales array
 */
package report1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Deepa Aswathaiah - CWID 10405689
 * Main Class to calculate maximum, minimum and average sales quantities for each combination of customer and product.
 * and print result 
 */
public class MaxMinAvgMain {

	/**
	 * Main method to calculate maximum, minimum and average sales quantities for each combination of customer and product.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// connection details variables
		String usr = "postgres"; 
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa"; // database connection url

		// declare Sales array to hold sales data
		Sales[] sArray = new Sales[500];
		int iterator = 0;

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

			// loop through resultset to access each row
			int loopcount = 0;
			while (rs.next()) {

				 //Reading the first row of the table and saving all details in sales object

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
				 * Flag checks if the customer-Product pair is already in the array
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
					if (s == null) { // check for null to avoid null pointer exception
						// no more data. 
						break;
					}

					if (rs.getString("cust").equals(s.getCustomer())
							&& rs.getString("prod").equals(s.getProduct())) {
						// matching customer and product present in Sales array
						
						s.setSum(rs.getInt("quant"));
						s.setCount();

						if (rs.getInt("quant") > s.getMaxQ()) {
							//update maximum sales quantity
							s.setMaxQ(rs.getInt("quant"));
							
							//convert to mm/dd//yyyy format
							s.setMaxDate(rs.getInt("month") + "/"
									+ rs.getInt("day") + "/"
									+ rs.getInt("year"));
							s.setMaxState(rs.getString("state"));

						}
						if (rs.getInt("quant") < s.getMinQ()) {
							// update minimum sales
							s.setMinQ(rs.getInt("quant"));
							
							//convert to mm/dd//yyyy format
							s.setMinDate(rs.getInt("month") + "/"
									+ rs.getInt("day") + "/"
									+ rs.getInt("year"));
							s.setMinState(rs.getString("state"));

						}
						flag = true;
					}

				} // end of for loop

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
					
					//convert to mm/dd//yyyy format
					sb.setMaxDate(rs.getInt("month") + "/" + rs.getInt("day")
							+ "/" + rs.getInt("year"));
					sb.setMinDate(rs.getInt("month") + "/" + rs.getInt("day")
							+ "/" + rs.getInt("year"));

					sArray[loopcount++] = sb;

				}

			} // end of while loop

			//display header section
			System.out.format("%1s%12s%12s%12s%12s%13s%12s%13s%14s",
					"CUSTOMER", "PRODUCT", "MAX_Q", "DATE", "ST", "MIN_Q",
					"DATE", "ST", "AVG\n");
			System.out.format("%1s%12s%12s%14s%11s%13s%13s%12s%15s",
					"========", "=======", "======", "=========", "====",
					"======", "=========", "====", "======\n");

			// Formatting date: ex- 1/1/2015 will result 01/01/2015
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

			// Printing all the fields 

			for (int i = 0; i < sArray.length; i++) {
				Sales s = sArray[i];
				//check if object is null to avoid null pointer exception
				if (s == null) {
					//no more elements .
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
			//error
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
		
		// end of program

	} // end of main method

}
