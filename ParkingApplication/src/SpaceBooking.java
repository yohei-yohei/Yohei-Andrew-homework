/**
 * Represents a space booking with a booking id, space number, staff number, visitor license and date of visit.
 * @author atrolph, mmuppa
 * @since 08/05/18
 */
public class SpaceBooking {
	private int myBookingId, mySpaceNumber, myStaffNumber;
	private String myVisitorLicense, myDateOfVisit;
	
	/**
	 * Initialize the lot parameters.
	 * @param theBookingId
	 * @param theSpaceNumber
	 * @param theStaffNumber
	 * @param theVisitorLicense
	 * @param theDateOfVisit
	 * @throws IllegalArgumentException if parameters are null or empty
	 */
	public SpaceBooking(int theBookingId, int theSpaceNumber, int theStaffNumber, String theVisitorLicense, String theDateOfVisit) {
		setBookingId(theBookingId);
		setSpaceNumber(theSpaceNumber);
		setStaffNumber(theStaffNumber);
		setVisitorLicense(theVisitorLicense);
		setDateOfVisit(theDateOfVisit);
	}
	
	@Override
	public String toString() {
		return "SpaceBooking [SpaceBooking Name = " + myBookingId + ", SpaceNumber = " + mySpaceNumber + ", StaffNumber = "
		+ myStaffNumber + ", VisitorLicense = " + myVisitorLicense + "]";
	}

	/**
	 * Returns the booking id.
	 * @return myBookingId
	 */
	public int getBookingId() {
		return myBookingId;
	}
	
	/**
	 * Modifies booking id.
	 * @param theBookingId
	 * @throws IllegalArgumentException if booking id is less than 1.
	 */
	public void setBookingId(int theBookingId) {
		if (theBookingId < 1)
			throw new IllegalArgumentException("Please supply a valid booking id.");
		myBookingId = theBookingId;
	}
	
	/**
	 * Returns the space number of the visitor.
	 * @return mySpaceNumber
	 */
	public int getSpaceNumber() {
		return mySpaceNumber;
	}
	
	/**
	 * Sets the mySpaceNumber of the visitor.
	 * @param theSpaceNumber
	 * @throws IllegalArgumentException if space number is les than 1.
	*/
	public void setSpaceNumber(int theSpaceNumber) {
		if (theSpaceNumber < 1)
			throw new IllegalArgumentException("Please supply a valid space number.");
		mySpaceNumber = theSpaceNumber;
	}

	/**
	 * Returns the staff number linked with the visitor.
	 * @return myStaffNumber
	 */
	public int getStaffNumber() {
		return myStaffNumber;
	}
	
	/**
	 * Modifies the staff number linked with the visitor.
	 * @param theStaffNumber
	 * @throws IllegalArgumentException if staff number is less than 1.
	 */
	public void setStaffNumber(int theStaffNumber) {
		if (myStaffNumber < 1)
			throw new IllegalArgumentException("StaffNumber cannot be less than 1");
		myStaffNumber = theStaffNumber;
	}
	
	
	/**
	 * Returns the visitor license.
	 * @return myVisitorLicense
	 */
	public String getVisitorLicense() {
		return myVisitorLicense;
	}
	
	/**
	 * Sets the visitor license.
	 * @param theVisitorLicense
	 * @throws IllegalArgumentException if visitor license is null, empty or more than 7 characters.
	 */
	public void setVisitorLicense(String theVisitorLicense) {
		if (theVisitorLicense == null || theVisitorLicense.length() == 0 || theVisitorLicense.length() > 7)
			throw new IllegalArgumentException("Please supply a valid visitor license.");	
		myVisitorLicense = theVisitorLicense;
	}
	
	/**
	 * Sets the date of visit.
	 * @return myDateOfVisit
	 */
	public String getDateOfVisit() {
		return myDateOfVisit;
	}
	
	/**
	 * Sets the date of visit.
	 * @param theDateOfVisit
	 * @throws IllegalArgumentException if visitor license is null or empty.
	 */
	public void setDateOfVisit(String theDateOfVisit) {
		if (theDateOfVisit == null || theDateOfVisit.length() == 0)
			throw new IllegalArgumentException("Please supply a valid date of visit.");	
		myDateOfVisit = theDateOfVisit;
	}
}
