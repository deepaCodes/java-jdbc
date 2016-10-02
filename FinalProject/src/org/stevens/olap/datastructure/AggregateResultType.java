package org.stevens.olap.datastructure;

import java.util.ArrayList;
import java.util.List;

public class AggregateResultType {
	private int sumQuant;
	private int countQuant;
	private int minQuant = Integer.MAX_VALUE;
	private int maxQuant;
	private List<GrpAttrDS> gd = new ArrayList<GrpAttrDS>();
	/**
	 * @return the sumQuant
	 */
	public int getSumQuant() {
		return sumQuant;
	}
	/**
	 * @param sumQuant the sumQuant to set
	 */
	public void setSumQuant(int sumQuant) {
		this.sumQuant = this.sumQuant + sumQuant;
	}
	/**
	 * @return the countQuant
	 */
	public int getCountQuant() {
		return countQuant;
	}
	/**
	 * @param countQuant the countQuant to set
	 *//*
	public void setCountQuant(int countQuant) {
		this.countQuant = countQuant;
	}
	*/
	public void incrementQuantCount(){
		this.countQuant++;
	}
	
	/**
	 * @return the minQuant
	 */
	public int getMinQuant() {
		return minQuant;
	}
	/**
	 * @param minQuant the minQuant to set
	 */
	public void setMinQuant(int minQuant) {
		this.minQuant = minQuant;
	}
	/**
	 * @return the maxQuant
	 */
	public int getMaxQuant() {
		return maxQuant;
	}
	/**
	 * @param maxQuant the maxQuant to set
	 */
	public void setMaxQuant(int maxQuant) {
		this.maxQuant = maxQuant;
	}
	/**
	 * @return the gd
	 */
	public List<GrpAttrDS> getGd() {
		return gd;
	}
	/**
	 * @param gd the gd to set
	 */
	public void setGd(List<GrpAttrDS> gd) {
		this.gd = gd;
	}
	
}
