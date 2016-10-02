/*
 * @author Deepa Aswathaiah - CWID 10405689
1. unzip Assignment2.rar file
2. Java files are compiled using java version "1.8.0_65"
3. Import unzipped project folder (Assignment2) into eclipse workspace
4. Change connection details in report1/Report1Main.java,  report2/Report2Main.java and   report3/Report3Main.java
		String usr = "user_name_here"; 
		String pwd = "password_here";
		String url = "connection_url_here"; 

5. Report-2: run report2/Report2Main.java file


 */
package report2;

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
public class Report2Main {

	public static void main(String[] args)
	{
		
		//username and password variables
		String usr ="postgres";
		String pwd ="xyz";
		String url ="jdbc:postgresql://localhost:5432/deepa";
		
		ArrayList<DataStructure> dataList = new ArrayList<DataStructure>();
		
		try
		{
			//load driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}
		catch(Exception e)
		{
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try
		{
			//connect 
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			//create statement and execute sql
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales ");
			int month;
			int itt=0;
			boolean flag=false;
			
			// loop thorugh result
			while (rs.next())
			{
				if(itt==0){
					//populate first row
					DataStructure ds = new DataStructure();
					ds.setCustomer(rs.getString("cust"));
					ds.setProduct(rs.getString("prod"));
					month=rs.getInt("month");
					
					//q1
					if(month>=1 && month<=3){
						ds.setQ1Sum(rs.getInt("quant"));
						ds.setQ1Count();						
					}
					
					//q2
					if(month>=4 && month<=6){
						ds.setQ2Sum(rs.getInt("quant"));
						ds.setQ2Count();	
					}
					
					//q3
					if(month>=7 && month<=9){
						ds.setQ3Sum(rs.getInt("quant"));
						ds.setQ3Count();	
					}
					
					//q4
					if(month>=10 && month<=12){
						ds.setQ4Sum(rs.getInt("quant"));
						ds.setQ4Count();	
					}
					
					dataList.add(ds);
					itt++;
					continue;
				}
				
				flag=false;
				
				for (DataStructure ds_forloop : dataList) {
					
					// check for same customer and product
					if(rs.getString("cust").equals(ds_forloop.getCustomer()) && rs.getString("prod").equals(ds_forloop.getProduct())){
						month=rs.getInt("month");
						//q1
						if(month>=1 && month<=3){
							ds_forloop.setQ1Sum(ds_forloop.getQ1Sum()+rs.getInt("quant"));
							ds_forloop.setQ1Count();
						}
						
						//q2
						if(month>=4 && month<=6){
							ds_forloop.setQ2Sum(ds_forloop.getQ2Sum()+rs.getInt("quant"));
							ds_forloop.setQ2Count();
						}
						
						//q3
						if(month>=7 && month<=9){
							ds_forloop.setQ3Sum(ds_forloop.getQ3Sum()+rs.getInt("quant"));
							ds_forloop.setQ3Count();
						}
						
						//q4
						if(month>=10 && month<=12){
							ds_forloop.setQ4Sum(ds_forloop.getQ4Sum()+rs.getInt("quant"));
							ds_forloop.setQ4Count();
						}
						flag=true;
					}
					
				}
				
				//add new row data
				if(!flag){
					
					DataStructure ds = new DataStructure();
					ds.setCustomer(rs.getString("cust"));
					ds.setProduct(rs.getString("prod"));
					month=rs.getInt("month");
					if(month>=1 && month<=3){
						ds.setQ1Sum(rs.getInt("quant"));
						ds.setQ1Count();						
					}
					if(month>=4 && month<=6){
						ds.setQ2Sum(rs.getInt("quant"));
						ds.setQ2Count();	
					}
					if(month>=7 && month<=9){
						ds.setQ3Sum(rs.getInt("quant"));
						ds.setQ3Count();	
					}
					if(month>=10 && month<=12){
						ds.setQ4Sum(rs.getInt("quant"));
						ds.setQ4Count();	
					}
					dataList.add(ds);
				}
				
			}
			
			//display header section
			System.out.format("%1s%12s%15s%16s%16s","CUSTOMER", "PRODUCT", "QUARTER", "BEFORE_AVG", "AFTER_AVG \n");
			System.out.format("%1s%12s%16s%15s%16s","========", "=======", "========", "==========", "========= \n");
			
			for (DataStructure ds : dataList) {
				
				//Display Q1 data
			  	System.out.format("%-5s%9s", ds.getCustomer(), "");
				System.out.format("%-10s%2s", ds.getProduct(), "");
				System.out.format("%8s%6s", "Q1", "");
				System.out.format("%8s%6s", "<NULL>", "");
				System.out.format("%6d", ds.getAvgQ2());					
				System.out.println();
				
				//Display Q2 data
			  	System.out.format("%-5s%9s", ds.getCustomer(), "");
				System.out.format("%-10s%2s", ds.getProduct(), "");
				System.out.format("%8s%6s", "Q2", "");
				System.out.format("%8d%6s", ds.getAvgQ1(), "");
				System.out.format("%6d", ds.getAvgQ3());					
				System.out.println();
				
				//Display Q3 data
			  	System.out.format("%-5s%9s", ds.getCustomer(), "");
				System.out.format("%-10s%2s", ds.getProduct(), "");
				System.out.format("%8s%6s", "Q3", "");
				System.out.format("%8d%6s", ds.getAvgQ2(), "");
				System.out.format("%6d", ds.getAvgQ4());					
				System.out.println();
				
				//Display Q4 data
			  	System.out.format("%-5s%9s", ds.getCustomer(), "");
				System.out.format("%-10s%2s", ds.getProduct(), "");
				System.out.format("%8s%6s", "Q4", "");
				System.out.format("%8d%6s", ds.getAvgQ3(), "");
				System.out.format("%6s", "<NULL>");					
				System.out.println();
				
				/*
				System.out.println(ds.getCustomer()+"\t \t"+ ds.getProduct()+"\t    \t"+ "Q1"+ "\t \t"+"<null>"+"\t \t"+ds.getAvgQ2() );
				System.out.println(ds.getCustomer()+"\t \t"+ ds.getProduct()+"\t    \t"+ "Q2"+ "\t \t"+ds.getAvgQ1()+"\t \t"+ds.getAvgQ3() );
				System.out.println(ds.getCustomer()+"\t \t"+ ds.getProduct()+"\t    \t"+ "Q3"+ "\t \t"+ds.getAvgQ2()+"\t \t"+ds.getAvgQ4() );
				System.out.println(ds.getCustomer()+"\t \t"+ ds.getProduct()+"\t    \t"+ "Q4"+ "\t \t"+ds.getAvgQ3()+"\t \t"+"<null>" );
				*/
				//System.out.println(ds.getCustomer()+"\t"+ ds.getProduct()+"\t"+ ds.getAvgQ1()+"\t"+ds.getAvgQ2()+"\t"+ ds.getAvgQ3()+ "\t"+ ds.getAvgQ4());
				
				
			}
		}
		catch(SQLException e)
		{
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}

