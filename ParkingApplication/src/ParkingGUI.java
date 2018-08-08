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
 * The myLotList is a table with all the movie information in it. The TableModelListener listens to
 * any changes to the cells to modify the values to reach lot.
 * @author atrolph, mmuppa
 *
 */
public class ParkingGUI extends JFrame implements ActionListener, TableModelListener {
	
	private static final long serialVersionUID = 1779520078061383929L;
	private JButton listLotsBtn, searchLotsBtn, addLotsButton;
	private JPanel lotButtons, lotPanelList;
	private ParkingDB db;
	private List<Lot> myLotList;
	private String[] lotColumns = {
			"Lot Name",
            "Location",
            "Capacity",
            "Floors"
    };
	
	private Object[][] data;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlSearch;
	private JLabel lblLotName;;
	private JTextField lotNameField;
	private JButton searchLotsPanelBtn;
	
	private JPanel pnlAdd;
	private JLabel[] lotLabel = new JLabel[4];
	private JTextField[] lotField = new JTextField[4];
	private JButton addLotsPanelBtn;	
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public ParkingGUI() {
		super("Parking");		
		db = new ParkingDB();
		try	{
			myLotList = db.getLots();		
			data = new Object[myLotList.size()][lotColumns.length];
			for (int i = 0; i < myLotList.size(); i++) {
				data[i][0] = myLotList.get(i).getLotName();
				data[i][1] = myLotList.get(i).getLocation();
				data[i][2] = myLotList.get(i).getCapacity();
				data[i][3] = myLotList.get(i).getFloors();				
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
	 * Creates panels for Movie myLotList, search, add and adds the corresponding 
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
		lotPanelList = new JPanel();
		table = new JTable(data, lotColumns);
		scrollPane = new JScrollPane(table);
		lotPanelList.add(scrollPane);
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
		String labelNames[] = {"Enter Lot Name: ", "Enter Location: ", "Enter Capacity: ", "Enter Floors: "};
		for (int i = 0; i < labelNames.length; i++) {
			JPanel panel = new JPanel();
			lotLabel[i] = new JLabel(labelNames[i]);
			lotField[i] = new JTextField(25);
			panel.add(lotLabel[i]);
			panel.add(lotField[i]);
			pnlAdd.add(panel);
		}
		JPanel panel = new JPanel();
		addLotsPanelBtn = new JButton("Add");
		addLotsPanelBtn.addActionListener(this);
		panel.add(addLotsPanelBtn);
		pnlAdd.add(panel);
		
		//Update panel
		add(lotPanelList, BorderLayout.CENTER);		
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
				myLotList = db.getLots();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			data = new Object[myLotList.size()][lotColumns.length];
			for (int i = 0; i < myLotList.size(); i++) {
				data[i][0] = myLotList.get(i).getLotName();
				data[i][1] = myLotList.get(i).getLocation();
				data[i][2] = myLotList.get(i).getCapacity();
				data[i][3] = myLotList.get(i).getFloors();	
			}
			lotPanelList.removeAll();
			table = new JTable(data, lotColumns);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			lotPanelList.add(scrollPane);
			lotPanelList.revalidate();
			this.repaint();
			
		} else if (e.getSource() == searchLotsBtn) {
			lotPanelList.removeAll();
			lotPanelList.add(pnlSearch);
			lotPanelList.revalidate();
			this.repaint();
		} else if (e.getSource() == addLotsButton) {
			lotPanelList.removeAll();
			lotPanelList.add(pnlAdd);
			lotPanelList.revalidate();
			this.repaint();
			
		} else if (e.getSource() == searchLotsPanelBtn) {
			String lotName = lotNameField.getText();
			if (lotName.length() > 0) {
				try {
					myLotList = db.getLots(lotName);
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(this, exception.getMessage());
					return;
				}
				data = new Object[myLotList.size()][lotColumns.length];
				for (int i = 0; i < myLotList.size(); i++) {
					data[i][0] = myLotList.get(i).getLotName();
					data[i][1] = myLotList.get(i).getLocation();
					data[i][2] = myLotList.get(i).getCapacity();
					data[i][3] = myLotList.get(i).getFloors();	
				}
				lotPanelList.removeAll();
				table = new JTable(data, lotColumns);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				lotPanelList.add(scrollPane);
				lotPanelList.revalidate();
				this.repaint();
			}
		} else if (e.getSource() == addLotsPanelBtn) {
			Lot lot = new Lot(lotField[0].getText(), lotField[1].getText(), 
					Integer.parseInt(lotField[2].getText()), Integer.parseInt(lotField[3].getText()));
			try {
				db.addLot(lot);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i = 0; i < lotField.length; i++) {
				lotField[i].setText("");
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
        	 db.updateLot(row, columnName, data);;
		}
		catch(Exception exception) {
			JOptionPane.showMessageDialog(this, exception.getMessage());
			return;
		}		
	}
}
