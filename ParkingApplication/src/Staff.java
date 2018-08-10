/**
 * Represents staff with a number, telephone extension and license number.
 * @author atrolph, mmuppa
 * @since 08/05/18
 */
public class Staff {
	private int myStaffNumber, myTelephoneExt;
	private String myVehicleLicenseNumber;
	
	/**
	 * Initialize the staff parameters.
	 * @param theStaffNumber
	 * @param theTelephoneExt
	 * @param theVehicleLicenseNumber
	 * @throws IllegalArgumentException if license number is null or empty
	 */
	public Staff(int theStaffNumber, int theTelephoneExt, String theVehicleLicenseNumber) {
		setStaffNumber(theStaffNumber);
		setTelephoneExt(theTelephoneExt);
		setVehicleLicenseNumber(theVehicleLicenseNumber);
	}
	
	@Override
	public String toString() {
		return "Staff [Staff Number = " + myStaffNumber + ", Telephone Extension = " + myTelephoneExt + ", Vehicle License Number = "
		+ myVehicleLicenseNumber + "]";
	}

	/**
	 * Returns the name of the Staff.
	 * @return StaffName
	 */
	public int getStaffNumber() {
		return myStaffNumber;
	}
	
	/**
	 * Modifies the title of the movie.
	 * @param title
	 * @throws IllegalArgumentException if Staff name is null or empty.
	 */
	public void setStaffNumber(int theStaffNumber) {
		if (theStaffNumber < 1)
			throw new IllegalArgumentException("Please supply a valid title.");
		myStaffNumber = theStaffNumber;
	}
	
	/**
	 * Returns the telephone extension of the staff.
	 * @return myTelephoneExt
	 */
	public int getTelephoneExt() {
		return myTelephoneExt;
	}
	
	/**
	 * Sets the myTelephoneExt of the staff.
	 * @param theTelephoneExt
	 * @throws IllegalArgumentException if telephone extension is less than 0.
	*/
	public void setTelephoneExt(int theTelephoneExt) {
		if (theTelephoneExt < 0)
			throw new IllegalArgumentException("Please supply a valid telephone extension.");
		myTelephoneExt = theTelephoneExt;
	}

	/**
	 * Returns the myVehicleLicenseNumber of the Staff.
	 * @return myVehicleLicenseNumber
	 */
	public String getVehicleLicenseNumber() {
		return myVehicleLicenseNumber;
	}
	
	/**
	 * Modifies the myVehicleLicenseNumber of the Staff.
	 * @param theVehicleLicenseNumber
	 * @throws IllegalArgumentException if myVehicleLicenseNumber is null, empty or more than 7 chars long.
	 */
	public void setVehicleLicenseNumber(String theVehicleLicenseNumber) {		
			if (theVehicleLicenseNumber == null || theVehicleLicenseNumber.length() == 0 || theVehicleLicenseNumber.length() > 7)
				throw new IllegalArgumentException("Please supply a valid license number.");
			myVehicleLicenseNumber = theVehicleLicenseNumber;
	}
}
