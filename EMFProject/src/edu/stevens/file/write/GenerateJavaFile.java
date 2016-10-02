package edu.stevens.file.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import edu.stevens.olap.datastructure.AttributeType;
import edu.stevens.olap.datastructure.MFStructureType;

public class GenerateJavaFile {

	private static String _1t = "\t";
	private static String _2t = "\t\t";
	private static String _3t = "\t\t\t";
	private static String _4t = "\t\t\t\t";
	private static String _5t = "\t\t\t\t\t";
	private static String _6t = "\t\t\t\t\t\t";

	private static String mathOperator = "[-+*/><]";
	private static String numberRange = "[0-9]+";
	private static Map<String, String> operatorMap = new HashMap<String, String>();

	private String filePath;
	private String javaPackageName;
	private MFStructureType mft;

	private String sql ="SELECT * FROM Sales";
	
	public GenerateJavaFile() {
		// string to java equivalent operator in map.
		operatorMap.put("||", "||");
		operatorMap.put("&&", "&&");
		operatorMap.put(">=", ">=");
		operatorMap.put("<=", "<=");
		operatorMap.put(">", ">");
		operatorMap.put("<", "<");
		operatorMap.put("!=", "!=");
		operatorMap.put("=", "==");

	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @param javaPackageName
	 *            the javaPackageName to set
	 */
	public void setJavaPackageName(String javaPackageName) {
		this.javaPackageName = javaPackageName;
	}

	/**
	 * @param mft
	 *            the mft to set
	 */
	public void setMft(MFStructureType mft) {
		this.mft = mft;
	}

	public void generateMFStructure(String javaClassName) throws IOException {

		File outFile = new File(this.filePath + javaClassName + ".java");
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter writer = new PrintWriter(fWriter);

		// package name
		writer.println("package " + this.javaPackageName + ";");
		// print empty line
		writer.println();
		writer.println();

		writer.println("public class " + javaClassName + " {");
		writer.println();

		// print group by attributes - ex : cust, prod
		for (AttributeType att : this.mft.getGroupAttributes()) {
			if (att.getDataType().equals("int")) {
				writer.println(_1t + "Integer" + _3t + att.getColName() + ";");
			} else {
				writer.println(_1t + att.getDataType() + _3t + att.getColName() + ";");
			}
		}
		writer.println();

		// aggregate fn attributes
		for (AttributeType att : this.mft.getAggrFn()) {
			if (att.getAggrfn().equals("min")) {
				writer.println(_1t + att.getDataType() + _3t + att.getfVectColName() + " ="+Integer.MAX_VALUE +";");
			} else {
				writer.println(_1t + att.getDataType() + _3t + att.getfVectColName() + ";");
			}
		}
		writer.println();
		writer.println();

		// hash code methods for HashMap
		writer.println(_1t + "@Override");
		writer.println(_1t + "public int hashCode() {");
		writer.println(_2t + "int hash = 1;");

		// group by attributes make unique column to find hash code
		for (AttributeType at : this.mft.getGroupAttributes()) {
			writer.println(_2t + "hash = hash * 31 + (this." + at.getColName() + " == null ? 0 : this."
					+ at.getColName() + ".hashCode());");
		}
		writer.println(_2t + "return hash;");
		writer.println(_1t + "}");

		// print emptry lines
		writer.println();
		writer.println();

		// equals method for hash map
		writer.println(_1t + "@Override");
		writer.println(_1t + "public boolean equals(Object obj) {");
		writer.println(_2t + "if (obj instanceof " + javaClassName + ") {");
		writer.println(_3t + "MFStructure mf = (MFStructure) obj;");
		writer.println(_3t + "boolean flag = true;");

		// group by attributes make unique column to find hash code
		for (AttributeType at : this.mft.getGroupAttributes()) {
			writer.println(_3t + "flag = (flag && mf." + at.getColName() + ".equals(this." + at.getColName()
					+ ")? true : false);");
		}
		writer.println(_3t + "return flag;");
		writer.println(_2t + "}");

		writer.println(_2t + "return false;");
		writer.println(_1t + "}");

		// end of equals method

		// end of class
		writer.println();
		writer.println();
		writer.println("}");
		writer.close();
		writer.close();

		writer.println("}");
		writer.close();

		System.out.println("File Generated -" + outFile.getAbsolutePath());

	}

	public void generateJavaFile(String javaClassName) throws IOException {

		File outFile = new File(this.filePath + javaClassName + ".java");
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter writer = new PrintWriter(fWriter);

		// package name
		writer.println("package " + this.javaPackageName + ";");
		// print empty line
		writer.println();

		// imports
		writer.println("import java.sql.*;");
		writer.println("import java.util.*;");
		writer.println();

		writer.println("public class " + javaClassName + " {");
		writer.println();

		// static main method
		writer.println(_1t + "public static void main(String[] args) {");
		writer.println();

		writer.println(_2t + "System.out.println(\"------Report Generation Started-----\");");
		writer.println();

		// write connection details
		writer.println(_2t + "// connection details variables");
		writer.println(_2t + "String usr = \"postgres\";");
		writer.println(_2t + "String pwd = \"xyz\";");
		writer.println(_2t + "String url = \"jdbc:postgresql://localhost:5432/deepa\";");
		writer.println();

		writer.println(_2t + "try {");
		writer.println();
		writer.println(_3t + "// Loading Drivers");
		writer.println(_3t + "Class.forName(\"org.postgresql.Driver\");");
		writer.println();
		writer.println(_3t + "// Connecting to Server");
		writer.println(_3t + "Connection conn = DriverManager.getConnection(url, usr, pwd);");
		writer.println(_3t + "System.out.println(\"Success connecting server!\");");
		writer.println();

		writer.println(_3t + "// create statement and execute sql query");
		writer.println(_3t + "Statement stmt = conn.createStatement();");
		writer.println(_3t + "Map<MFStructure, MFStructure> resultMap = new HashMap<MFStructure, MFStructure>();");

		writer.println();

		writer.println(_3t + "// Number of scans = number of group variables + 1");
		writer.println(_3t + "for (int i = 0; i <= " + this.mft.getGrpVar() + "; i++) {");
		writer.println();

		writer.println(_3t + "ResultSet rs = stmt.executeQuery(\""+sql+"\");");
		writer.println();

		// while loop for result set
		writer.println(_3t + "while(rs.next()){");
		writer.println();

		writer.println(_4t + "// Algorithem logic goes here. Ex- update if exists otherwise insert into map collection");
		writer.println();

		writer.println(_4t + "MFStructure newMF = new MFStructure();");
		writer.println();

		// group by attributes make unique column to find hash code
		for (AttributeType at : this.mft.getGroupAttributes()) {
			if (at.getDataType().equals("int") || at.getDataType().equals("Integer")) {
				writer.println(_4t + "newMF." + at.getColName() + " = rs.getInt(\"" + at.getColName() + "\");");
			} else {
				writer.println(_4t + "newMF." + at.getColName() + " = rs.getString(\"" + at.getColName() + "\");");
			}

		}
		writer.println();

		writer.println(_4t + "// get existing data if already present in map. map will return null if data not present");
		writer.println(_4t + "MFStructure mfs = resultMap.get(newMF);");
		writer.println();

		writer.println(_4t + "boolean updateData = true; // indicates, data already present in hash map");
		writer.println(_4t + "if(mfs == null){ // data not present in hash map");
		writer.println(_5t + "updateData = false; // this indicates insert data into map");
		writer.println(_5t + "mfs = newMF;");
		writer.println(_4t + "}");
		writer.println();

		writer.println(_4t + "if(!updateData){ // add data if does not exists in hash map");
		writer.println(_5t + "resultMap.put(mfs, mfs);");
		writer.println(_4t + "}");
		writer.println();
		writer.println(_4t + "//-------------End of Algorithem---------");

		// writer.println(_4t
		// +"if(false){} // this is dummy, condition set to false always - go to else part"
		// );
		// predicates loop

		writer.println(_4t + "int value = -1;");
		writer.println(_4t + "MFStructure mf1 = new MFStructure();");
		writer.println(_4t + "for (Map.Entry<MFStructure, MFStructure> entry : resultMap.entrySet()) {");
		writer.println(_5t + "MFStructure mfstr = entry.getValue();");

		for (int gpv = 1; gpv <= this.mft.getGrpVar(); gpv++) {

			int index = -1;
			writer.print(_5t + "if( ( ");
			for (AttributeType at : this.mft.getPredicates()) {

				if (at.getIndex() != gpv) {
					continue;
				}
				if (index == -1) {
					index = at.getIndex();
				}
				// writer.println(_4t +"else if(rs.getString(\""+at.getColName()
				// +"\").equals(\""+at.getSuchThatValue()+"\")){" );
				if (at.getDataType() != null && at.getDataType().equals("int") && at.getSuchThatValue() == null) {
					if (at.getColName2().contains("avg")) {
						// if average, convert to sum/count
						writer.print(" (mfstr." + at.getColName2().replaceFirst("avg", "count") +">0 ? rs.getInt(\"" + at.getColName() + "\")"+this.operatorMap.get(at.getOperator()));
						writer.print(" (mfstr." + at.getColName2().replaceFirst("avg", "sum") + "/mfstr." + at.getColName2().replaceFirst("avg", "count") + ") : false ) ");
					} else{
						writer.print(" rs.getInt(\"" + at.getColName() + "\")"+this.operatorMap.get(at.getOperator()));
						writer.print(" mfstr." + at.getColName2() + " ");
					}
					
				} else if (at.getDataType() != null && at.getColName() != null && at.getSuchThatValue() == null) {
					if(at.getOperator().equals("!=")){
						writer.print("!");
					}
					writer.print("rs.getString(\"" + at.getColName() + "\").equals(mfstr." + at.getColName2() + ") ");
				} else if (at.getDataType() != null && at.getDataType().equals("int") && at.getSuchThatValue() != null) {
					writer.print("rs.getInt(\"" + at.getColName() + "\") "+ this.operatorMap.get(at.getOperator()) + at.getSuchThatValue() + " ");
				} else if (at.getDataType() != null && at.getColName() != null && at.getSuchThatValue() != null) {
					if(at.getOperator().equals("!=")){
						writer.print("!");
					}
					writer.print("rs.getString(\"" + at.getColName() + "\").equals(" + at.getSuchThatValue() + ") ");
				} else {
					writer.print(" " + at.getOperator() + " ");
				}
			}

			writer.println(" ) && i == " + index + " ){ ");
			writer.println();

			// start aggregate sum

			// based on aggregate function use the appropriate columns
			writer.println(_5t + "if(value == -1){");
			writer.println(_6t + "value = 1;");
			for (AttributeType fn : this.mft.getAggrFn()) {
				// if predicates column index is same as aggregate function
				// column index - ex : sum_quant_1 . here 1 is index
				if (index == fn.getIndex()) {

					if (fn.getAggrfn().equals("sum")) {
						writer.println(_6t + "mf1." + fn.getfVectColName() + " = mfstr." + fn.getfVectColName()
								+ "+rs.getInt(\"" + fn.getColName() + "\");");
					}

					if (fn.getAggrfn().equals("count")) {
						writer.println(_6t + "mf1." + fn.getfVectColName() + " = ++mfstr." + fn.getfVectColName() + ";");
					}

					if (fn.getAggrfn().equals("max")) {
						writer.println(_6t + "mf1." + fn.getfVectColName() + " = mfstr." + fn.getfVectColName()
								+ " < rs.getInt(\"" + fn.getColName() + "\") ? rs.getInt(\"" + fn.getColName()
								+ "\") : mfstr." + fn.getfVectColName() + ";");
					}
					if (fn.getAggrfn().equals("min")) {
						writer.println(_6t + "mf1." + fn.getfVectColName() + " = mfstr." + fn.getfVectColName()
								+ " > rs.getInt(\"" + fn.getColName() + "\") ? rs.getInt(\"" + fn.getColName()
								+ "\") : mfstr." + fn.getfVectColName() + ";");
					}

				}

			}

			writer.println(_5t + "}");
			writer.println(_5t + "if(value != -1){");

			for (AttributeType fn : this.mft.getAggrFn()) {
				// if predicates column index is same as aggregate function
				// column index - ex : sum_quant_1 . here 1 is index
				if (index == fn.getIndex()) {

					writer.println(_6t + "mfstr." + fn.getfVectColName() + " = mf1." + fn.getfVectColName() + ";");

				}

			}
			writer.println(_5t + "}");

			// end aggregate sum

			// if end symbol
			writer.println(_4t + "}");
		}

		writer.println();

		writer.println(_4t + "}"); // end of forloop
		writer.println();

		// end of while loop
		writer.println(_3t + "} //end of while loop");
		writer.println();
		writer.println(_3t + "} //end of for loop");
		writer.println();

		writer.println(_3t + "//Filter result based on having clause");
		writer.println();
		writer.println(_3t + "List<MFStructure> finalRsultList = new ArrayList<MFStructure>();");
		writer.println(_3t + "for (Map.Entry<MFStructure, MFStructure> entry : resultMap.entrySet()) {");
		writer.println(_4t + "MFStructure mfs = entry.getValue();");

		writer.println(_4t + "try {");

		if (this.mft.getHavingClause() != null) {
			writer.print(_5t + "if(");
		} else {
			writer.println(_4t + "finalRsultList.add(entry.getValue());");
		}

		// loop through each having column names and copy paste conditions
		for (String hc : this.mft.getHavingClauseList()) {

			if (hc.matches(mathOperator) || hc.matches(numberRange)) {
				writer.print(" " + hc);
			} else if (hc.contains("avg")) {
				// if average, convert to sum/count
				writer.print(" (mfs." + hc.replaceFirst("avg", "sum") + "/mfs." + hc.replaceFirst("avg", "count") + ")");
			} else if (operatorMap.containsKey(hc.toUpperCase())) {
				// convert or to ||, and to && etc from map lookup
				writer.print(" " + operatorMap.get(hc.toUpperCase()));
			} else {
				writer.print(" mfs." + hc);
			}
		}

		if (this.mft.getHavingClause() != null) {
			writer.println("){");
			writer.println(_6t + "finalRsultList.add(entry.getValue());");
			writer.println(_5t + "}");
		}
		writer.println(_4t + "}catch(Exception e){}");
		writer.println(_3t + "} //End of hashmap loop");

		writer.println();
		writer.println();
		writer.println(_3t + "stmt.close();");
		writer.println(_3t + "conn.close();");
		writer.println();

		// Print the Final results
		// Display Heading Row of data
		for (AttributeType sc : this.mft.getSelectCols()) {
			if(sc.getOperator()!=null){
			writer.println(_3t + "System.out.format(\"%-10s%9s\",\" " + sc.getColName() + sc.getOperator()
					+ sc.getColName2() + "\", \"\");");}
			else{
				writer.println(_3t + "System.out.format(\"%-10s%9s\",\" " + sc.getColName()+"\", \"\");");
			}

		}
		writer.println(_3t + "System.out.println();");
		// Display the actual results of query
		writer.println(_3t + "for (MFStructure m : finalRsultList) {");
		for (AttributeType i : this.mft.getSelectCols()) {
			if( i.getAggrfn()!=null && i.getAggrfn().equals("avg")) {
				if(i.getOperator()==null){
				writer.println(_4t + "System.out.format(\"%-10s%10s\",(m." + i.getColName().replaceFirst("avg", "count") +">0 ? m." + i.getColName().replaceFirst("avg", "sum") + "/m." + i.getColName().replaceFirst("avg", "count") + " : 0 ),\"\");");
				}
				else{
					if(i.getColName().contains("avg") && i.getColName2().contains("avg")){
						
						writer.println(_4t + "System.out.format(\"%-10s%10s\",(m." + i.getColName().replaceFirst("avg", "count") +">0 && m." + i.getColName2().replaceFirst("avg", "count") +">0 ? ((double)m." + i.getColName().replaceFirst("avg", "sum") + "/m." + i.getColName().replaceFirst("avg", "count")+")"+
						i.getOperator()+"(m."+i.getColName2().replaceFirst("avg", "sum") + "/m." + i.getColName2().replaceFirst("avg", "count") + ") :0),\"\");");
					}
					else{
						if(i.getColName().contains("avg") && !i.getColName2().contains("avg")){
							writer.println(_4t + "System.out.format(\"%-10s%10s\", (m." + i.getColName().replaceFirst("avg", "count") +">0 ? ((double)m." + i.getColName().replaceFirst("avg", "sum") + "/m." + i.getColName().replaceFirst("avg", "count")+")"+
								    i.getOperator()+"m."+i.getColName2() + " : 0 ),\"\");");
						}
						else if(!i.getColName().contains("avg") && i.getColName2().contains("avg")){
							writer.println(_4t + "System.out.format(\"%-10s%10s\", (m." + i.getColName2().replaceFirst("avg", "count") +">0 ? ((double)m." + i.getColName()+")"+
								    i.getOperator()+"(m."+i.getColName2().replaceFirst("avg", "sum") + "/m." + i.getColName2().replaceFirst("avg", "count") + ") : 0 ),\"\");");
						}
					}
					
				}
			}
			else if (i.getColName2() != null) {
				writer.println(_4t + "System.out.format(\"%-10s%10s\",(double) m." + i.getColName() +" "+ i.getOperator() +" m."+ i.getColName2()
						+ ", \"\");");
			} else {
				writer.println(_4t + "System.out.format(\"%-10s%10s\", m." + i.getColName() + ", \"\");");
			}
		}
		writer.println(_4t + "System.out.println();");

		writer.println(_3t + "}");

		// end of try
		writer.println(_2t + "} catch (Exception e) {");
		writer.println(_3t + "e.printStackTrace();");
		writer.println(_2t + "}");

		// end of main
		writer.println(_1t + "}");

		// end of class
		writer.println();
		writer.println();
		writer.println("}");
		writer.close();
		writer.close();

		writer.println("}");
		writer.close();

		System.out.println("File Generated -" + outFile.getAbsolutePath());

	}

}