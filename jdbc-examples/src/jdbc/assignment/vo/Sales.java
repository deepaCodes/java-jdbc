package jdbc.assignment.vo;

/* Class consists of all the fields that need to be displayed as a result by MaxMinAvg */

public class Sales {

	private String customer;
	private String product;
	private String maxDate;
	private String minDate;
	private int maxQ;
	private int minQ;
	private String maxState;
	private String minState;

	private double average;
	private double sum;
	private int count;

	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * @param average
	 *            the average to set
	 */
	public void setAverage() {
		this.average = sum / count;
	}

	/**
	 * @return the sum
	 */
	public double getSum() {
		return sum;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount() {
		this.count++;
	}

	/**
	 * @param sum
	 *            the sum to set
	 */
	public void setSum(double sum) {
		this.sum += sum;

	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the maxDate
	 */
	public String getMaxDate() {
		return maxDate;
	}

	/**
	 * @param maxDate
	 *            the maxDate to set
	 */
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	/**
	 * @return the minDate
	 */
	public String getMinDate() {
		return minDate;
	}

	/**
	 * @param minDate
	 *            the minDate to set
	 */
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	/**
	 * @return the maxQ
	 */
	public int getMaxQ() {
		return maxQ;
	}

	/**
	 * @param maxQ
	 *            the maxQ to set
	 */
	public void setMaxQ(int maxQ) {
		this.maxQ = maxQ;
	}

	/**
	 * @return the minQ
	 */
	public int getMinQ() {
		return minQ;
	}

	/**
	 * @param minQ
	 *            the minQ to set
	 */
	public void setMinQ(int minQ) {
		this.minQ = minQ;
	}

	/**
	 * @return the maxState
	 */
	public String getMaxState() {
		return maxState;
	}

	/**
	 * @param maxState
	 *            the maxState to set
	 */
	public void setMaxState(String maxState) {
		this.maxState = maxState;
	}

	/**
	 * @return the minState
	 */
	public String getMinState() {
		return minState;
	}

	/**
	 * @param minState
	 *            the minState to set
	 */
	public void setMinState(String minState) {
		this.minState = minState;
	}
	/**
	 * @return the average
	 */

}
