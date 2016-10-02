/*
 * @author Deepa Aswathaiah - CWID 10405689
	1. unzip dbms_assignment_1.rar file
	2. Java files are compiled using java version "1.8.0_65"
	3. Import unzipped project folder (dbms_assignment_1) into eclipse workspace
	4. Change connection details in report2/StateResultMain.java
		String usr = "user_name_here"; 
		String pwd = "password_here";
		String url = "connection_url_here";
	5. Report-2: run report2/StateResultMain.java file

 * Pseudocode:
 * while(rs.next()){
 * Insert the first row into Customer array and also initialize the 
 * StateQ array within Customer array.
 * 
 * if(customer-product exists in array already){
 * 		if(it belongs to same state in StateQ array){
 * 			compare the quantity and update maximum and minimum quantities 
 * 			}
 * 		Insert into StateQ array
 * }
 * Insert the details into Customer array and initialize StateQ array too
 * }
 * Print output  
 */

package report2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main Class to calculate maximum sales quantities for NY and NJ and minimum sales quantities for CT
 *  and print result 
 *
 */
public class StateResultMain {

	/**
	 * @author Deepa Aswathaiah - CWID 10405689
	 * static main method
	 * Main method to calculate maximum sales quantities for NY and NJ and minimum sales quantities for CT
	 * @param args
	 */
	public static void main(String[] args) {
		
		//connection variables
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";

		//customer array declaration
		Customer[] carray = new Customer[800];

		// Loading Drivers

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		// Connecting to Server

		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			// create statement from connection object and execute sql query
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
			int itt = 0;

			// loop through resultset
			while (rs.next()) {

				String state = rs.getString("state");

				// Data is colleted and processed only if the states are NY, NJ
				// or CT, else next row is read

				if (!(state.equals("NY") || state.equals("NJ") || state
						.equals("CT"))) {
					continue;
				}
				// Read the first row and save the data into Customer array and
				// save the data specific to state in StateQ array
				if (itt == 0) {
					Customer cb = new Customer();
					cb.setCust(rs.getString("cust"));
					cb.setProd(rs.getString("prod"));

					StateQ sq = new StateQ();
					sq.setState(rs.getString("state"));
					
					//date format mm/dd/yyyy
					sq.setDate_max(rs.getInt("month") + "/"
							+ rs.getString("day") + "/" + rs.getInt("year"));
					sq.setDate_min(rs.getInt("month") + "/"
							+ rs.getString("day") + "/" + rs.getInt("year"));
					sq.setYear(rs.getInt("year"));
					sq.setMax(rs.getInt("quant"));
					sq.setMin(rs.getInt("quant"));
					cb.statearray[cb.pos++] = sq;

					carray[itt++] = cb;
					continue;

				}
				/*
				 * Flag and flag2 checks if the Name-Product pair for a state is
				 * already in the array or not
				 */
				boolean flag = true;
				for (int i = 0; i < carray.length; i++) {

					Customer cb = carray[i];

					// Deals with null pointer exception

					if (cb == null) {
						break;
					}

					//check if matching customer and product found
					if (cb.getCust().equals(rs.getString("cust"))
							&& cb.getProd().equals(rs.getString("prod"))) {
						boolean flag2 = true;
						for (int j = 0; j < cb.statearray.length; j++) {

							if (cb.statearray[j] == null) {
								break;
							}
							
							//check if same state present in array
							if (rs.getString("state").equals(
									cb.statearray[j].getState())) {
								
								//update maximm quantity and its date
								if (rs.getInt("quant") > cb.statearray[j]
										.getMax()) {
									cb.statearray[j].setMax(rs.getInt("quant"));
									cb.statearray[j].setDate_max(rs
											.getString("month")
											+ "/"
											+ rs.getString("day")
											+ "/"
											+ rs.getInt("year"));
									cb.statearray[j].setYear(rs.getInt("year"));

								}
								
								//update minimum quantity and its date
								if (rs.getInt("quant") < cb.statearray[j]
										.getMin()) {
									cb.statearray[j].setMin(rs.getInt("quant"));
									cb.statearray[j].setDate_min(rs
											.getString("month")
											+ "/"
											+ rs.getString("day")
											+ "/"
											+ rs.getString("year"));

								}
								flag2 = false;
							}
						} // end of for-loop
						/*
						 * If Details of a state is not present in the array,
						 * add it into the array
						 */
						if (flag2) {
							StateQ sq = new StateQ();
							sq.setState(rs.getString("state"));
							sq.setDate_max(rs.getString("month") + "/"
									+ rs.getString("day") + "/"
									+ rs.getString("year"));
							sq.setDate_min(rs.getString("month") + "/"
									+ rs.getString("day") + "/"
									+ rs.getString("year"));
							sq.setYear(rs.getInt("year"));
							sq.setMax(rs.getInt("quant"));
							sq.setMin(rs.getInt("quant"));
							cb.statearray[cb.pos++] = sq;

						}

						flag = false;

					}

				}
				// if the customer-product pair is not already in array add to
				// it

				if (flag) {

					Customer c = new Customer();
					c.setCust(rs.getString("cust"));
					c.setProd(rs.getString("prod"));

					StateQ sq = new StateQ();
					sq.setState(rs.getString("state"));
					sq.setDate_max(rs.getString("month") + "/"
							+ rs.getString("day") + "/" + rs.getString("year"));
					sq.setDate_min(rs.getString("month") + "/"
							+ rs.getString("day") + "/" + rs.getString("year"));
					sq.setYear(rs.getInt("year"));

					sq.setMax(rs.getInt("quant"));
					sq.setMin(rs.getInt("quant"));
					c.statearray[c.pos++] = sq;

					carray[itt++] = c;

				}

			} // end of while loop

			
			//display header columns
			System.out.format("%1s%15s%15s%20s%20s%20s%20s%20s", "CUSTOMER",
					"PRODUCT", "NY_MAX", "Max_DATE", "NJ_MAX", "Max_DATE",
					"CT_MIN", "Min_DATE\n");
			System.out.format("%1s%15s%15s%20s%20s%20s%20s%20s", "========",
					"=======", "======", "========", "======", "========",
					"======", "========\n");
			
			//Formatting date: ex- 1/1/2015 will result 01/01/2015
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			for (int i = 0; i < carray.length; i++) {

				Customer c = carray[i];
				if (c == null) {
					break;
				}

				System.out.format("%-16s%-16s", c.getCust(), c.getProd());
				
				//NY state info printing
				for (int j = 0; j < c.statearray.length; j++) {
					StateQ st = c.statearray[j];

					if (st == null) {
						break;
					}
					
					//check if state is NY
					if (st.getState().equals("NY")) {
						if (st.getYear() >= 2000 && st.getYear() <= 2005) {
							System.out.format("%-18s%-22s", st.getMax(),
									df.format(new Date(st.getDate_max())));

						} else {
							System.out.format("%-18s%-22s", "", "");
						}
					}

				}

				// display NJ state info
				for (int j = 0; j < c.statearray.length; j++) {
					StateQ st = c.statearray[j];

					if (st == null) {
						break;
					}
					//check for NJ state
					if (st.getState().equals("NJ")) {
						if (st.getYear() >= 2000 && st.getYear() <= 2005) {
							System.out.format("%-18s%-22s", st.getMax(),
									df.format(new Date(st.getDate_max())));
						} else {
							System.out.format("%-18s%-22s", "", "");
						}
					}

				}

				// CT state info
				for (int j = 0; j < c.statearray.length; j++) {
					StateQ st = c.statearray[j];

					if (st == null) {
						break;
					}
					
					//check fo CT state
					if (st.getState().equals("CT")) {
						System.out.format("%-18s%-10s", st.getMin(),
								df.format(new Date(st.getDate_min())));
					}

				}
				System.out.println();
			}
		}

		catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

		
		//end of program
	} // end of main static method

}
