package edu.stevens.generated;

import java.sql.*;
import java.util.*;

public class GenerateRptDbMain {

	public static void main(String[] args) {

		System.out.println("------Report Generation Started-----");

		// connection details variables
		String usr = "postgres";
		String pwd = "xyz";
		String url = "jdbc:postgresql://localhost:5432/deepa";

		try {

			// Loading Drivers
			Class.forName("org.postgresql.Driver");

			// Connecting to Server
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			// create statement and execute sql query
			Statement stmt = conn.createStatement();
			Map<MFStructure, MFStructure> resultMap = new HashMap<MFStructure, MFStructure>();

			// Number of scans = number of group variables + 1
			for (int i = 0; i <= 1; i++) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			while(rs.next()){

				// Algorithem logic goes here. Ex- update if exists otherwise insert into map collection

				MFStructure newMF = new MFStructure();

				newMF.prod = rs.getString("prod");
				newMF.quant = rs.getInt("quant");

				// get existing data if already present in map. map will return null if data not present
				MFStructure mfs = resultMap.get(newMF);

				boolean updateData = true; // indicates, data already present in hash map
				if(mfs == null){ // data not present in hash map
					updateData = false; // this indicates insert data into map
					mfs = newMF;
				}

				if(!updateData){ // add data if does not exists in hash map
					resultMap.put(mfs, mfs);
				}

				//-------------End of Algorithem---------
				int value = -1;
				MFStructure mf1 = new MFStructure();
				for (Map.Entry<MFStructure, MFStructure> entry : resultMap.entrySet()) {
					MFStructure mfstr = entry.getValue();
					if( ( rs.getString("prod").equals(mfstr.prod)  ) && i == 1 ){ 

					if(value == -1){
						value = 1;
						mf1.max_quant_1 = mfstr.max_quant_1 < rs.getInt("quant") ? rs.getInt("quant") : mfstr.max_quant_1;
					}
					if(value != -1){
						mfstr.max_quant_1 = mf1.max_quant_1;
					}
				}

				}

			} //end of while loop

			} //end of for loop

			//Filter result based on having clause

			List<MFStructure> finalRsultList = new ArrayList<MFStructure>();
			for (Map.Entry<MFStructure, MFStructure> entry : resultMap.entrySet()) {
				MFStructure mfs = entry.getValue();
				try {
					if( mfs.quant < mfs.max_quant_1 / 4){
						finalRsultList.add(entry.getValue());
					}
				}catch(Exception e){}
			} //End of hashmap loop


			stmt.close();
			conn.close();

			System.out.format("%-10s%9s"," prod", "");
			System.out.format("%-10s%9s"," quant", "");
			System.out.println();
			for (MFStructure m : finalRsultList) {
				System.out.format("%-10s%10s", m.prod, "");
				System.out.format("%-10s%10s", m.quant, "");
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
