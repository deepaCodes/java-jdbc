package report2;

/**
 * 
 * @author Deepa Aswathaiah
 *
 */
public class DataStructure {
	private String customer;
	private String product;
	private int q1Sum;
	private int q1Count;
	private int q2Sum;
	private int q2Count;
	private int q3Sum;
	private int q3Count;
	private int q4Sum;
	private int q4Count;

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
	 * @return the q1Sum
	 */
	public int getQ1Sum() {
		return q1Sum;
	}

	/**
	 * @param q1Sum
	 *            the q1Sum to set
	 */
	public void setQ1Sum(int q1Sum) {
		this.q1Sum = q1Sum;
	}

	/**
	 * @return the q2Sum
	 */
	public int getQ2Sum() {
		return q2Sum;
	}

	/**
	 * @param q2Sum
	 *            the q2Sum to set
	 */
	public void setQ2Sum(int q2Sum) {
		this.q2Sum = q2Sum;
	}

	/**
	 * @return the q3Sum
	 */
	public int getQ3Sum() {
		return q3Sum;
	}

	/**
	 * @param q3Sum
	 *            the q3Sum to set
	 */
	public void setQ3Sum(int q3Sum) {
		this.q3Sum = q3Sum;
	}

	/**
	 * @return the q4Sum
	 */
	public int getQ4Sum() {
		return q4Sum;
	}

	/**
	 * @param q4Sum
	 *            the q4Sum to set
	 */
	public void setQ4Sum(int q4Sum) {
		this.q4Sum = q4Sum;
	}

	/**
	 * @return the q1Count
	 */
	public int getQ1Count() {
		return q1Count;
	}

	/**
	 * @param q1Count
	 *            the q1Count to set
	 */
	public void setQ1Count() {
		this.q1Count++;
	}

	/**
	 * @return the q2Count
	 */
	public int getQ2Count() {
		return q2Count;
	}

	/**
	 * @param q2Count
	 *            the q2Count to set
	 */
	public void setQ2Count() {
		this.q2Count++;
	}

	/**
	 * @return the q3Count
	 */
	public int getQ3Count() {
		return q3Count;
	}

	/**
	 * @param q3Count
	 *            the q3Count to set
	 */
	public void setQ3Count() {
		this.q3Count++;
	}

	/**
	 * @return the q4Count
	 */
	public int getQ4Count() {
		return q4Count;
	}

	/**
	 * @param q4Count
	 *            the q4Count to set
	 */
	public void setQ4Count() {
		this.q4Count++;
	}

	public int getAvgQ1() {
		if (q1Count != 0) {
			return q1Sum / q1Count;
		} else
			return 0;
	}

	public int getAvgQ2() {
		if (q2Count != 0) {
			return q2Sum / q2Count;
		} else
			return 0;
	}

	public int getAvgQ3() {
		if (q3Count != 0) {
			return q3Sum / q3Count;
		} else
			return 0;
	}

	public int getAvgQ4() {
		if (q4Count != 0) {
			return q4Sum / q4Count;
		} else
			return 0;
	}
}
