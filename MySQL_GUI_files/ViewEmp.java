/**
Code for allowing user to view info in table

Open to any and all suggestions to help make code better
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.sql.*;
public class ViewEmp {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void mainView(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmp window = new ViewEmp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewEmp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String selectQuery="SELECT * FROM employee"; //string to hold SELECT query
		frame = new JFrame();
		frame.setBounds(100, 100, 846, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		MainWindow mw = new MainWindow();
		Connection con=mw.dbConnection();//gets Connection obj from MainWindow function
		
		/**closes ViewEmp window and reopens MainWindow*/
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow mw = new MainWindow();
				mw.setVisible();
			}
		});
		btnMainMenu.setBounds(318, 251, 166, 36);
		frame.getContentPane().add(btnMainMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 810, 139);
		frame.getContentPane().add(scrollPane);
		
		/**prints table data for user to see all records.
		surrounded with JScrollPane if number of records exceeds space allocated for table*/
		DefaultTableModel dtm = new DefaultTableModel();
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		dtm.addColumn("fname");
		dtm.addColumn("lname");
		dtm.addColumn("emp_id");
		dtm.addColumn("email");
		dtm.addColumn("age");
		dtm.addColumn("occupation");
		try {
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			while(rs.next()){
				dtm.addRow(new Object[]{rs.getString("fname"),rs.getString("lname"),rs.getString("emp_id"),
						rs.getString("email"),rs.getString("age"),rs.getString("occupation")});
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	//function to alllow other buttons to pull up ViewEmp window
	public void setVisible() {
		frame.setVisible(true);
		
	}

}
