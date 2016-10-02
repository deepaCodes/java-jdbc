/*
 * @author Deepa Aswathaiah - CWID 10405689
1. unzip Assignment2.rar file
2. Java files are compiled using java version "1.8.0_65"
3. Import unzipped project folder (Assignment2) into eclipse workspace
4. Change connection details in report1/Report1Main.java,  report2/Report2Main.java and   report3/Report3Main.java
		String usr = "user_name_here"; 
		String pwd = "password_here";
		String url = "connection_url_here"; 

5. Report-3: run report3/Report3Main.java file


 */
package report3;
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
public class Report3Main {

	
		public static void main(String[] args)
		{
			//username and password
			String usr ="postgres";
			String pwd ="xyz";
			String url ="jdbc:postgresql://localhost:5432/deepa";
			
			//List to hold aggregated data
			ArrayList<Report3DataType> amList = new ArrayList<Report3DataType>();

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
				Connection conn = DriverManager.getConnection(url, usr, pwd);
				System.out.println("Success connecting server!");

				//create statement and execute query
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");
				
				int itt=0;
				
				//loop through result
				while (rs.next())
				{
					// very first row add into List
					if(itt==0){
						Report3DataType ds = new Report3DataType();
						ds.setCustomer(rs.getString("cust"));
						ds.setProduct(rs.getString("prod"));
						//q1
						if(rs.getInt("month")>=1 && rs.getInt("month")<=3)
						{
							ds.getQ1().setQuant(rs.getInt("quant"));
							ds.getQ1().incrementCount();
							ds.getQ1().setMinQuant(rs.getInt("quant"));
						}
						
						//q2
						if(rs.getInt("month")>=4 && rs.getInt("month")<=6)
						{
							ds.getQ2().setQuant(rs.getInt("quant"));
							ds.getQ2().incrementCount();
							ds.getQ2().setMinQuant(rs.getInt("quant"));
						}
						
						//q3
						if(rs.getInt("month")>=7 && rs.getInt("month")<=9)
						{
							ds.getQ3().setQuant(rs.getInt("quant"));
							ds.getQ3().incrementCount();
							ds.getQ3().setMinQuant(rs.getInt("quant"));
						}
						
						//q4
						if(rs.getInt("month")>=10 && rs.getInt("month")<=12)
						{
							ds.getQ4().setQuant(rs.getInt("quant"));
							ds.getQ4().incrementCount();
							ds.getQ4().setMinQuant(rs.getInt("quant"));
						}
						amList.add(ds);
						itt++;
						continue;						
					}
					
					//flag to search existing row in List
					boolean found=false;
					
					//loop through previously found result and find max, min and quarter
					for (Report3DataType ds3 : amList) {
						
						if(ds3.getCustomer().equals(rs.getString("cust")) && ds3.getProduct().equals(rs.getString("prod"))){
							
							//q1
							if(rs.getInt("month")>=1 && rs.getInt("month")<=3){
								ds3.getQ1().setQuant(rs.getInt("quant")+ ds3.getQ1().getQuant());
								ds3.getQ1().incrementCount();
								if(rs.getInt("quant")<ds3.getQ1().getMinQuant()){
									ds3.getQ1().setMinQuant(rs.getInt("quant"));
								}
							}
							
							//q2
							if(rs.getInt("month")>=4 && rs.getInt("month")<=6){
								ds3.getQ2().setQuant(rs.getInt("quant")+ ds3.getQ2().getQuant());
								ds3.getQ2().incrementCount();
								if(rs.getInt("quant")<ds3.getQ2().getMinQuant()){
									ds3.getQ2().setMinQuant(rs.getInt("quant"));
								}
							}
							
							//q3
							if(rs.getInt("month")>=7 && rs.getInt("month")<=9){
								ds3.getQ3().setQuant(rs.getInt("quant")+ ds3.getQ3().getQuant());
								ds3.getQ3().incrementCount();
								if(rs.getInt("quant")<ds3.getQ3().getMinQuant()){
									ds3.getQ3().setMinQuant(rs.getInt("quant"));
								}
							}
							
							//q4
							if(rs.getInt("month")>=10 && rs.getInt("month")<=12){
								ds3.getQ4().setQuant(rs.getInt("quant")+ ds3.getQ4().getQuant());
								ds3.getQ4().incrementCount();
								if(rs.getInt("quant")<ds3.getQ4().getMinQuant()){
									ds3.getQ4().setMinQuant(rs.getInt("quant"));
								}
							}
							found=true;
						}
						
					}
					
					// add new data into List. 
					if(!found){
						
						Report3DataType ds = new Report3DataType();
						ds.setCustomer(rs.getString("cust"));
						ds.setProduct(rs.getString("prod"));
						//q1
						if(rs.getInt("month")>=1 && rs.getInt("month")<=3)
						{
							ds.getQ1().setQuant(rs.getInt("quant"));
							ds.getQ1().incrementCount();
							ds.getQ1().setMinQuant(rs.getInt("quant"));
						}
						
						//q2
						if(rs.getInt("month")>=4 && rs.getInt("month")<=6)
						{
							ds.getQ2().setQuant(rs.getInt("quant"));
							ds.getQ2().incrementCount();
							ds.getQ2().setMinQuant(rs.getInt("quant"));
						}
						
						//q3
						if(rs.getInt("month")>=7 && rs.getInt("month")<=9)
						{
							ds.getQ3().setQuant(rs.getInt("quant"));
							ds.getQ3().incrementCount();
							ds.getQ3().setMinQuant(rs.getInt("quant"));
						}
						
						//q4
						if(rs.getInt("month")>=10 && rs.getInt("month")<=12)
						{
							ds.getQ4().setQuant(rs.getInt("quant"));
							ds.getQ4().incrementCount();
							ds.getQ4().setMinQuant(rs.getInt("quant"));
						}
						amList.add(ds);
					
					}
					
					
				} // end of while loop
				
				
				//execute statement for 2nd scan
				rs = stmt.executeQuery("SELECT * FROM Sales");
				
				//loop through result to find count 
				while(rs.next()){
					
					
					for (Report3DataType ds : amList) {
						
						if(rs.getString("cust").equals(ds.getCustomer()) && rs.getString("prod").equals(ds.getProduct())){
							
							//q1
							if(rs.getInt("month")>=1 && rs.getInt("month")<=3){
								if(rs.getInt("quant")>=ds.getQ2().getMinQuant() && rs.getInt("quant")<=ds.getQ2().getAverage() ){
									ds.getQ2().incrementPreviousQCount();
								}
							}
							
							//q2
							if(rs.getInt("month")>=4 && rs.getInt("month")<=6){
								if(rs.getInt("quant")>=ds.getQ1().getMinQuant() && rs.getInt("quant")<=ds.getQ1().getAverage() ){
									ds.getQ1().incrementNextQCount();
								}
								if(rs.getInt("quant")>=ds.getQ3().getMinQuant() && rs.getInt("quant")<=ds.getQ3().getAverage() ){
									ds.getQ3().incrementPreviousQCount();
								}
							}
							
							//q3
							if(rs.getInt("month")>=7 && rs.getInt("month")<=9){
								if(rs.getInt("quant")>=ds.getQ2().getMinQuant() && rs.getInt("quant")<=ds.getQ2().getAverage() ){
									ds.getQ2().incrementNextQCount();
								}
								if(rs.getInt("quant")>=ds.getQ4().getMinQuant() && rs.getInt("quant")<=ds.getQ4().getAverage() ){
									ds.getQ4().incrementPreviousQCount();
								}
							}
							
							//q4
							if(rs.getInt("month")>=10 && rs.getInt("month")<=12){
								if(rs.getInt("quant")>=ds.getQ3().getMinQuant() && rs.getInt("quant")<=ds.getQ3().getAverage() ){
									ds.getQ3().incrementNextQCount();
								}
							}
						}
					}
					
				} // end of while loop

				//display header section
				System.out.format("%1s%12s%15s%16s%16s","CUSTOMER", "PRODUCT", "QUARTER", "BEFORE_TOT", "AFTER_TOT \n");
				System.out.format("%1s%12s%16s%15s%16s","========", "=======", "========", "==========", "========= \n");
				
				//display result
				for (Report3DataType ds3 : amList) {
					
					if(ds3.getQ1().getCount()==0){
						ds3.getQ1().setMinQuant(0);
					}
					if(ds3.getQ2().getCount()==0){
						ds3.getQ2().setMinQuant(0);
					}
					if(ds3.getQ3().getCount()==0){
						ds3.getQ3().setMinQuant(0);
					}
					if(ds3.getQ4().getCount()==0){
						ds3.getQ4().setMinQuant(0);
					}
				
					//Display Q1 data
				  	System.out.format("%-5s%9s", ds3.getCustomer(), "");
					System.out.format("%-10s%2s", ds3.getProduct(), "");
					System.out.format("%8s%6s", "Q1", "");
					System.out.format("%8s%6s", "<NULL>", "");
					System.out.format("%6d", ds3.getQ1().getNextQCount());					
					System.out.println();
					
					//Display Q2 data
					System.out.format("%-5s%9s", ds3.getCustomer(), "");
					System.out.format("%-10s%2s", ds3.getProduct(), "");
					System.out.format("%8s%6s", "Q2", "");
					System.out.format("%8d%6s", ds3.getQ2().getPreviousQCount(), "");
					System.out.format("%6d", ds3.getQ2().getNextQCount());					
					System.out.println();
					
					//Display Q3 data
					System.out.format("%-5s%9s", ds3.getCustomer(), "");
					System.out.format("%-10s%2s", ds3.getProduct(), "");
					System.out.format("%8s%6s", "Q3", "");
					System.out.format("%8d%6s", ds3.getQ3().getPreviousQCount(), "");
					System.out.format("%6d", ds3.getQ3().getNextQCount());					
					System.out.println();
					
					//Display Q4 data
					System.out.format("%-5s%9s", ds3.getCustomer(), "");
					System.out.format("%-10s%2s", ds3.getProduct(), "");
					System.out.format("%8s%6s", "Q4", "");
					System.out.format("%8d%6s", ds3.getQ4().getPreviousQCount(), "");
					System.out.format("%6s", "<NULL>");					
					System.out.println();
					
				  
				} //end of for loop
				
			} //end of try
			catch(SQLException e){
				System.out.println("Connection URL or username or password errors!");
				e.printStackTrace();
			}
			

			//end of main method
		}

	}


