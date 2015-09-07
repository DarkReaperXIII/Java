/**
code for adding employee info to table
Does not require user to add all required fields 

Open to any and all suggestions for changes to be made to make code better.*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AddEmp {

	private JFrame frame;
	private JTextField fnameTextField;
	private JTextField lnameTextField;
	private JTextField emp_idTextField;
	private JLabel emailLbl;
	private JTextField emailTextField;
	private JTextField ageTextField;
	private JTextField occTextField;
	private JLabel lnameLbl;
	private JLabel emp_idLbl;
	private JLabel ageLbl;
	private JLabel occLbl;
	private JLabel infoTitleLbl;
	private JButton submitButton;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void mainAdd(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmp window = new AddEmp();
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
	public AddEmp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Connection con=MainWindow.dbConnection();//gets connection from dbConnection function
		
		/*Closes AddEmp windo and reopens MainWindow*/
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow mw = new MainWindow();
				mw.setVisible();
			}
		});
		btnMainMenu.setBounds(478, 362, 131, 41);
		frame.getContentPane().add(btnMainMenu);
		
		fnameTextField = new JTextField();
		fnameTextField.setBounds(134, 48, 131, 33);
		frame.getContentPane().add(fnameTextField);
		fnameTextField.setColumns(10);
		
		JLabel fnameLbl = new JLabel("First Name");
		fnameLbl.setBounds(10, 48, 88, 33);
		frame.getContentPane().add(fnameLbl);
		
		lnameTextField = new JTextField();
		lnameTextField.setBounds(134, 102, 130, 33);
		frame.getContentPane().add(lnameTextField);
		lnameTextField.setColumns(10);
		
		emp_idTextField = new JTextField();
		emp_idTextField.setBounds(178, 156, 131, 33);
		frame.getContentPane().add(emp_idTextField);
		emp_idTextField.setColumns(10);
		
		emailLbl = new JLabel("Email");
		emailLbl.setBounds(388, 52, 88, 24);
		frame.getContentPane().add(emailLbl);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(478, 48, 131, 33);
		frame.getContentPane().add(emailTextField);
		emailTextField.setColumns(10);
		
		ageTextField = new JTextField();
		ageTextField.setBounds(478, 102, 131, 33);
		frame.getContentPane().add(ageTextField);
		ageTextField.setColumns(10);
		
		occTextField = new JTextField();
		occTextField.setBounds(488, 156, 131, 33);
		frame.getContentPane().add(occTextField);
		occTextField.setColumns(10);
		
		lnameLbl = new JLabel("Last Name");
		lnameLbl.setBounds(10, 106, 98, 24);
		frame.getContentPane().add(lnameLbl);
		
		emp_idLbl = new JLabel("Employee ID (ex 1234)");
		emp_idLbl.setBounds(10, 165, 153, 14);
		frame.getContentPane().add(emp_idLbl);
		
		ageLbl = new JLabel("Age");
		ageLbl.setBounds(388, 111, 46, 14);
		frame.getContentPane().add(ageLbl);
		
		occLbl = new JLabel("Occupation");
		occLbl.setBounds(388, 165, 68, 14);
		frame.getContentPane().add(occLbl);
		
		infoTitleLbl = new JLabel("Add Employee Info");
		infoTitleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		infoTitleLbl.setBounds(350, 11, 230, 33);
		frame.getContentPane().add(infoTitleLbl);
		
		
		/*performs INSERT query into database*/
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname, lname, emp_id, email, age,occupation, insert;
				PreparedStatement stmt=null;
				try{
					insert="INSERT INTO employee(fname, lname, emp_id, email, age, occupation) "
							+ "values (?,?,?,?,?,?)";
					/**
					gets input from textFields and assigns them to proper variables*/
					fname=fnameTextField.getText();
					lname=lnameTextField.getText();
					emp_id=emp_idTextField.getText();
					email=emailTextField.getText();
					age=ageTextField.getText();
					occupation=occTextField.getText();
					
					/**
					plugs appropriate variables into insert string
					to properly execute INSERT query to table*/
					stmt = con.prepareStatement(insert);
					stmt.setString(1,fname);
					stmt.setString(2, lname);
					stmt.setString(3, emp_id);
					stmt.setString(4, email);
					stmt.setString(5, age);
					stmt.setString(6, occupation);
					stmt.executeUpdate();
					
					//clears textFields after INSERT is executed
					fnameTextField.setText("");
					lnameTextField.setText("");
					emp_idTextField.setText("");
					emailTextField.setText("");
					ageTextField.setText("");
					occTextField.setText("");
					
					/*Reprints table and values to see new info that was added.
					Can more than likely be written better.
					Open to any and all suggestion*/
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
						String selectQuery="SELECT * FROM employee";
						Statement stmt2=con.createStatement();
						ResultSet rs = stmt2.executeQuery(selectQuery);
						while(rs.next()){
							dtm.addRow(new Object[]{rs.getString("fname"),rs.getString("lname"),rs.getString("emp_id"),
									rs.getString("email"),rs.getString("age"),rs.getString("occupation")});
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				} catch (Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				} finally {
					try {
						stmt.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});
		submitButton.setBounds(85, 362, 115, 41);
		frame.getContentPane().add(submitButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 209, 664, 145);
		frame.getContentPane().add(scrollPane);
		
		/**prints table to be seen for those who want know 
		what is in table currently before pressing Submit*/
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
			String selectQuery="SELECT * FROM employee";
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
	
	//function to be called to allow MainWindow to pull up AddEmp window
	public void setVisible() {
		frame.setVisible(true);
		
	}

}
