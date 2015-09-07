import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class DeleteEmp {
	private JFrame frame;
	private JTable table;
	private JButton btnDelete;
	private JTextField delTextField;
	private JLabel deleteEmp_IdLbl;
	private JLabel deleteTitleLbl;

	/**
	 * Launch the application.
	 */
	public static void mainDelete(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmp window = new DeleteEmp();
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
	public DeleteEmp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String selectQuery="SELECT * FROM employee";
		frame = new JFrame();
		frame.setBounds(100, 100, 846, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		MainWindow mw = new MainWindow();
		Connection con=mw.dbConnection();//gets Connection obj from MainWindow function
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow mw = new MainWindow();
				mw.setVisible();
			}
		});
		btnMainMenu.setBounds(654, 264, 166, 36);
		frame.getContentPane().add(btnMainMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 810, 139);
		frame.getContentPane().add(scrollPane);
		
		DefaultTableModel dtm = new DefaultTableModel();
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		btnDelete = new JButton("Delete Employee");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emp_idDelete;
				String delete;
				PreparedStatement stmt=null;
				delete="DELETE FROM employee "
						+ "WHERE emp_id = ?";
				emp_idDelete=delTextField.getText();
				try {
					stmt = con.prepareStatement(delete);
					stmt.setString(1, emp_idDelete);
					stmt.executeUpdate();
					
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
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				delTextField.setText("");
			}
		});
		btnDelete.setBounds(10, 271, 166, 36);
		frame.getContentPane().add(btnDelete);
		
		delTextField = new JTextField();
		delTextField.setBounds(10, 229, 166, 31);
		frame.getContentPane().add(delTextField);
		delTextField.setColumns(10);
		
		deleteEmp_IdLbl = new JLabel("Employee ID ");
		deleteEmp_IdLbl.setBounds(62, 191, 166, 27);
		frame.getContentPane().add(deleteEmp_IdLbl);
		
		deleteTitleLbl = new JLabel("Delete Employee");
		deleteTitleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		deleteTitleLbl.setBounds(335, 183, 126, 36);
		frame.getContentPane().add(deleteTitleLbl);
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

	public void setVisible() {
		frame.setVisible(true);
		
	}

}
