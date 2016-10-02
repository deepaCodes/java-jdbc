package assignment2;

// This class contains all the fields related to any state

public class StateQ {

	private String state;
	private int max;
	private int min;
	private String date_max;
	private String date_min;
	private int year;

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the date_max
	 */
	public String getDate_max() {
		return date_max;
	}

	/**
	 * @param date_max
	 *            the date_max to set
	 */
	public void setDate_max(String date_max) {
		this.date_max = date_max;
	}

	/**
	 * @return the date_min
	 */
	public String getDate_min() {
		return date_min;
	}

	/**
	 * @param date_min
	 *            the date_min to set
	 */
	public void setDate_min(String date_min) {
		this.date_min = date_min;
	}

}
