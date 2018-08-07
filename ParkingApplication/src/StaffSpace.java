/**
 * Represents a staff space with a staff number and a space number.
 * @author atrolph, mmuppa
 * @since 08/05/18
 */
public class StaffSpace {
	private int myStaffNumber;
	private int mySpaceNumber;
	
	/**
	 * Initialize the staff space parameters.
	 * @param theStaffNumber
	 * @param theSpaceNumber
	 * @throws IllegalArgumentException if staff number or space number is lower than 1.
	 */
	public StaffSpace(int theStaffNumber, int theSpaceNumber) {
		setStaffNumber(theStaffNumber);
		setSpaceNumber(theSpaceNumber);
	}
	
	@Override
	public String toString() {
		return "StaffSpace [Staff number = " + myStaffNumber + ", Space number = " + mySpaceNumber  + "]";
	}

	/**
	 * Returns the name of the Staff.
	 * @return StaffName
	 */
	public int getStaffNumber() {
		return myStaffNumber;
	}
	
	/**
	 * Modifies the space number of the staff space.
	 * @param title
	 * @throws IllegalArgumentException if staff number is less than 1.
	 */
	public void setStaffNumber(int theStaffNumber) {
		if (theStaffNumber < 1)
			throw new IllegalArgumentException("Please supply a valid staf number.");
		myStaffNumber = theStaffNumber;
	}
	
	/**
	 * Returns the space type of the staff space.
	 * @return mySpaceNumber
	 */
	public int getSpaceNumber() {
		return mySpaceNumber;
	}
	
	/**
	 * Sets the space type of the staff space.
	 * @param theSpaceNumber
	 * @throws IllegalArgumentException if space number is less than 1.
	*/
	public void setSpaceNumber(int theSpaceNumber) {
		if (theSpaceNumber < 1)
			throw new IllegalArgumentException("Please supply a valid space number");
		mySpaceNumber = theSpaceNumber;
	}
}
