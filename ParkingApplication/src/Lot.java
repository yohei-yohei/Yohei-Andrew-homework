/**
 * Represents a lot with a name, location, capacity and floors.
 * @author atrolph, mmuppa
 * @since 08/05/18
 */
public class Lot {
	private String myLotName, myLocation;
	private int myCapacity, myFloors;
	
	/**
	 * Initialize the lot parameters.
	 * @param theLotName
	 * @param theLocation
	 * @param theCapacity
	 * @param theFloors
	 * @throws IllegalArgumentException if name or location are null or empty
	 */
	public Lot(String theLotName, String theLocation, int theCapacity, int theFloors) {
		setLotName(theLotName);
		setLocation(theLocation);
		setCapacity(theCapacity);
		setFloors(theFloors);
	}
	
	@Override
	public String toString() {
		return "Lot [Lot Name = " + myLotName + ", Location = " + myLocation + ", Capacity = "
		+ myCapacity + ", Floors = " + myFloors + "]";
	}

	/**
	 * Returns the name of the lot.
	 * @return myLotName
	 */
	public String getLotName() {
		return myLotName;
	}
	
	/**
	 * Modifies the name of the lot.
	 * @param title
	 * @throws IllegalArgumentException if lot name is null or empty.
	 */
	public void setLotName(String theLotName) {
		if (theLotName == null || theLotName.length() == 0) {
			throw new IllegalArgumentException("Please supply a valid lot name.");
		}
		myLotName = theLotName;
	}
	
	/**
	 * Returns the location of the lot.
	 * @return myLocation
	 */
	public String getLocation() {
		return myLocation;
	}
	
	/**
	 * Sets the location of the lot.
	 * @param theLocation
	 * @throws IllegalArgumentException if myLocation is null or empty.
	*/
	public void setLocation(String theLocation) {
		if (theLocation == null || theLocation.length() == 0) {
			throw new IllegalArgumentException("Please supply a valid location.");
		}
		myLocation = theLocation;
	}

	/**
	 * Returns the capacity of the lot.
	 * @return myCapacity
	 */
	public int getCapacity() {
		return myCapacity;
	}
	
	/**
	 * Modifies the capacity of the lot.
	 * @param theCapacity
	 * @throws IllegalArgumentException if myCapacity is less than 1.
	 */
	public void setCapacity(int theCapacity) {
		if (theCapacity < 1) {
			throw new IllegalArgumentException("Capacity cannot be less than 1");
		}
		myCapacity = theCapacity;
	}
	
	
	/**
	 * Returns the number of myFloors.
	 * @return myFloors
	 */
	public int getFloors() {
		return myFloors;
	}
	
	/**
	 * Sets the number of myFloors
	 * @param genre
	 * @throws IllegalArgumentException if floors is less than 1.
	 */
	public void setFloors(int theFloors) {
		if (theFloors < 1) {
			throw new IllegalArgumentException("Number of floors cannot be less than 1.");	
		}
		myFloors = theFloors;
	}
	
}
