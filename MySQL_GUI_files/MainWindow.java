/**
Main file for GUI.
Only works with one table right now.
Will be adding more functionality to it soon.
Will be adding a login window soon as well.

Open to all who want to help make this code better in any way possible.

*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.sql.*;
public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	Connection con = null;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		/**
		check to see if there is an error connecting to database*/
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","790053aL");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Error Connecting");
			System.exit(0);
		}
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ViewEmp ve = new ViewEmp();
				ve.setVisible();
			}
		});
		btnView.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnView);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UpdateEmp ue = new UpdateEmp();
				ue.setVisible();
			}
		});
		btnUpdate.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddEmp ae = new AddEmp();
				ae.setVisible();
			}
		});
		btnAdd.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				DeleteEmp de = new DeleteEmp();
				de.setVisible();
			}
		});
		btnDelete.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblEmployeeDatabaseGui = new JLabel("MySQL Database GUI", SwingConstants.CENTER);
		lblEmployeeDatabaseGui.setBounds(123, 58, 171, 35);
		frame.getContentPane().add(lblEmployeeDatabaseGui);
		
		JLabel lblEmployeeTable = new JLabel("Employee Table", SwingConstants.CENTER);
		lblEmployeeTable.setBounds(123, 128, 171, 35);
		frame.getContentPane().add(lblEmployeeTable);
	}

	/*function used to allow other windows to pull MainWindow back up*/
	public void setVisible() { 
		frame.setVisible(true);
	}
	/*allows me to pass Connection object to other functions*/
	public static Connection dbConnection(){
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","790053aL");
			return con;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error sending connection.");
			return null;
		}
		
	}

}
