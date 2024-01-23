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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User {

	public JFrame frame;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User window = new User();
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
	public User() {
		initialize();
		Connect();
		User_Load();
	}
	
	Connection connect;
	PreparedStatement prepareState;
	ResultSet resultSet;
	DefaultTableModel dTableModel;
	private JTextField textNumber;
	
	public void Connect()
	{
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmanagement", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void User_Load()
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from user");
			resultSet = prepareState.executeQuery();
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			colums = resultSetMetaData.getColumnCount();
			
			dTableModel = (DefaultTableModel)table.getModel();
			dTableModel.setRowCount(0);
			
			while(resultSet.next())
			{
				Vector vector = new Vector();
				
				for (int i = 1; i <= colums; i++)
				{
					vector.add(resultSet.getString("id"));
					vector.add(resultSet.getString("name"));
					vector.add(resultSet.getString("number"));
					vector.add(resultSet.getString("phone"));
					vector.add(resultSet.getString("email"));
					vector.add(resultSet.getString("username"));
					vector.add(resultSet.getString("usertype"));
				}
				dTableModel.addRow(vector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 920, 622);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(32, 31, 368, 462);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(56, 161, 69, 20);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblPhone);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(56, 60, 69, 20);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(56, 212, 69, 20);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(56, 332, 69, 20);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("UserType");
		lblNewLabel_4.setBounds(56, 395, 69, 20);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Username");
		lblNewLabel_5.setBounds(56, 270, 85, 20);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_5);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textName.setBounds(166, 62, 140, 20);
		panel.add(textName);
		textName.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPhone.setColumns(10);
		textPhone.setBounds(166, 161, 140, 20);
		panel.add(textPhone);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(166, 214, 140, 20);
		panel.add(textEmail);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textUsername.setColumns(10);
		textUsername.setBounds(166, 270, 140, 20);
		panel.add(textUsername);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPassword.setBounds(168, 332, 138, 20);
		panel.add(textPassword);
		
		JComboBox cbUserType = new JComboBox();
		cbUserType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbUserType.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Teacher", "Staff", "Student"}));
		cbUserType.setBounds(166, 394, 140, 22);
		panel.add(cbUserType);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumber.setBounds(56, 116, 69, 20);
		panel.add(lblNumber);
		
		textNumber = new JTextField();
		textNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNumber.setColumns(10);
		textNumber.setBounds(166, 116, 140, 20);
		panel.add(textNumber);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String name = textName.getText();
					String number = textNumber.getText();
					String phone = textPhone.getText();
					String email = textEmail.getText();
					String username = textUsername.getText();
					String password = textPassword.getText();
					String usertype = cbUserType.getSelectedItem().toString();
					
					prepareState = connect.prepareStatement("insert "
							+ "into user(name,number,phone,email,username,password,usertype)"
							+ "values(?,?,?,?,?,?,?)");
					prepareState.setString(1, name);
					prepareState.setString(2, number);
					prepareState.setString(3, phone);
					prepareState.setString(4, email);
					prepareState.setString(5, username);
					prepareState.setString(6, password);
					prepareState.setString(7, usertype);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "User Added");
					
					textName.setText("");
					textNumber.setText("");
					textPhone.setText("");
					textEmail.setText("");
					textUsername.setText("");
					textPassword.setText("");
					cbUserType.setSelectedIndex(-1);
					
					textName.requestFocus();
					User_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(32, 522, 96, 42);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
					
					String name = textName.getText();
					String number = textNumber.getText();
					String phone = textPhone.getText();
					String email = textEmail.getText();
					String username = textUsername.getText();
					String usertype = cbUserType.getSelectedItem().toString();
					
					prepareState = connect.prepareStatement("update "
							+ "user set "
							+ "name=?, number=?, phone=?, email=?, username=?, usertype=? where id=?");
					prepareState.setString(1, name);
					prepareState.setString(2, number);
					prepareState.setString(3, phone);
					prepareState.setString(4, email);
					prepareState.setString(5, username);
					prepareState.setString(6, usertype);
					prepareState.setString(7, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "User Edited");
					btnSave.setEnabled(true);
					
					textName.setText("");
					textNumber.setText("");
					textPhone.setText("");
					textEmail.setText("");
					textUsername.setText("");
					textPassword.setText("");
					cbUserType.setSelectedIndex(-1);
					
					textName.requestFocus();
					User_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(138, 522, 96, 42);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				textName.setText("");
				textNumber.setText("");
				textPhone.setText("");
				textEmail.setText("");
				textUsername.setText("");
				textPassword.setText("");
				cbUserType.setSelectedIndex(-1);
				
				textName.requestFocus();
				User_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(244, 522, 96, 42);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelele = new JButton("Delele");
		btnDelele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
															
					prepareState = connect.prepareStatement("delete from user where id = ?");
					prepareState.setString(1, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "User Deleted");
					btnSave.setEnabled(true);
					
					textName.setText("");
					textNumber.setText("");
					textPhone.setText("");
					textEmail.setText("");
					textUsername.setText("");
					textPassword.setText("");
					cbUserType.setSelectedIndex(-1);
					
					textName.requestFocus();
					User_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelele.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelele.setBounds(350, 522, 96, 42);
		frame.getContentPane().add(btnDelele);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(588, 522, 114, 42);
		frame.getContentPane().add(btnClose);
		
		JLabel lblNewLabel = new JLabel("User Creation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(45, 0, 180, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 32, 465, 462);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dTableModel = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String id = dTableModel.getValueAt(selectedIndex, 0).toString();
				textName.setText(dTableModel.getValueAt(selectedIndex, 1).toString());
				textNumber.setText(dTableModel.getValueAt(selectedIndex, 2).toString());
				textPhone.setText(dTableModel.getValueAt(selectedIndex, 3).toString());
				textEmail.setText(dTableModel.getValueAt(selectedIndex, 4).toString());
				textUsername.setText(dTableModel.getValueAt(selectedIndex, 5).toString());
				cbUserType.setSelectedItem(dTableModel.getValueAt(selectedIndex, 6).toString());
				
				btnSave.setEnabled(false);
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Number", "Phone", "Email", "Username", "UserType"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}
}
