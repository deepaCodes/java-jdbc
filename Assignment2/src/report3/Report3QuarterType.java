package report3;

/**
 * 
 * @author Deepa Aswathaiah
 *
 */
public class Report3QuarterType {

	private int quant;
	private int count;
	private int minQuant = Integer.MAX_VALUE;

	private int previousQCount;
	private int nextQCount;

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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void incrementCount() {
		this.count++;
	}

	/**
	 * @return the minQuant
	 */
	public int getMinQuant() {
		return minQuant;
	}

	/**
	 * @param minQuant
	 *            the minQuant to set
	 */
	public void setMinQuant(int minQuant) {
		this.minQuant = minQuant;
	}

	public int getAverage() {
		if (this.count != 0) {
			return this.quant / this.count;
		}
		return 0;
	}

	/**
	 * @return the previousQCount
	 */
	public int getPreviousQCount() {
		return previousQCount;
	}

	/**
	 * @param previousQCount
	 *            the previousQCount to set
	 */
	public void incrementPreviousQCount() {
		this.previousQCount++;
	}

	/**
	 * @return the nextQCount
	 */
	public int getNextQCount() {
		return nextQCount;
	}

	/**
	 * @param nextQCount
	 *            the nextQCount to set
	 */
	public void incrementNextQCount() {
		this.nextQCount++;

	}

}
