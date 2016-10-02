/**
 * 
 */
package report3;

/**
 * 
 * @author Deepa Aswathaiah
 *
 */
public class Report3DataType {

	private String customer;
	private String product;

	private Report3QuarterType q1 = new Report3QuarterType();
	private Report3QuarterType q2 = new Report3QuarterType();
	private Report3QuarterType q3 = new Report3QuarterType();
	private Report3QuarterType q4 = new Report3QuarterType();

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
	 * @return the q1
	 */
	public Report3QuarterType getQ1() {
		return q1;
	}

	/**
	 * @param q1
	 *            the q1 to set
	 */
	public void setQ1(Report3QuarterType q1) {
		this.q1 = q1;
	}

	/**
	 * @return the q2
	 */
	public Report3QuarterType getQ2() {
		return q2;
	}

	/**
	 * @param q2
	 *            the q2 to set
	 */
	public void setQ2(Report3QuarterType q2) {
		this.q2 = q2;
	}

	/**
	 * @return the q3
	 */
	public Report3QuarterType getQ3() {
		return q3;
	}

	/**
	 * @param q3
	 *            the q3 to set
	 */
	public void setQ3(Report3QuarterType q3) {
		this.q3 = q3;
	}

	/**
	 * @return the q4
	 */
	public Report3QuarterType getQ4() {
		return q4;
	}

	/**
	 * @param q4
	 *            the q4 to set
	 */
	public void setQ4(Report3QuarterType q4) {
		this.q4 = q4;
	}

}
