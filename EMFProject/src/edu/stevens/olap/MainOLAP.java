package edu.stevens.olap;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import edu.stevens.file.write.GenerateJavaFile;
import edu.stevens.olap.datastructure.MFStructureType;
import edu.stevens.olap.dbconnection.DatabaseConnection;

public class MainOLAP {

	public static void main(String[] args) throws Exception {
		
		
		//read input data from file
		File file = new File("files/inputtext.txt");
		System.out.println("Reading input data from :"+file.getAbsolutePath());
		
		Scanner inputFile = new Scanner(file);
		// Get input from file and storing all data into a hashmap where key is
		// the line number and value is the input string

		Map<Integer, String> dataMap = new HashMap<Integer, String>();

		int line = 0;
		// we are reading every line of input from the file till eof and adding
		// data into map
		while (inputFile.hasNext()) {
			line++;
			dataMap.put(line, inputFile.nextLine());
		}
		inputFile.close();
		
		for (Entry<Integer, String> m : dataMap.entrySet()) {
			System.out.println(m.getKey() +"--"+m.getValue());
		}
		
		// Instance of MFStructure class - is a datastructure that store 6
		// elements of phi and we populate data into those 6 elements
		MFStructureType mfstr = new MFStructureType();
		// send the hashmap to Mf structure for further processing
		mfstr.setData(dataMap);

		// create object of database connection to establish connection and to
		// get the map containing col name and its datatype
		DatabaseConnection db = new DatabaseConnection();
		Map<String, String> dataType = db.getMetaData();

		mfstr.setDataType(dataType);
		
		mfstr.toString();
		
		
		// check divide by 0 condition and if so throw error
		if(mfstr.getHavingClause() != null){
			//check if 0 and previous content is /. ex List can be [ col_name,/,0,..etc]
			
			int zeroIndex = mfstr.getHavingClauseList().indexOf("0"); // if exists it returns index or -1
			if(zeroIndex != -1 && mfstr.getHavingClauseList().get(zeroIndex-1).equals("/")){
				throw new Exception(" Error - Divide By Zero Not Allowed. Change Input and Try Again");
			}
			
			
		}
		
		
		GenerateJavaFile generateFile = new GenerateJavaFile();
		generateFile.setMft(mfstr);
		generateFile.setFilePath("generated/edu/stevens/generated/");
		generateFile.setJavaPackageName("edu.stevens.generated");
		
		generateFile.generateMFStructure("MFStructure");
		
		generateFile.generateJavaFile("GenerateRptDbMain");
		
		
	}

}
