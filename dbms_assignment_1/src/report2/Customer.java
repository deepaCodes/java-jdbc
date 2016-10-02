package report2;

// Class consists of Customer-Product pair with StateQ array representing details for different states

public class Customer {

	//private variable declaration
	
	private String cust;
	private String prod;
	public StateQ[] statearray = new StateQ[500]; // initialize StateQ array with details specific to state
	public int pos = 0; // index used to access StateQ array
	
	
	
	//public method section starts

	/**
	 * @return the cust
	 */
	public String getCust() {
		return cust;
	}

	/**
	 * @param cust
	 *            the cust to set
	 */
	public void setCust(String cust) {
		this.cust = cust;
	}

	/**
	 * @return the prod
	 */
	public String getProd() {
		return prod;
	}

	/**
	 * @param prod
	 *            the prod to set
	 */
	public void setProd(String prod) {
		this.prod = prod;
	}

}
