package edu.stevens.olap.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MFStructureType {

	private List<AttributeType> selectCols = new ArrayList<AttributeType>();
	private int grpVar;
	
	private List<AttributeType> groupAttributes = new ArrayList<AttributeType>();
	
	private List<AttributeType> aggrFn = new ArrayList<AttributeType>();
	
	private List<AttributeType> predicates = new ArrayList<AttributeType>();
	
	private String havingClause;
	
	private List<String> havingClauseList = new ArrayList<String>();
	
	public MFStructureType() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void setData(Map<Integer, String> dataMap){
		
		// separating attribute list by ',' and convert array to A list for attr
		String temp = dataMap.get(1);
		String[] sarray = temp.split(",");
		for (String s : sarray) { //we want to split data further by math operators here
			AttributeType at = new AttributeType(s); // we have operator field empty here
			selectCols.add(at);
		}
		
		grpVar = Integer.parseInt(dataMap.get(2));
		
		// separating attribute list by ',' and convert array to a list for
		// grpattr
		temp = dataMap.get(3);
		sarray = temp.split(",");
		for (String grpByCol : sarray) {
			AttributeType at = new AttributeType(grpByCol);
			groupAttributes.add(at);
		}
		
		// separating attribute list by ',' and convert array to A list for
		// aggrFn
		temp = dataMap.get(4);
		sarray = temp.split(",");
		for (String s : sarray) {
			System.out.println(s);
			String[] s2array = s.split("_");
			
			// Split by '_' to divide 1_sum_quant into sum, quant, 1
			AttributeType at = new AttributeType();
			// If avg function , then print the count of it
			if (s2array[0].equals("avg")) {
				at.setIndex(Integer.parseInt(s2array[2]));
				at.setAggrfn("count");
				at.setColName(s2array[1]);
				at.setfVectColName("count_"+at.getColName() + "_" + at.getIndex());
				
				aggrFn.add(at);
			} else {
				System.out.println(s2array[2]);
				at.setIndex(Integer.parseInt(s2array[2]));
				at.setAggrfn(s2array[0]);
				at.setfVectColName(s);
				at.setColName(s2array[1]);
				
				aggrFn.add(at);
			}
		}
		int i = 5;
		// to separate the select condition , dataMap.size gives the number of
		// elements in select condition
		while (i < (5+this.grpVar) ) {
			String tempS = dataMap.get(i);
			System.out.println(tempS);
			
			String[] sarray2 = tempS.split(" ");
			
			int index = -1;
			for (String string : sarray2) {
				System.out.println(string);
				
				if(string.split("~").length > 1){
					String[] sa = string.split("~");
					String left = sa[0];
					String right = sa[2];
					String ops = sa[1];
					AttributeType ar = new AttributeType();
					ar.setColName(left.split("_")[0]);
					
					//check for actual value
					if(right.startsWith("\"") && right.endsWith("\"")){
						ar.setSuchThatValue(right);
					} else {
						ar.setColName2(right);
					}
					index = Integer.parseInt(left.split("_")[1]);
					ar.setIndex(index);
					ar.setOperator(ops);
					predicates.add(ar);
				} else{
					AttributeType ar = new AttributeType();
					
					if(index != -1){
						ar.setIndex(index);
						ar.setOperator(string);
						predicates.add(ar);
					}
				}
				
			}
			
			i++;
		}
		
		System.out.println("value of i ="+i);
		
		if(dataMap.size() >= i) {
			havingClause = dataMap.get(dataMap.size());
			this.havingClauseList.addAll(Arrays.asList(havingClause.split(" ")));
		}
		
	}
	
	
	// here we set data type for each colname

	public void setDataType(Map<String, String> typeMap) {
		// itterate through list- access the colname and if it matches the map's
		// colname then set the corresponding datatype
		for (AttributeType att : selectCols) {
			att.setDataType(typeMap.get(att.getColName()));
		}
		for (AttributeType att : groupAttributes) {
			att.setDataType(typeMap.get(att.getColName()));
		}
		
		for (AttributeType att : aggrFn) {
			att.setDataType(typeMap.get(att.getColName()));
		}
		
		for (AttributeType att : this.predicates) {
			att.setDataType(typeMap.get(att.getColName()));
		}

	}

	/**
	 * @return the selectCols
	 */
	public List<AttributeType> getSelectCols() {
		return selectCols;
	}

	/**
	 * @return the grpVar
	 */
	public int getGrpVar() {
		return grpVar;
	}

	/**
	 * @return the groupAttributes
	 */
	public List<AttributeType> getGroupAttributes() {
		return groupAttributes;
	}

	/**
	 * @return the aggrFn
	 */
	public List<AttributeType> getAggrFn() {
		return aggrFn;
	}

	/**
	 * @return the predicates
	 */
	public List<AttributeType> getPredicates() {
		return predicates;
	}

	/**
	 * @return the havingClause
	 */
	public String getHavingClause() {
		return havingClause;
	}

	/**
	 * @return the havingClauseList
	 */
	public List<String> getHavingClauseList() {
		return havingClauseList;
	}
	
	
	@Override
	public String toString() {
		System.out.println(ReflectionToStringBuilder.toString(this,ToStringStyle.MULTI_LINE_STYLE));
		return "";
	}
	
}
