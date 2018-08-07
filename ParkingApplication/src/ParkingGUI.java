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
	private JButton btnList, btnSearch, btnAdd;
	private JPanel pnlButtons, pnlList;
	private ParkingDB db;
	private List<Lot> myLotList;
	private String[] columnNames = {
			"LotName",
            "Location",
            "Capacity",
            "Floors"
    };
	
	private Object[][] data;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlSearch;
	private JLabel lblLotName;;
	private JTextField txfLotName;
	private JButton btnLotNameSearch;
	
	private JPanel pnlAdd;
	private JLabel[] txfLabel = new JLabel[4];
	private JTextField[] txfField = new JTextField[4];
	private JButton btnAddLot;	
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public ParkingGUI() {
		super("Parking");		
		db = new ParkingDB();
		try	{
			myLotList = db.getLots();		
			data = new Object[myLotList.size()][columnNames.length];
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
		pnlButtons = new JPanel();
		btnList = new JButton("Lot List");
		btnList.addActionListener(this);
		
		btnSearch = new JButton("Lot Search");
		btnSearch.addActionListener(this);
		
		btnAdd = new JButton("Add Lot");
		btnAdd.addActionListener(this);
		
		pnlButtons.add(btnList);
		pnlButtons.add(btnSearch);
		pnlButtons.add(btnAdd);
		add(pnlButtons, BorderLayout.NORTH);
		
		//List Panel
		pnlList = new JPanel();
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane(table);
		pnlList.add(scrollPane);
		table.getModel().addTableModelListener(this);
		
		//Search Panel
		pnlSearch = new JPanel();
		lblLotName = new JLabel("Enter Lot Name: ");
		txfLotName = new JTextField(25);
		btnLotNameSearch = new JButton("Search");
		btnLotNameSearch.addActionListener(this);
		pnlSearch.add(lblLotName);
		pnlSearch.add(txfLotName);
		pnlSearch.add(btnLotNameSearch);
		
		//Add Panel
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(6, 0));
		String labelNames[] = {"Enter Lot Name: ", "Enter Location: ", "Enter Capacity: ", "Enter Floors: "};
		for (int i = 0; i < labelNames.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelNames[i]);
			txfField[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfField[i]);
			pnlAdd.add(panel);
		}
		JPanel panel = new JPanel();
		btnAddLot = new JButton("Add");
		btnAddLot.addActionListener(this);
		panel.add(btnAddLot);
		pnlAdd.add(panel);
		
		//Update panel
		add(pnlList, BorderLayout.CENTER);		
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
		if (e.getSource() == btnList) {
			try {
				myLotList = db.getLots();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			data = new Object[myLotList.size()][columnNames.length];
			for (int i = 0; i < myLotList.size(); i++) {
				data[i][0] = myLotList.get(i).getLotName();
				data[i][1] = myLotList.get(i).getLocation();
				data[i][2] = myLotList.get(i).getCapacity();
				data[i][3] = myLotList.get(i).getFloors();	
			}
			pnlList.removeAll();
			table = new JTable(data, columnNames);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlList.add(scrollPane);
			pnlList.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnSearch) {
			pnlList.removeAll();
			pnlList.add(pnlSearch);
			pnlList.revalidate();
			this.repaint();
		} else if (e.getSource() == btnAdd) {
			pnlList.removeAll();
			pnlList.add(pnlAdd);
			pnlList.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnLotNameSearch) {
			String lotName = txfLotName.getText();
			if (lotName.length() > 0) {
				try {
					myLotList = db.getLots(lotName);
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(this, exception.getMessage());
					return;
				}
				data = new Object[myLotList.size()][columnNames.length];
				for (int i = 0; i < myLotList.size(); i++) {
					data[i][0] = myLotList.get(i).getLotName();
					data[i][1] = myLotList.get(i).getLocation();
					data[i][2] = myLotList.get(i).getCapacity();
					data[i][3] = myLotList.get(i).getFloors();	
				}
				pnlList.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlList.add(scrollPane);
				pnlList.revalidate();
				this.repaint();
			}
		} else if (e.getSource() == btnAddLot) {
			Lot lot = new Lot(txfField[0].getText(), txfField[1].getText(), 
					Integer.parseInt(txfField[2].getText()), Integer.parseInt(txfField[3].getText()));
			try {
				db.addLot(lot);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i = 0; i < txfField.length; i++) {
				txfField[i].setText("");
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
