/**
Code for allowing user to update Employee info
does not require user to update all fields

Open to any and all suggestions to help make code better
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
public class UpdateEmp {

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
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField emp_idCurrentTextField;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void mainUpdate(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmp window = new UpdateEmp();
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
	public UpdateEmp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 744, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Connection con = MainWindow.dbConnection();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 272, 708, 129);
		frame.getContentPane().add(scrollPane);
		
		/**
		 * Prints table to allow user to see current values in each column 
		 * before committing updates
		*/
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
		
		/**performs UPDATE query on table. Can more than likely be written better. Open to any and all
		suggestions*/
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname, lname, emp_id, email, age, occupation, emp_idCurrent;
				String update1, update2, update3, update4, update5, update6;
				PreparedStatement stmt1=null;
				PreparedStatement stmt2=null;
				PreparedStatement stmt3=null;
				PreparedStatement stmt4=null;
				PreparedStatement stmt5=null;
				PreparedStatement stmt6=null;
				
				try{
					emp_idCurrent=emp_idCurrentTextField.getText();
					
					/**checks to see if textField is empty
					if it is empty, not changes get made to that particular variable.
					
					Done so blank textfields do add empty strings into table.
					Repeated for all text fields.*/
					if(fnameTextField.getText().isEmpty()){
						
					} else{
						fname=fnameTextField.getText();
						update1="UPDATE employee"
								+ " SET fname = ?"
								+ " WHERE emp_id = ? ";
						stmt1 = con.prepareStatement(update1);
						stmt1.setString(1,fname);
						stmt1.setString(2, emp_idCurrent);
						stmt1.executeUpdate();
					}
					//repeat 2
					if(lnameTextField.getText().isEmpty()){
						
					} else {
						lname=lnameTextField.getText();
						update2="UPDATE employee"
								+ " SET lname = ?"
								+ " WHERE emp_id = ? ";
						stmt2 = con.prepareStatement(update2);
						stmt2.setString(1,lname);
						stmt2.setString(2, emp_idCurrent);
						stmt2.executeUpdate();
					}
					
					//repeat 3
					if(emp_idTextField.getText().isEmpty()){
						
					} else {
						emp_id=emp_idTextField.getText();
						update3="UPDATE employee"
								+ " SET emp_id = ?"
								+ " WHERE emp_id = ? ";
						stmt3 = con.prepareStatement(update3);
						stmt3.setString(1,emp_id);
						stmt3.setString(2, emp_idCurrent);
						stmt3.executeUpdate();
					}
					
					//repeat 4
					if (emailTextField.getText().isEmpty()){
						
					} else {
						email=emailTextField.getText();
						update4="UPDATE employee"
								+ " SET email = ?"
								+ " WHERE emp_id = ? ";
						stmt4 = con.prepareStatement(update4);
						stmt4.setString(1,email);
						stmt4.setString(2, emp_idCurrent);
						stmt4.executeUpdate();
					}
					
					//repeat 5
					if (ageTextField.getText().isEmpty()){
						
					} else {
						age=ageTextField.getText();
						update5="UPDATE employee"
								+ " SET age = ?"
								+ " WHERE emp_id = ? ";
						stmt5 = con.prepareStatement(update5);
						stmt5.setString(1,age);
						stmt5.setString(2, emp_idCurrent);
						stmt5.executeUpdate();
					}
					
					//repeat 6
					if (occTextField.getText().isEmpty()){
	
					} else {
						occupation=occTextField.getText();
						update6="UPDATE employee"
								+ " SET occupation = ?"
								+ " WHERE emp_id = ? ";
						stmt6 = con.prepareStatement(update6);
						stmt6.setString(1,occupation);
						stmt6.setString(2, emp_idCurrent);
						stmt6.executeUpdate();
					}
					
					/*Resets text fields after updates have been made*/
					fnameTextField.setText("");
					lnameTextField.setText("");
					emp_idTextField.setText("");
					emailTextField.setText("");
					ageTextField.setText("");
					occTextField.setText("");
					emp_idCurrentTextField.setText("");
					
					/**reprints table to see allow user to see changes.
					Open to ways to rewrite to make better*/
					
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
					
					
				} catch(Exception e1){
					
				}
			}
		});
		submitBtn.setBounds(90, 406, 138, 37);
		frame.getContentPane().add(submitBtn);
		
		fnameTextField = new JTextField();
		fnameTextField.setBounds(135, 65, 131, 33);
		frame.getContentPane().add(fnameTextField);
		fnameTextField.setColumns(10);
		
		JLabel fnameLbl = new JLabel("New First Name");
		fnameLbl.setBounds(10, 65, 115, 33);
		frame.getContentPane().add(fnameLbl);
		
		lnameTextField = new JTextField();
		lnameTextField.setBounds(135, 122, 130, 33);
		frame.getContentPane().add(lnameTextField);
		lnameTextField.setColumns(10);
		
		emp_idTextField = new JTextField();
		emp_idTextField.setBounds(182, 184, 131, 33);
		frame.getContentPane().add(emp_idTextField);
		emp_idTextField.setColumns(10);
		
		emailLbl = new JLabel("New Email");
		emailLbl.setBounds(388, 69, 88, 24);
		frame.getContentPane().add(emailLbl);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(486, 65, 131, 33);
		frame.getContentPane().add(emailTextField);
		emailTextField.setColumns(10);
		
		ageTextField = new JTextField();
		ageTextField.setBounds(486, 122, 131, 33);
		frame.getContentPane().add(ageTextField);
		ageTextField.setColumns(10);
		
		occTextField = new JTextField();
		occTextField.setBounds(520, 184, 131, 33);
		frame.getContentPane().add(occTextField);
		occTextField.setColumns(10);
		
		lnameLbl = new JLabel("New Last Name");
		lnameLbl.setBounds(10, 126, 115, 24);
		frame.getContentPane().add(lnameLbl);
		
		emp_idLbl = new JLabel("New Employee ID (ex 1234)");
		emp_idLbl.setBounds(10, 193, 162, 14);
		frame.getContentPane().add(emp_idLbl);
		
		ageLbl = new JLabel("New Age");
		ageLbl.setBounds(388, 131, 69, 14);
		frame.getContentPane().add(ageLbl);
		
		occLbl = new JLabel("New Occupation");
		occLbl.setBounds(388, 193, 109, 14);
		frame.getContentPane().add(occLbl);
		
		infoTitleLbl = new JLabel("Update Info");
		infoTitleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		infoTitleLbl.setBounds(421, 0, 230, 41);
		frame.getContentPane().add(infoTitleLbl);
		
		/**
		Closes UpdateEmp window and reopens MainWindow*/
		JButton menuBtn = new JButton("Main Menu");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow mw = new MainWindow();
				mw.setVisible();
			}
		});
		menuBtn.setBounds(463, 406, 138, 37);
		frame.getContentPane().add(menuBtn);
		
	
		
		emp_idCurrentTextField = new JTextField();
		emp_idCurrentTextField.setBounds(182, 13, 131, 33);
		frame.getContentPane().add(emp_idCurrentTextField);
		emp_idCurrentTextField.setColumns(10);
		
		lblNewLabel = new JLabel("Current Employee ID");
		lblNewLabel.setBounds(10, 17, 150, 24);
		frame.getContentPane().add(lblNewLabel);
	}
	
	//function used to allow other buttons to pull up UpdateEmp window
	public void setVisible() {
		frame.setVisible(true);
		
	}

}
