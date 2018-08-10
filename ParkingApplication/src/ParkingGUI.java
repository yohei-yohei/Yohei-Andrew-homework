import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A user interface to view the lots, add a new lot and to update an existing lot.
 * The myStaffList is a table with all the movie information in it. The TableModelListener listens to
 * any changes to the cells to modify the values to reach lot.
 * @author atrolph, mmuppa
 *
 */
public class ParkingGUI extends JFrame implements ActionListener, TableModelListener {
	
	private static final long serialVersionUID = 1779520078061383929L;
	private JButton listLotsBtn, searchLotsBtn, addLotsButton;
	private JPanel lotButtons, staffListPanel;
	private ParkingDB db;
	private List<Staff> myStaffList;
	
	private String[] staffColumns = {
			"Staff Number",
            "Telephone Extension",
            "License Number",
    };
	
	private Object[][] staffData;

	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlSearch;
	private JLabel lblLotName;;
	private JTextField lotNameField;
	private JButton searchLotsPanelBtn;
	
	private JPanel pnlAdd;
	private JLabel[] staffLabel = new JLabel[3];
	private JTextField[] staffField = new JTextField[3];
	private JButton addLotsPanelBtn;	
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public ParkingGUI() {
		super("Parking");		
		db = new ParkingDB();
		try	{
			myStaffList = db.getStaff();
			staffData = new Object[myStaffList.size()][staffColumns.length];
			for (int i = 0; i < myStaffList.size(); i++) {
				staffData[i][0] = myStaffList.get(i).getStaffNumber();
				staffData[i][1] = myStaffList.get(i).getTelephoneExt();
				staffData[i][2] = myStaffList.get(i).getVehicleLicenseNumber();			
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Error: " + e.getMessage());
			return;
		}
		createComponents();
		setVisible(true);
		setSize(500, 500);
	}
	
	
    
	/**
	 * Creates panels for Movie myStaffList, search, add and adds the corresponding 
	 * components to each panel.
	 */
	private void createComponents()	{
		lotButtons = new JPanel();
		listLotsBtn = new JButton("Lot List");
		listLotsBtn.addActionListener(this);
		
		searchLotsBtn = new JButton("Lot Search");
		searchLotsBtn.addActionListener(this);
		
		addLotsButton = new JButton("Add Lot");
		addLotsButton.addActionListener(this);
		
		lotButtons.add(listLotsBtn);
		lotButtons.add(searchLotsBtn);
		lotButtons.add(addLotsButton);
		add(lotButtons, BorderLayout.NORTH);
		
		//List Panel
		staffListPanel = new JPanel();
		table = new JTable(staffData, staffColumns);
		scrollPane = new JScrollPane(table);
		staffListPanel.add(scrollPane);
		table.getModel().addTableModelListener(this);
		
		//Search Panel
		pnlSearch = new JPanel();
		lblLotName = new JLabel("Enter Lot Name: ");
		lotNameField = new JTextField(25);
		searchLotsPanelBtn = new JButton("Search");
		searchLotsPanelBtn.addActionListener(this);
		pnlSearch.add(lblLotName);
		pnlSearch.add(lotNameField);
		pnlSearch.add(searchLotsPanelBtn);
		
		//Add Panel
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(6, 0));
		String labelNames[] = {"Enter Staff Number: ", "Enter Telephone Extension: ", "Enter License Number: "};
		for (int i = 0; i < labelNames.length; i++) {
			JPanel panel = new JPanel();
			staffLabel[i] = new JLabel(labelNames[i]);
			staffField[i] = new JTextField(25);
			panel.add(staffLabel[i]);
			panel.add(staffField[i]);
			pnlAdd.add(panel);
		}
		JPanel panel = new JPanel();
		addLotsPanelBtn = new JButton("Add");
		addLotsPanelBtn.addActionListener(this);
		panel.add(addLotsPanelBtn);
		pnlAdd.add(panel);
		
		//Update panel
		add(staffListPanel, BorderLayout.CENTER);		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParkingGUI parkingGUI = new ParkingGUI();
		parkingGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Event handling to change the panels when different tabs are clicked,
	 * add and search buttons are clicked on the corresponding add and search panels.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == listLotsBtn) {
			try {
				myStaffList = db.getStaff();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			staffData = new Object[myStaffList.size()][staffColumns.length];
			for (int i = 0; i < myStaffList.size(); i++) {
				staffData[i][0] = myStaffList.get(i).getStaffNumber();
				staffData[i][1] = myStaffList.get(i).getTelephoneExt();
				staffData[i][2] = myStaffList.get(i).getVehicleLicenseNumber();
			}
			staffListPanel.removeAll();
			table = new JTable(staffData, staffColumns);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			staffListPanel.add(scrollPane);
			staffListPanel.revalidate();
			this.repaint();
			
		} else if (e.getSource() == searchLotsBtn) {
			staffListPanel.removeAll();
			staffListPanel.add(pnlSearch);
			staffListPanel.revalidate();
			this.repaint();
		} else if (e.getSource() == addLotsButton) {
			staffListPanel.removeAll();
			staffListPanel.add(pnlAdd);
			staffListPanel.revalidate();
			this.repaint();
			
		} else if (e.getSource() == searchLotsPanelBtn) {
			int staffNumber = Integer.parseInt(staffField[0].getText());
			if (staffNumber > 0) {
				try {
					myStaffList = db.getStaff(staffNumber);
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(this, exception.getMessage());
					return;
				}
				staffData = new Object[myStaffList.size()][staffColumns.length];
				for (int i = 0; i < myStaffList.size(); i++) {
					staffData[i][0] = myStaffList.get(i).getStaffNumber();
					staffData[i][1] = myStaffList.get(i).getTelephoneExt();
					staffData[i][2] = myStaffList.get(i).getVehicleLicenseNumber();	
				}
				staffListPanel.removeAll();
				table = new JTable(staffData, staffColumns);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				staffListPanel.add(scrollPane);
				staffListPanel.revalidate();
				this.repaint();
			}
		} else if (e.getSource() == addLotsPanelBtn) {
			Staff staff = new Staff(Integer.parseInt(staffField[0].getText()), 
					Integer.parseInt(staffField[1].getText()), staffField[2].getText());
			try {
				db.addStaff(staff);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i = 0; i < staffField.length; i++) {
				staffField[i].setText("");
			}
		}
		
	}

	/**
	 * Event handling for any cell being changed in the table.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        try {
        	 db.updateStaff(row, columnName, data);;
		}
		catch(Exception exception) {
			JOptionPane.showMessageDialog(this, exception.getMessage());
			return;
		}		
	}
}
