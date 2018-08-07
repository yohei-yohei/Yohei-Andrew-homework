/**
 * Represents a covered space with a number and a monthly rate.
 * @author atrolph, mmuppa
 * @since 08/05/18
 */
public class CoveredSpace {
	private int mySpaceNumber;
	private double myMonthlyRate;
	
	/**
	 * Initialize the covered space parameters.
	 * @param theSpaceNumber
	 * @param theMonthlyRate
	 * @throws IllegalArgumentException if space number or monthly rate is lower than 1.
	 */
	public CoveredSpace(int theSpaceNumber, double theMonthlyRate) {
		setSpaceNumber(theSpaceNumber);
		setMonthlyRate(theMonthlyRate);
	}
	
	@Override
	public String toString() {
		return "CoveredSpace [Space number = " + mySpaceNumber + ", Monthly rate = " + myMonthlyRate  + "]";
	}

	/**
	 * Returns the name of the Staff.
	 * @return StaffName
	 */
	public int getSpaceNumber() {
		return mySpaceNumber;
	}
	
	/**
	 * Modifies the space number of the covered space.
	 * @param title
	 * @throws IllegalArgumentException if space number is less than 1.
	 */
	public void setSpaceNumber(int theSpaceNumber) {
		if (theSpaceNumber < 1)
			throw new IllegalArgumentException("Please supply a valid space number.");
		mySpaceNumber = theSpaceNumber;
	}
	
	/**
	 * Returns the space type of the covered space.
	 * @return myMonthlyRate
	 */
	public double getMonthlyRate() {
		return myMonthlyRate;
	}
	
	/**
	 * Sets the space type of the covered space.
	 * @param theMonthlyRate
	 * @throws IllegalArgumentException if monthly rate is negative.
	*/
	public void setMonthlyRate(double theMonthlyRate) {
		if (theMonthlyRate < 0)
			throw new IllegalArgumentException("Please supply a valid monthly rate");
		myMonthlyRate = theMonthlyRate;
	}
}
