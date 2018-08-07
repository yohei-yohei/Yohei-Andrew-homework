/**
 * Represents an uncovered space with a number.
 * @author atrolph, mmuppa
 * @since 08/05/18
 */
public class UncoveredSpace {
	private int mySpaceNumber;
	
	/**
	 * Initialize the uncovered space parameters.
	 * @param theSpaceNumber
	 * @throws IllegalArgumentException if space number or monthly rate is lower than 1.
	 */
	public UncoveredSpace(int theSpaceNumber) {
		setSpaceNumber(theSpaceNumber);
	}
	
	@Override
	public String toString() {
		return "Uncovered [Space Number = " + mySpaceNumber + "]";
	}

	/**
	 * Returns the name of the Staff.
	 * @return StaffName
	 */
	public int getSpaceNumber() {
		return mySpaceNumber;
	}
	
	/**
	 * Modifies the space number of the uncovered space.
	 * @param title
	 * @throws IllegalArgumentException if space number is less than 1.
	 */
	public void setSpaceNumber(int theSpaceNumber) {
		if (theSpaceNumber < 1)
			throw new IllegalArgumentException("Please supply a valid space number.");
		mySpaceNumber = theSpaceNumber;
	}
}
