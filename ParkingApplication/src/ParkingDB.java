import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that consists of the database operations to insert and update the Movie information.
 * @author atrolph, mmuppa
 *
 */

public class ParkingDB {
	private static String userName = "atrolph";
	private static String password = "FriWaHig";
	private static String serverName = "cssgate.insttech.washington.edu"; 
	private static Connection sConnection;
	
	private List<Lot> myLotList;
	private List<Staff> myStaffList;
	private List<ParkingSpace> myParkingSpaceList;
	private List<CoveredSpace> myCoveredSpaceList;
	private List<UncoveredSpace> myUncoveredSpaceList;
	private List<StaffSpace> myStaffSpaceList;
	private List<SpaceBooking> mySpaceBookingList;

	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		sConnection = DriverManager
				.getConnection("jdbc:mysql://" + serverName + "/" + userName + "?user=" + userName + "&password=" + password);
		//Uncomment For Debugging - System.out.println("Connected to database");
	}
	
	public List<SpaceBooking> getSpaceBooking() throws Exception{
		if(sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String  query = "select bookingId, spaceNumber, staffNumber, visitorLicense, dateOfVisit" + "from SPACE_BOOKING";
		mySpaceBookingList = new ArrayList<SpaceBooking>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int bookingId = rs.getInt("bookingId");
				int spaceNumber = rs.getInt("spaceNumber");
				int staffNumber = rs.getInt("staffNumber");
				String visitorLincense = rs.getString("visitorLicense");
				String dateofvisit = rs.getString("dateOfVisit");
				SpaceBooking spaceBooking = new SpaceBooking(bookingId, spaceNumber, staffNumber, visitorLincense, dateofvisit);
				mySpaceBookingList.add(spaceBooking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Space booking: " + e.getMessage());
		}finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return mySpaceBookingList;
	}
	
	public List<SpaceBooking> getSpaceBooking(int bookingId) throws Exception {
		List<SpaceBooking> filterList = new ArrayList<SpaceBooking>();
		try {
			mySpaceBookingList = getSpaceBooking();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve Space booking: " + e.getMessage());
		}
		for (SpaceBooking spaceBooking : mySpaceBookingList) {
			if (spaceBooking.getBookingId() == bookingId) {
				filterList.add(spaceBooking);
			}
		}
		return filterList;
	}
	
	public void addSpaceBooking(SpaceBooking spacebooking) throws Exception {
		String sql = "insert into SPACE_BOOKING values " + "(?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, spacebooking.getBookingId());
			preparedStatement.setInt(2, spacebooking.getSpaceNumber());
			preparedStatement.setInt(3, spacebooking.getStaffNumber());
			preparedStatement.setString(4, spacebooking.getVisitorLicense());
			preparedStatement.setString(5, spacebooking.getDateOfVisit());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Space Booking: " + e.getMessage());
		} 
	}
	
	public void updateSpaceBooking(int row, String columnName, Object data) throws Exception {		
		SpaceBooking spacebooking = mySpaceBookingList.get(row);
		int bookingId = spacebooking.getBookingId();
		
		String sql = "update SPACE_BOOKING set " + columnName + " = ?  where bookingId = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String) {
				preparedStatement.setString(1, (String) data);
			} else if (data instanceof Integer) {
				preparedStatement.setInt(1, (Integer) data);
			}
			preparedStatement.setInt(2, bookingId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Space Booking: " + e.getMessage());
		} 		
	}
	
	
	public List<StaffSpace> getStaffSpace() throws Exception{
		if(sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String  query = "select staffNumber, spaceNumber" + "from STAFF_SPACE";
		myStaffSpaceList = new ArrayList<StaffSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int staffNumber = rs.getInt("staffNumber");
				int spaceNumber = rs.getInt("spaceNumber");
				StaffSpace staffspace = new StaffSpace(staffNumber, spaceNumber);
				myStaffSpaceList.add(staffspace);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Staff Space: " + e.getMessage());
		}finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return myStaffSpaceList;
	}
	
	public List<StaffSpace> getStaffSpace(int staffNumber) throws Exception {
		List<StaffSpace> filterList = new ArrayList<StaffSpace>();
		try {
			myStaffList = getStaff();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve Staff Space: " + e.getMessage());
		}
		for (StaffSpace staffSpace : myStaffSpaceList) {
			if (staffSpace.getStaffNumber() == staffNumber) {
				filterList.add(staffSpace);
			}
		}
		return filterList;
	}
	
	public void addStaffSpace(StaffSpace staffspace) throws Exception {
		String sql = "insert into STAFF_SPACE values " + "(?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, staffspace.getStaffNumber());
			preparedStatement.setInt(2, staffspace.getSpaceNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Staff Space: " + e.getMessage());
		} 
	}
	
	public void updateStaffSpace(int row, String columnName, Object data) throws Exception {		
		StaffSpace parkingspace = myStaffSpaceList.get(row);
		int staffnumber = parkingspace.getStaffNumber();
		
		String sql = "update STAFF_SPACE set " + columnName + " = ?  where staffNumber = ? and spaceNumber = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String) {
				preparedStatement.setString(1, (String) data);
			} else if (data instanceof Integer) {
				preparedStatement.setInt(1, (Integer) data);
			}
			preparedStatement.setInt(2, staffnumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Staff Space: " + e.getMessage());
		} 		
	}
	
	
	public List<UncoveredSpace> getUncoveredSpace() throws Exception {
		if(sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String  query = "select spaceNumber" + "from UNCOVERED_SPACE";
		myUncoveredSpaceList = new ArrayList<UncoveredSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int spaceNumber = rs.getInt("spaceNumber");
				UncoveredSpace uncoveredspace = new UncoveredSpace(spaceNumber);
				myUncoveredSpaceList.add(uncoveredspace);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Uncovered Space: " + e.getMessage());
		}finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return myUncoveredSpaceList;
	}
	
	public List<UncoveredSpace> getUncoveredSpace(int spaceNumber) throws Exception {
		List<UncoveredSpace> filterList = new ArrayList<UncoveredSpace>();
		try {
			myUncoveredSpaceList = getUncoveredSpace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve Uncovered Space: " + e.getMessage());
		}
		for (UncoveredSpace uncoveredSpace : myUncoveredSpaceList) {
			if (uncoveredSpace.getSpaceNumber() == spaceNumber) {
				filterList.add(uncoveredSpace);
			}
		}
		return filterList;
	}
	
	public void addUnCoveredSpace(UncoveredSpace coveredspace) throws Exception {
		String sql = "insert into UNCOVERED_SPACE values " + "(?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, coveredspace.getSpaceNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Uncovered Space: " + e.getMessage());
		} 
	}
	
	public void updateUnCoveredSpace(int row, String columnName, Object data) throws Exception {		
		UncoveredSpace parkingspace = myUncoveredSpaceList.get(row);
		int spaceNumber = parkingspace.getSpaceNumber();
		
		String sql = "update COVERED_SPACE set " + columnName + " = ?  where spaceNumber = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String) {
				preparedStatement.setString(1, (String) data);
			} else if (data instanceof Integer) {
				preparedStatement.setInt(1, (Integer) data);
			}
			preparedStatement.setInt(2, spaceNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Uncovered Space: " + e.getMessage());
		} 		
	}
	
	public List<CoveredSpace> getCoveredSpace() throws Exception{
		if(sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select spaceNumber, monthlyRate" + "from COVERED_SPACE";
		myCoveredSpaceList = new ArrayList<CoveredSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int spaceNumber = rs.getInt("spaceNumber");
				double monthlyRate = rs.getDouble("monthlyRate");
				CoveredSpace coveredspace = new CoveredSpace(spaceNumber, monthlyRate);
				myCoveredSpaceList.add(coveredspace);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Covered Space: " + e.getMessage());
		}finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return myCoveredSpaceList;
	}
	
	public List<CoveredSpace> getCoveredSpace(int spaceNumber) throws Exception {
		List<CoveredSpace> filterList = new ArrayList<CoveredSpace>();
		try {
			myCoveredSpaceList = getCoveredSpace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve Covered Space: " + e.getMessage());
		}
		for (CoveredSpace coveredSpace : myCoveredSpaceList) {
			if (coveredSpace.getSpaceNumber() == spaceNumber) {
				filterList.add(coveredSpace);
			}
		}
		return filterList;
	}
	
	public void addCoveredSpace(CoveredSpace coveredspace) throws Exception {
		String sql = "insert into COVERED_SPACE values " + "(?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, coveredspace.getSpaceNumber());
			preparedStatement.setDouble(2, coveredspace.getMonthlyRate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Covered Space: " + e.getMessage());
		} 
	}
	
	public void updateCoveredSpace(int row, String columnName, Object data) throws Exception {		
		CoveredSpace parkingspace = myCoveredSpaceList.get(row);
		int spacenumber = parkingspace.getSpaceNumber();
		
		String sql = "update COVERED_SPACE set " + columnName + " = ?  where spaceNumber = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String) {
				preparedStatement.setString(1, (String) data);
			} else if (data instanceof Integer) {
				preparedStatement.setInt(1, (Integer) data);
			}
			preparedStatement.setInt(2, spacenumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Covered Space: " + e.getMessage());
		} 		
	}
	
	
	public List<ParkingSpace> getParkingSpace() throws Exception{
		if(sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String  query = "select spaceNumber, spaceType, lotName" + "from PARKING_SPACE";
		myParkingSpaceList = new ArrayList<ParkingSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int spaceNumber = rs.getInt("spaceNumber");
				String spaceType = rs.getString("spaceType");
				String lotname = rs.getString("lotName");
				ParkingSpace parkingspace = new ParkingSpace(spaceNumber, spaceType, lotname);
				myParkingSpaceList.add(parkingspace);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Parking Spaces: " + e.getMessage());
		}finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return myParkingSpaceList;
	}
	public List<ParkingSpace> getParkingSpace(int spaceNumber) throws Exception {
		List<ParkingSpace> filterList = new ArrayList<ParkingSpace>();
		try {
			myParkingSpaceList = getParkingSpace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve parking space: " + e.getMessage());
		}
		for (ParkingSpace parkingSpace : myParkingSpaceList) {
			if (parkingSpace.getSpaceNumber() == spaceNumber) {
				filterList.add(parkingSpace);
			}
		}
		return filterList;
	}
	
	public void addParkingSpace(ParkingSpace parkingspace) throws Exception {
		String sql = "insert into PARKING_SPACE values " + "(?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, parkingspace.getSpaceNumber());
			preparedStatement.setString(2, parkingspace.getSpaceType());
			preparedStatement.setString(3, parkingspace.getLotName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Parking Space: " + e.getMessage());
		} 
	}
	
	public void updateParkingSpace(int row, String columnName, Object data) throws Exception {		
		ParkingSpace parkingspace = myParkingSpaceList.get(row);
		int spacenumber = parkingspace.getSpaceNumber();
		
		String sql = "update PARKING_SPACE set " + columnName + " = ?  where spaceNumber = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String) {
				preparedStatement.setString(1, (String) data);
			} else if (data instanceof Integer) {
				preparedStatement.setInt(1, (Integer) data);
			}
			preparedStatement.setInt(2, spacenumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add parking space: " + e.getMessage());
		} 		
	}
	
	public List<Staff> getStaff() throws Exception{
		if(sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String  query = "select staffNumber, telephoneExt, vehicleLicenseNumber " + "from STAFF";
		myStaffList = new ArrayList<Staff>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int staffNumber = rs.getInt("staffNumber");
				int telephoneExt = rs.getInt("telephoneExt");
				String vehicleLicenseNumber = rs.getString("vehicleLicenseNumber");
				Staff staff = new Staff(staffNumber, telephoneExt, vehicleLicenseNumber);
				myStaffList.add(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Staff: " + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return myStaffList;
	}
	public List<Staff> getStaff(int staffNumber) throws Exception {
		List<Staff> filterList = new ArrayList<Staff>();
		try {
			myStaffList = getStaff();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve staff: " + e.getMessage());
		}
		for (Staff staff : myStaffList) {
			if (staff.getStaffNumber() == staffNumber) {
				filterList.add(staff);
			}
		}
		return filterList;
	}
	
	public void addStaff(Staff staff) throws Exception {
		String sql = "insert into STAFF values " + "(?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getStaffNumber());
			preparedStatement.setInt(2, staff.getTelephoneExt());
			preparedStatement.setString(3, staff.getVehicleLicenseNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Staff: " + e.getMessage());
		} 
	}
	
	public void updateStaff(int row, String columnName, Object data) throws Exception {		
		Staff staff = myStaffList.get(row);
		int staffNumber = staff.getStaffNumber();
		
		String sql = "update STAFF set " + columnName + " = ?  where staffNumber = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String) {
				preparedStatement.setString(1, (String) data);
			} else if (data instanceof Integer) {
				preparedStatement.setInt(1, (Integer) data);
			}
			preparedStatement.setInt(2, staffNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Staff: " + e.getMessage());
		} 		
	}
	
	/**
	 * Returns a list of lot objects from the database.
	 * @return list of lots
	 * @throws Exception 
	 */
	public List<Lot> getLots() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select lotName, location, capacity, floors "
				+ "from LOT ";

		myLotList = new ArrayList<Lot>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String lotName = rs.getString("lotName");
				String location = rs.getString("location");
				int capacity = rs.getInt("capacity");
				int floors = rs.getInt("floors");
				Lot lot = new Lot(lotName, location, capacity, floors);
				myLotList.add(lot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Lots: " + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return myLotList;
	}

	/**
	 * Filters the lot list to find the given title. Returns a list with the
	 * movie objects that match the title provided.
	 * @param title
	 * @return list of lots that contain the lot name.
	 */
	public List<Lot> getLots(String lotName) throws Exception {
		List<Lot> filterList = new ArrayList<Lot>();
		try {
			myLotList = getLots();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve Lots: " + e.getMessage());
		}
		for (Lot lot : myLotList) {
			if (lot.getLotName().toLowerCase().contains(lotName.toLowerCase())) {
				filterList.add(lot);
			}
		}
		return filterList;
	}

	/**
	 * Adds a new lot to the table.
	 * @param lot 
	 * @throws Exception 
	 */
	public void addLot(Lot lot) throws Exception {
		String sql = "insert into LOT values " + "(?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setString(1, lot.getLotName());
			preparedStatement.setString(2, lot.getLocation());
			preparedStatement.setInt(3, lot.getCapacity());
			preparedStatement.setInt(4, lot.getFloors());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Lot: " + e.getMessage());
		} 
	}

	/**
	 * Modifies the lot information corresponding to the index in the list.
	 * @param row index of the element in the list
	 * @param columnName attribute to modify
	 * @param data value to supply
	 * @throws Exception 
	 */
	public void updateLot(int row, String columnName, Object data) throws Exception {		
		Lot lot = myLotList.get(row);
		String lotName = lot.getLotName();
		
		String sql = "update LOT set " + columnName + " = ?  where lotName = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String) {
				preparedStatement.setString(1, (String) data);
			} else if (data instanceof Integer) {
				preparedStatement.setInt(1, (Integer) data);
			}
			preparedStatement.setString(2, lotName);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Lot: " + e.getMessage());
		} 		
	}
}