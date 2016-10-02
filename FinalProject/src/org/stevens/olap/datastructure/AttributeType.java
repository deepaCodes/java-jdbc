package org.stevens.olap.datastructure;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AttributeType {

	private String colName;
	private String dataType;
	// for such that condition
	private String colVal;
	// For F_vect
	private int index;
	private String aggrfn;
	private String fVectColName;

	public AttributeType() {

	}

	public AttributeType(String coln) {
		colName = coln;
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
	 * @return the colVal
	 */
	public String getColVal() {
		return colVal;
	}

	/**
	 * @param colVal
	 *            the colVal to set
	 */
	public void setColVal(String colVal) {
		this.colVal = colVal;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//System.out.println(colName + "\t" + dataType+"\t"+ this.colVal+"\t"+this.index+"\t"+ this.aggrfn+"\t"+ this.fVectColName);
		//System.out.println(ReflectionToStringBuilder.toString(this));
		return ReflectionToStringBuilder.toString(this);
	}
}
