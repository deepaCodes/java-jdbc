package report1;

/**
 * 
 * @author Deepa Aswathaiah
 *
 */
public class Report1DataType {

	private String customer;
	private String product;
	private int quant;
	private int count;
	private int otherProdQuant;
	private int otherProdCount;
	private int otherCustQuant;
	private int otherCustCount;

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
	 * @return the quant
	 */
	public int getQuant() {
		return quant;
	}

	/**
	 * @param quant
	 *            the quant to set
	 */
	public void setQuant(int quant) {
		this.quant = quant;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void incrementCount() {
		this.count = this.count + 1;
	}

	/**
	 * @return the otherProdQuant
	 */
	public int getOtherProdQuant() {
		return otherProdQuant;
	}

	/**
	 * @param otherProdQuant
	 *            the otherProdQuant to set
	 */
	public void setOtherProdQuant(int otherProdQuant) {
		this.otherProdQuant = otherProdQuant;
	}

	/**
	 * @return the otherProdCount
	 */
	public int getOtherProdCount() {
		return otherProdCount;
	}

	/**
	 * @param i
	 * @param otherProdCount
	 *            the otherProdCount to set
	 */
	public void incrementOtherProdCount(int i) {
		this.otherProdCount = this.otherProdCount + i;
	}

	public int getAverage() {
		return this.quant / this.count;
	}

	public int getOtherProdAverage() {
		return this.otherProdQuant / this.otherProdCount;
	}

	/**
	 * @return the otherCustQuant
	 */
	public int getOtherCustQuant() {
		return otherCustQuant;
	}

	/**
	 * @param otherCustQuant
	 *            the otherCustQuant to set
	 */
	public void setOtherCustQuant(int otherCustQuant) {
		this.otherCustQuant = otherCustQuant;
	}

	public void incrementOtherCustCount(int i) {
		this.otherCustCount = this.otherCustCount + i;
	}

	public int getOtherCustAvg() {
		return this.otherCustQuant / this.otherCustCount;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

}
