/**
 * Represents staff with a number, telephone extension and license number.
 * @author atrolph, mmuppa
 * @since 08/05/18
 */
public class ParkingSpace {
	private int mySpaceNumber;
	private String mySpaceType, myLotName;
	
	/**
	 * Initialize the staff parameters.
	 * @param theSpaceNumber
	 * @param theSpaceType
	 * @param theLotName
	 * @throws IllegalArgumentException if license number is null or empty
	 */
	public ParkingSpace(int theSpaceNumber, String theSpaceType, String theLotName) {
		setSpaceNumber(theSpaceNumber);
		setSpaceType(theSpaceType);
		setLotName(theLotName);
	}
	
	@Override
	public String toString() {
		return "ParkingSpace [Space number = " + mySpaceNumber + ", Space type = " + mySpaceType + ", Lot name = "
		+ myLotName + "]";
	}

	/**
	 * Returns the name of the Staff.
	 * @return StaffName
	 */
	public int getSpaceNumber() {
		return mySpaceNumber;
	}
	
	/**
	 * Modifies the title of the movie.
	 * @param title
	 * @throws IllegalArgumentException if space number is null or empty.
	 */
	public void setSpaceNumber(int theSpaceNumber) {
		if (theSpaceNumber < 1)
			throw new IllegalArgumentException("Please supply a valid title.");
		mySpaceNumber = theSpaceNumber;
	}
	
	/**
	 * Returns the space type of the parking space.
	 * @return mySpaceType
	 */
	public String getSpaceType() {
		return mySpaceType;
	}
	
	/**
	 * Sets the space type of the parking space.
	 * @param theSpaceType
	 * @throws IllegalArgumentException if space type is null or empty.
	*/
	public void setSpaceType(String theSpaceType) {
		if (theSpaceType == null || theSpaceType.length() == 0)
			throw new IllegalArgumentException("Please supply a valid space type");
		mySpaceType = theSpaceType;
	}

	/**
	 * Returns the lot name of the parking space.
	 * @return myLotName
	 */
	public String getLotName() {
		return myLotName;
	}
	
	/**
	 * Modifies the lot name of the parking space.
	 * @param theLotName
	 * @throws IllegalArgumentException if lot name is null or empty.
	 */
	public void setLotName(String theLotName) {		
			if (theLotName == null || theLotName.length() == 0)
				throw new IllegalArgumentException("Please supply a valid lot name.");
			myLotName = theLotName;
	}
}
