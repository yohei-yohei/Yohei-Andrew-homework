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
//	private List<Staff> myStaffList;
//	private List<ParkingSpace> myParkingSpaceList;
//	private List<CoveredSpace> myCoveredSpaceList;
//	private List<UncoveredSpace> myUncoveredSpaceList;
//	private List<StaffSpace> myStaffSpaceList;
//	private List<SpaceBooking> mySpaceBookingList;	

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
			throw new Exception("Unable to retrieve list of lots: " + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return myLotList;
	}

	/**
	 * Filters the movie list to find the given title. Returns a list with the
	 * movie objects that match the title provided.
	 * @param title
	 * @return list of movies that contain the title.
	 */
	public List<Lot> getLots(String lotName) throws Exception {
		List<Lot> filterList = new ArrayList<Lot>();
		try {
			myLotList = getLots();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve lots: " + e.getMessage());
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
	 * Modifies the movie information corresponding to the index in the list.
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
