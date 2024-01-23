package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	public JFrame frame;
	private JTextField textUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
		Connect();
	}
	
	Connection connect;
	PreparedStatement prepareState;
	ResultSet resultSet;
	DefaultTableModel dTableModel;
	private JPasswordField textPassword;
	
	public void Connect()
	{
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmanagement", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(306, 11, 131, 88);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(65, 90, 614, 330);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(98, 75, 88, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(98, 148, 88, 30);
		panel.add(lblNewLabel_1_1);
		
		textUsername = new JTextField();
		textUsername.setBounds(235, 77, 236, 30);
		panel.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("UserType");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(98, 224, 88, 30);
		panel.add(lblNewLabel_1_1_1);
		
		JComboBox cbUserType = new JComboBox();
		cbUserType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbUserType.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Teacher", "Staff", "Student"}));
		cbUserType.setBounds(235, 224, 236, 30);
		panel.add(cbUserType);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(235, 148, 236, 30);
		panel.add(textPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String password = textPassword.getText();
				String usertype = cbUserType.getSelectedItem().toString();
				
				try {
					prepareState = connect.prepareStatement("SELECT * FROM "
							+ "user WHERE username = ? AND password = ? AND usertype = ?");
					prepareState.setString(1, username);
					prepareState.setString(2, password);
					prepareState.setString(3, usertype);
					resultSet = prepareState.executeQuery();
					
					if (resultSet.next()) {
					    String userType = resultSet.getString("usertype");
					    if ("Admin".equals(userType)) {
					        int id = resultSet.getInt("id");
					        frame.setVisible(false);
					        Main main = new Main(id, username, userType);
					        main.frame.setVisible(true);
					    } else if ("Staff".equals(userType)) {
							int id = resultSet.getInt("id");
							String staffnumber = resultSet.getString("number");
							frame.setVisible(false);
							StaffMain staffMain = new StaffMain(id, username, userType, staffnumber);
							staffMain.frame.setVisible(true);
					    } else if ("Teacher".equals(userType)) {
							int id = resultSet.getInt("id");
							String teachernumber = resultSet.getString("number");
							frame.setVisible(false);
							TeacherMain teacherMain = new TeacherMain(id, username, userType, teachernumber);
							teacherMain.frame.setVisible(true);
					    } else if ("Student".equals(userType)) {
							int id = resultSet.getInt("id");
							String studentnumber = resultSet.getString("number");
							frame.setVisible(false);
							StudentMain studentMain = new StudentMain(id, username, userType, studentnumber);
							studentMain.frame.setVisible(true);
					    }	
					    else {
					        JOptionPane.showMessageDialog(null, "You don't permission to login.");
					        textUsername.setText("");
							textPassword.setText("");
							cbUserType.setSelectedIndex(-1);
							textUsername.requestFocus();
					    }
					} else {
					    JOptionPane.showMessageDialog(null, "Infomation sign in is wrong. Please reinput");
					    textUsername.setText("");
						textPassword.setText("");
						cbUserType.setSelectedIndex(-1);
						textUsername.requestFocus();
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(184, 462, 131, 57);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(388, 462, 131, 57);
		frame.getContentPane().add(btnCancel);
	}
}
