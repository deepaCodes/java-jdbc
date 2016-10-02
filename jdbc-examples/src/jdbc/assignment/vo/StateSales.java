package jdbc.assignment.vo;

public class StateSales {
	
	private String customer;
	private String product;
	private int max_sales;
	private int min_sales;
	private String state;
	private String date;
	
	
	/**
	 * @return the min_sales
	 */
	public int getMin_sales() {
		return min_sales;
	}
	/**
	 * @param min_sales the min_sales to set
	 */
	public void setMin_sales(int min_sales) {
		this.min_sales = min_sales;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
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
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return the max_salesny
	 */
	public int getMax_sales() {
		return max_sales;
	}
	/**
	 * @param max_salesny the max_salesny to set
	 */
	public void setMax_sales(int max_sales) {
		this.max_sales = max_sales;
	}
	

}
