package org.stevens.olap.datastructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class MFStructure {
	// contains all 6 input elements of phi
	private List<AttributeType> attr = new ArrayList<AttributeType>(); // AttributeTyp
																		// is a
																		// custom
																		// class
																		// that
																		// holds
																		// the
																		// colname
																		// and
																		// its
																		// datatype
	private int grpVar;
	private List<AttributeType> grpAttr = new ArrayList<AttributeType>();
	private List<AttributeType> aggrFn = new ArrayList<AttributeType>();
	private List<AttributeType> predicates = new ArrayList<AttributeType>();
	private String havingClause;

	// default constructor
	public MFStructure() {

	}

	// process hashmap data and split it about ','
	public void setData(Map<Integer, String> dataMap) {
		// separating attribute list by ',' and convert array to A list for attr
		String temp = dataMap.get(1);
		String[] sarray = temp.split(",");
		for (String s : sarray) {
			AttributeType at = new AttributeType(s);
			attr.add(at);
		}

		grpVar = Integer.parseInt(dataMap.get(2));

		// separating attribute list by ',' and convert array to a list for
		// grpattr
		temp = dataMap.get(3);
		sarray = temp.split(",");
		for (String s : sarray) {
			AttributeType at = new AttributeType(s);
			grpAttr.add(at);
		}

		// separating attribute list by ',' and convert array to A list for
		// aggrFn
		temp = dataMap.get(4);
		sarray = temp.split(",");
		for (String s : sarray) {
			String[] s2array = s.split("_");
			// Split by '_' to divide 1_sum_quant into 1, sum, quant
			AttributeType at = new AttributeType();
			// If avg function , then print the count of it
			if (s2array[1].equals("avg")) {
				at.setIndex(Integer.parseInt(s2array[0]));
				at.setAggrfn("count");
				at.setColName(s2array[2]);
				at.setfVectColName(at.getIndex() + "_count_" + at.getColName());
				aggrFn.add(at);
			} else {
				at.setIndex(Integer.parseInt(s2array[0]));
				at.setAggrfn(s2array[1]);
				at.setfVectColName(s);
				at.setColName(s2array[2]);
				aggrFn.add(at);
			}
		}
		int i = 5;
		// to separate the select condition , dataMap.size gives the number of
		// elements in select condition
		while (i < dataMap.size()) {
			sarray = dataMap.get(i).split("=");
			AttributeType ar = new AttributeType();
			ar.setColName(sarray[0]);
			ar.setColVal(sarray[1]);
			predicates.add(ar);
			i++;
		}
		havingClause = dataMap.get(dataMap.size());

	}

	// here we set data type for each colname

	public void setDataType(Map<String, String> typeMap) {
		// itterate through list- access the colname and if it matches the map's
		// colname then set the corresponding datatype
		for (AttributeType att : attr) {
			att.setDataType(typeMap.get(att.getColName()));
		}
		for (AttributeType att : grpAttr) {
			att.setDataType(typeMap.get(att.getColName()));
		}
		for (AttributeType att : aggrFn) {
			att.setDataType(typeMap.get(att.getColName()));
		}

	}

	public void displayData() {
		for (AttributeType att : grpAttr) {
			System.out.println(att.getColName() + " " + att.getDataType());
		}
		for (AttributeType att : aggrFn) {
			System.out.println(att.getfVectColName() + " " + att.getDataType());
		}
	}

	public void printToFile(String outputFile) throws IOException {
		File outFile = new File(outputFile);
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter writer = new PrintWriter(fWriter);
		writer.println("public class MFStructure {");

		for (AttributeType att : grpAttr) {
			writer.println("\t" + att.getDataType() + "\t\t" + att.getColName());
		}
		for (AttributeType att : aggrFn) {
			writer.println("\t" + att.getDataType() + "\t\t" + att.getfVectColName());
		}
		writer.println("}");
		writer.close();
	}

	/**
	 * @return the attr
	 */
	public List<AttributeType> getAttr() {
		return attr;
	}

	/**
	 * @param attr
	 *            the attr to set
	 */
	public void setAttr(List<AttributeType> attr) {
		this.attr = attr;
	}

	/**
	 * @return the grpVar
	 */
	public int getGrpVar() {
		return grpVar;
	}

	/**
	 * @param grpVar
	 *            the grpVar to set
	 */
	public void setGrpVar(int grpVar) {
		this.grpVar = grpVar;
	}

	/**
	 * @return the grpAttr
	 */
	public List<AttributeType> getGrpAttr() {
		return grpAttr;
	}

	/**
	 * @param grpAttr
	 *            the grpAttr to set
	 */
	public void setGrpAttr(List<AttributeType> grpAttr) {
		this.grpAttr = grpAttr;
	}

	/**
	 * @return the aggrFn
	 */
	public List<AttributeType> getAggrFn() {
		return aggrFn;
	}

	/**
	 * @param aggrFn
	 *            the aggrFn to set
	 */
	public void setAggrFn(List<AttributeType> aggrFn) {
		this.aggrFn = aggrFn;
	}

	/**
	 * @return the predicates
	 */
	public List<AttributeType> getPredicates() {
		return predicates;
	}

	/**
	 * @param predicates
	 *            the predicates to set
	 */
	public void setPredicates(List<AttributeType> predicates) {
		this.predicates = predicates;
	}

	/**
	 * @return the havingClause
	 */
	public String getHavingClause() {
		return havingClause;
	}

	/**
	 * @param havingClause
	 *            the havingClause to set
	 */
	public void setHavingClause(String havingClause) {
		this.havingClause = havingClause;
	}

	@Override
	public String toString() {
		/*for (AttributeType a : predicates) {
			a.toString();
		}*/
		
		System.out.println(ReflectionToStringBuilder.toString(this));
		return " ";
	}

}
