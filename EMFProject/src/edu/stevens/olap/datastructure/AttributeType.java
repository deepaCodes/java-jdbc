package edu.stevens.olap.datastructure;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AttributeType {

	private String colName;
	private String dataType;
	// for such that condition
	private String suchThatValue;
	// For F_vect
	private int index;
	private String aggrfn;
	private String fVectColName;
	
	private String operator;
	private String colName2;

	public AttributeType() {

	}

	public AttributeType(String coln) {
		String[] sarray = coln.split(" ");
		if(sarray.length==1)
		colName=sarray[0];
		else{
			colName=sarray[0];
			operator= sarray[1];
			colName2 = sarray[2];
		}
		if(coln.contains("avg")){
			aggrfn="avg";
		}
		else if(coln.contains("max")){
			aggrfn="max";
		}
		else if(coln.contains("min")){
			aggrfn="min";
		}
		else if(coln.contains("count")){
			aggrfn="count";
		}
		
		
	}

	/**
	 * @return the colName
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @param colName
	 *            the colName to set
	 */
	public void setColName(String colName) {
		this.colName = colName;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the aggrfn
	 */
	public String getAggrfn() {
		return aggrfn;
	}

	/**
	 * @param aggrfn
	 *            the aggrfn to set
	 */
	public void setAggrfn(String aggrfn) {
		this.aggrfn = aggrfn;
	}

	/**
	 * @return the fVectColName
	 */
	public String getfVectColName() {
		return fVectColName;
	}

	/**
	 * @param fVectColName
	 *            the fVectColName to set
	 */
	public void setfVectColName(String fVectColName) {
		this.fVectColName = fVectColName;
	}

	
	/**
	 * @return the suchThatValue
	 */
	public String getSuchThatValue() {
		return suchThatValue;
	}

	/**
	 * @param suchThatValue the suchThatValue to set
	 */
	public void setSuchThatValue(String suchThatValue) {
		this.suchThatValue = suchThatValue;
	}
	
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the colName2
	 */
	public String getColName2() {
		return colName2;
	}

	/**
	 * @param colName2 the colName2 to set
	 */
	public void setColName2(String colName2) {
		this.colName2 = colName2;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
