/*
 * @author Deepa Aswathaiah - CWID 10405689
1. unzip Assignment2.rar file
2. Java files are compiled using java version "1.8.0_65"
3. Import unzipped project folder (Assignment2) into eclipse workspace
4. Change connection details in report1/Report1Main.java,  report2/Report2Main.java and   report3/Report3Main.java
		String usr = "user_name_here"; 
		String pwd = "password_here";
		String url = "connection_url_here"; 

5. Report-1: run report1/Report1Main.java file 


 */
package report1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Deepa Aswathaiah
 *
 */
public class Report1Main {

	public static void main(String[] args) {
		
		//user name and password variables
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";

		ArrayList<Report1DataType> Avglist = new ArrayList<Report1DataType>();
		
		try {
			//load driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}
		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try {
			// get connection
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");
			
			//create statement and execute statement
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
			
			int itt = 0;
			
			// loop through result
			while (rs.next()) {
				boolean found=false;
				if (itt == 0) {
					// populate first row
					Report1DataType ap = new Report1DataType();
					ap.setCustomer(rs.getString("cust"));
					ap.setProduct(rs.getString("prod"));
					ap.setQuant(rs.getInt("quant"));
					ap.incrementCount();
					Avglist.add(ap);
					itt++;
					continue;
				}
				
				//loop through previous result and add quant for the matching cust and product
				for (Report1DataType avgProd : Avglist) {
					if (rs.getString("cust").equals(avgProd.getCustomer()) && rs.getString("prod").equals(avgProd.getProduct())) {
						avgProd.setQuant(avgProd.getQuant()
								+ rs.getInt("quant"));
						avgProd.incrementCount();
						found=true;
					}
				}
				
				//add new data
				if(!found){
					Report1DataType ap = new Report1DataType();
					ap.setCustomer(rs.getString("cust"));
					ap.setProduct(rs.getString("prod"));
					ap.setQuant(rs.getInt("quant"));
					ap.incrementCount();
					Avglist.add(ap);
				}
				
			}
			//part-2 report
			for (Report1DataType avg1 : Avglist) {
				for (Report1DataType avg2 : Avglist) {
					//same product but other customer
					if(!avg1.getCustomer().equals(avg2.getCustomer()) && avg1.getProduct().equals(avg2.getProduct())){
						avg1.setOtherCustQuant(avg1.getOtherCustQuant()+avg2.getQuant());
						avg1.incrementOtherCustCount(avg2.getCount());
					}
					
				}
			}
			
			//part-3 report
			for (Report1DataType avg1 : Avglist) {
				for (Report1DataType avg2 : Avglist) {
					//same cust but other product
					if( avg1.getCustomer().equals(avg2.getCustomer())&& !avg1.getProduct().equals(avg2.getProduct())){
						
						avg1.setOtherProdQuant(avg1.getOtherProdQuant()+avg2.getQuant());
						avg1.incrementOtherProdCount(avg2.getCount());
					}
					
				}
			}
			
			//display header
			System.out.println("CUSTOMER \tPRODUCT \tCUST_AVG \tOTHER_CUST_AVG \tOTHER_PROD_AVG");
			System.out.println("======== \t======= \t======== \t============== \t==============");
			
			for (Report1DataType avgProd : Avglist) {
				//display data
				System.out.println(avgProd.getCustomer()+ "\t  \t"+ avgProd.getProduct()+ "\t \t"+ avgProd.getAverage()+"\t \t \t"+ avgProd.getOtherCustAvg() + "\t \t"
						+ avgProd.getOtherProdAverage());
			}

		}
		catch (SQLException e) {
			System.out
					.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
