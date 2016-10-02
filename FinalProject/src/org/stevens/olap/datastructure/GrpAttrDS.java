package org.stevens.olap.datastructure;

public class GrpAttrDS {
	private String colName;
	private String colVal;
	public GrpAttrDS(String colName, String colVal) {
		this.colName = colName;
		this.colVal = colVal;
	}
	/**
	 * @return the colName
	 */
	public String getColName() {
		return colName;
	}
	/**
	 * @param colName the colName to set
	 */
	public void setColName(String colName) {
		this.colName = colName;
	}
	/**
	 * @return the colVal
	 */
	public String getColVal() {
		return colVal;
	}
	/**
	 * @param colVal the colVal to set
	 */
	public void setColVal(String colVal) {
		this.colVal = colVal;
	}
	

}
