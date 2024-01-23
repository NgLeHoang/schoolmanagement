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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentRegistration {

	public JFrame frame;
	private JTextField textStudentName;
	private JTextField textStudentNumber;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textAddress;
	private JTable table;
	JComboBox cbClassName = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentRegistration window = new StudentRegistration();
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
	public StudentRegistration() {
		initialize();
		Connect();
		Load_Class();
		Student_Load();
	}
	
	Connection connect;
	PreparedStatement prepareState;
	ResultSet resultSet;
	DefaultTableModel dTableModel;
	
	public void Connect()
	{
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmanagement", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Load_Class()
	{
		try {
		    prepareState = connect.prepareStatement("SELECT DISTINCT classname FROM class");
		    resultSet = prepareState.executeQuery();

		    cbClassName.removeAllItems();

		    while (resultSet.next()) {
		    	cbClassName.addItem(resultSet.getString("classname"));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public void Student_Load() 
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from student");
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
					vector.add(resultSet.getString("studentname"));
					vector.add(resultSet.getString("studentnumber"));
					vector.add(resultSet.getString("dateofbirth"));
					vector.add(resultSet.getString("gender"));
					vector.add(resultSet.getString("email"));
					vector.add(resultSet.getString("phone"));
					vector.add(resultSet.getString("address"));
					vector.add(resultSet.getString("classname"));
					vector.add(resultSet.getString("department"));
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
		frame.setBounds(100, 100, 1233, 687);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblStudentManagement = new JLabel("Student Management");
		lblStudentManagement.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblStudentManagement.setBounds(465, 11, 267, 56);
		frame.getContentPane().add(lblStudentManagement);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(44, 81, 342, 494);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 36, 107, 24);
		panel.add(lblNewLabel);
		
		JLabel lblStudentNumber = new JLabel("Student Number");
		lblStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudentNumber.setBounds(10, 83, 115, 24);
		panel.add(lblStudentNumber);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateOfBirth.setBounds(10, 129, 99, 24);
		panel.add(lblDateOfBirth);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGender.setBounds(10, 175, 99, 24);
		panel.add(lblGender);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(10, 225, 99, 24);
		panel.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(10, 275, 99, 24);
		panel.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(10, 327, 99, 24);
		panel.add(lblAddress);
		
		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClassName.setBounds(10, 382, 99, 24);
		panel.add(lblClassName);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(10, 438, 99, 24);
		panel.add(lblDepartment);
		
		textStudentName = new JTextField();
		textStudentName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStudentName.setBounds(146, 29, 180, 31);
		panel.add(textStudentName);
		textStudentName.setColumns(10);
		
		textStudentNumber = new JTextField();
		textStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStudentNumber.setColumns(10);
		textStudentNumber.setBounds(146, 76, 180, 31);
		panel.add(textStudentNumber);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(146, 218, 180, 31);
		panel.add(textEmail);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPhone.setColumns(10);
		textPhone.setBounds(146, 274, 180, 31);
		panel.add(textPhone);
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAddress.setColumns(10);
		textAddress.setBounds(146, 327, 180, 31);
		panel.add(textAddress);
		
		JDateChooser dateOfBirth = new JDateChooser();
		dateOfBirth.setBounds(146, 122, 180, 31);
		panel.add(dateOfBirth);
		
		JComboBox cbGender = new JComboBox();
		cbGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cbGender.setBounds(146, 171, 180, 31);
		panel.add(cbGender);
		
		cbClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbClassName.setBounds(146, 381, 180, 31);
		panel.add(cbClassName);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"Mathematics - Computer Science", "Physical - Technical physics", "Chemistry", "Biology - Biotechnology", "Electronics - Telecommunication", "Technology", "Geological", "Materials Science - Technology", "Environment"}));
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setBounds(146, 438, 180, 31);
		panel.add(cbDepartment);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String studentname = textStudentName.getText();
					String studentnumber = textStudentNumber.getText();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dateofbirth = dateFormat.format(dateOfBirth.getDate());
					
					String gender = cbGender.getSelectedItem().toString();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					String address = textAddress.getText();
					String classname = cbClassName.getSelectedItem().toString();
					String department = cbDepartment.getSelectedItem().toString();
					
					prepareState = connect.prepareStatement("insert into student(studentname,studentnumber,dateofbirth,gender,email,phone,address,classname,department)values(?,?,?,?,?,?,?,?,?)");
					prepareState.setString(1, studentname);
					prepareState.setString(2, studentnumber);
					prepareState.setString(3, dateofbirth);
					prepareState.setString(4, gender);
					prepareState.setString(5, email);
					prepareState.setString(6, phone);
					prepareState.setString(7, address);
					prepareState.setString(8, classname);
					prepareState.setString(9, department);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Student Add");
					
					textStudentName.setText("");
					textStudentNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					
					textStudentName.requestFocus();
					Student_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(41, 586, 96, 42);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
					String studentname = textStudentName.getText();
					String studentnumber = textStudentNumber.getText();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dateofbirth = dateFormat.format(dateOfBirth.getDate());
					
					String gender = cbGender.getSelectedItem().toString();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					String address = textAddress.getText();
					String classname = cbClassName.getSelectedItem().toString();
					String department = cbDepartment.getSelectedItem().toString();
					
					prepareState = connect.prepareStatement("update student set studentname=?,studentnumber=?,dateofbirth=?,gender=?,email=?,phone=?,address=?,classname=?,department=? where id=?");
					prepareState.setString(1, studentname);
					prepareState.setString(2, studentnumber);
					prepareState.setString(3, dateofbirth);
					prepareState.setString(4, gender);
					prepareState.setString(5, email);
					prepareState.setString(6, phone);
					prepareState.setString(7, address);
					prepareState.setString(8, classname);
					prepareState.setString(9, department);
					prepareState.setString(10, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Student Update");
					btnSave.setEnabled(true);
					
					textStudentName.setText("");
					textStudentNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					
					textStudentName.requestFocus();
					Student_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(154, 586, 96, 42);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				textStudentName.setText("");
				textStudentNumber.setText("");
				dateOfBirth.setCalendar(null);
				cbGender.setSelectedIndex(-1);
				textEmail.setText("");
				textPhone.setText("");
				textAddress.setText("");
				cbClassName.setSelectedIndex(-1);
				cbDepartment.setSelectedIndex(-1);
				
				textStudentName.requestFocus();
				Student_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(265, 586, 96, 42);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelele = new JButton("Delele");
		btnDelele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
															
					prepareState = connect.prepareStatement("delete from student where id = ?");
					prepareState.setString(1, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Student Deleted");
					btnSave.setEnabled(true);
					
					textStudentName.setText("");
					textStudentNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					
					textStudentName.requestFocus();
					Student_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelele.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelele.setBounds(382, 586, 96, 42);
		frame.getContentPane().add(btnDelele);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(567, 586, 114, 42);
		frame.getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 87, 759, 488);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dTableModel = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String id = dTableModel.getValueAt(selectedIndex, 0).toString();
				textStudentName.setText(dTableModel.getValueAt(selectedIndex, 1).toString());
				textStudentNumber.setText(dTableModel.getValueAt(selectedIndex, 2).toString());
				
				try {
					Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String)dTableModel.getValueAt(selectedIndex, 3));
					dateOfBirth.setDate(date);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				cbGender.setSelectedItem(dTableModel.getValueAt(selectedIndex, 4).toString());
				textEmail.setText(dTableModel.getValueAt(selectedIndex, 5).toString());
				textPhone.setText(dTableModel.getValueAt(selectedIndex, 6).toString());
				textAddress.setText(dTableModel.getValueAt(selectedIndex, 7).toString());
				cbClassName.setSelectedItem(dTableModel.getValueAt(selectedIndex, 8).toString());
				cbDepartment.setSelectedItem(dTableModel.getValueAt(selectedIndex, 9).toString());
				
				btnSave.setEnabled(false);
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Student Name", "Student Number", "Date of Birth", "Gender", "Email", "Phone", "Address", "Class Name", "Department"
			}
		) {
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(22);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		table.getColumnModel().getColumn(5).setPreferredWidth(85);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
		table.getColumnModel().getColumn(9).setPreferredWidth(80);
	}
}
