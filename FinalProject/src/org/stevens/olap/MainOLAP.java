package org.stevens.olap;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.stevens.olap.datastructure.MFStructure;
import org.stevens.olap.dbconnection.CalculateAggrDb;
import org.stevens.olap.dbconnection.DatabaseConnection;

public class MainOLAP {

	public static void main(String[] args) throws SQLException, IOException {
		Scanner inputFile = new Scanner(new File("inputtext.txt"));
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
		// Instance of MFStructure class - is a datastructure that store 6
		// elements of phi and we populate data into those 6 elements
		MFStructure mfstr = new MFStructure();
		// send the hashmap to Mf structure for further processing
		mfstr.setData(dataMap);

		// create object of database connection to establish connection and to
		// get the map containing col name and its datatype
		DatabaseConnection db = new DatabaseConnection();
		Map<String, String> dataType = db.getMetaData();

		/*
		 * for (Entry<String, String> entry : dataType.entrySet()) {
		 * System.out.println(entry.getKey() + " " + entry.getValue()); }
		 */
		mfstr.setDataType(dataType);

		mfstr.displayData();
		mfstr.toString();
		mfstr.printToFile("outputext.txt");
		CalculateAggrDb c = new CalculateAggrDb();
		c.compareNCalculate(mfstr);
		//c.test(mfstr);
	}

}
