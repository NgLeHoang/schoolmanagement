package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
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

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherRegistration {

	public JFrame frame;
	private JTextField textTeacherName;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textAddress;
	private JTextField textQualification;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherRegistration window = new TeacherRegistration();
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
	public TeacherRegistration() {
		initialize();
		Connect();
		Teacher_Load();
	}
	
	Connection connect;
	PreparedStatement prepareState;
	ResultSet resultSet;
	DefaultTableModel dTableModel;
	private JTextField textTeacherNumber;
	
	public void Connect()
	{
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmanagement", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Teacher_Load() 
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from teacher");
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
					vector.add(resultSet.getString("teachername"));
					vector.add(resultSet.getString("teachernumber"));
					vector.add(resultSet.getString("dateofbirth"));
					vector.add(resultSet.getString("gender"));
					vector.add(resultSet.getString("email"));
					vector.add(resultSet.getString("phone"));
					vector.add(resultSet.getString("address"));
					vector.add(resultSet.getString("department"));
					vector.add(resultSet.getString("qualification"));
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
		frame.setBounds(100, 100, 1201, 702);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(29, 90, 342, 511);
		frame.getContentPane().add(panel);
		
		JLabel lblTeacherName = new JLabel("Teacher Name");
		lblTeacherName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeacherName.setBounds(10, 36, 107, 24);
		panel.add(lblTeacherName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateOfBirth.setBounds(10, 83, 99, 24);
		panel.add(lblDateOfBirth);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGender.setBounds(10, 134, 99, 24);
		panel.add(lblGender);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(10, 188, 99, 24);
		panel.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(10, 239, 99, 24);
		panel.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(10, 293, 99, 24);
		panel.add(lblAddress);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(10, 347, 99, 24);
		panel.add(lblDepartment);
		
		textTeacherName = new JTextField();
		textTeacherName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTeacherName.setColumns(10);
		textTeacherName.setBounds(146, 29, 180, 31);
		panel.add(textTeacherName);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(146, 185, 180, 31);
		panel.add(textEmail);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPhone.setColumns(10);
		textPhone.setBounds(146, 236, 180, 31);
		panel.add(textPhone);
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAddress.setColumns(10);
		textAddress.setBounds(146, 290, 180, 31);
		panel.add(textAddress);
		
		JDateChooser dateOfBirth = new JDateChooser();
		dateOfBirth.setBounds(146, 83, 180, 31);
		panel.add(dateOfBirth);
		
		JComboBox cbGender = new JComboBox();
		cbGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cbGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbGender.setBounds(146, 134, 180, 31);
		panel.add(cbGender);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"Mathematics - Computer Science", "Physical - Technical physics", "Chemistry", "Biology - Biotechnology", "Electronics - Telecommunication", "Technology", "Geological", "Materials Science - Technology", "Environment"}));
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setBounds(146, 344, 180, 31);
		panel.add(cbDepartment);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQualification.setBounds(10, 402, 107, 24);
		panel.add(lblQualification);
		
		textQualification = new JTextField();
		textQualification.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textQualification.setColumns(10);
		textQualification.setBounds(146, 399, 180, 31);
		panel.add(textQualification);
		
		JLabel lblTeacherNumber = new JLabel("Teacher Number");
		lblTeacherNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeacherNumber.setBounds(10, 457, 126, 24);
		panel.add(lblTeacherNumber);
		
		textTeacherNumber = new JTextField();
		textTeacherNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTeacherNumber.setColumns(10);
		textTeacherNumber.setBounds(146, 450, 180, 31);
		panel.add(textTeacherNumber);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String teachername = textTeacherName.getText();
					String teachernumber = textTeacherNumber.getText();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dateofbirth = dateFormat.format(dateOfBirth.getDate());
					
					String gender = cbGender.getSelectedItem().toString();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					String address = textAddress.getText();
					String department = cbDepartment.getSelectedItem().toString();
					String qualification = textQualification.getText();
					
					prepareState = connect.prepareStatement("insert into teacher(teachername,teachernumber,dateofbirth,gender,email,phone,address,department,qualification)values(?,?,?,?,?,?,?,?,?)");
					prepareState.setString(1, teachername);
					prepareState.setString(2, teachernumber);
					prepareState.setString(3, dateofbirth);
					prepareState.setString(4, gender);
					prepareState.setString(5, email);
					prepareState.setString(6, phone);
					prepareState.setString(7, address);
					prepareState.setString(8, department);
					prepareState.setString(9, qualification);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Teacher Added");
					
					textTeacherName.setText("");
					textTeacherNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbDepartment.setSelectedIndex(-1);
					textQualification.setText("");
					
					textTeacherName.requestFocus();
					Teacher_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(29, 612, 96, 42);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
					String teachername = textTeacherName.getText();
					String teachernumber = textTeacherNumber.getText();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dateofbirth = dateFormat.format(dateOfBirth.getDate());
					
					String gender = cbGender.getSelectedItem().toString();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					String address = textAddress.getText();
					String department = cbDepartment.getSelectedItem().toString();
					String qualification = textQualification.getText();
					
					prepareState = connect.prepareStatement("update teacher set teachername=?,teachernumber=?,dateofbirth=?,gender=?,email=?,phone=?,address=?,department=?,qualification=? where id=?");
					prepareState.setString(1, teachername);
					prepareState.setString(2, teachernumber);
					prepareState.setString(3, dateofbirth);
					prepareState.setString(4, gender);
					prepareState.setString(5, email);
					prepareState.setString(6, phone);
					prepareState.setString(7, address);
					prepareState.setString(8, department);
					prepareState.setString(9, qualification);
					prepareState.setString(10, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Teacher Edited");
					btnSave.setEnabled(true);
					
					textTeacherName.setText("");
					textTeacherNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbDepartment.setSelectedIndex(-1);
					textQualification.setText("");
					
					textTeacherName.requestFocus();
					Teacher_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(135, 612, 96, 42);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				textTeacherName.setText("");
				textTeacherNumber.setText("");
				dateOfBirth.setCalendar(null);
				cbGender.setSelectedIndex(-1);
				textEmail.setText("");
				textPhone.setText("");
				textAddress.setText("");
				cbDepartment.setSelectedIndex(-1);
				textQualification.setText("");
				
				textTeacherName.requestFocus();
				Teacher_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(241, 612, 96, 42);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelele = new JButton("Delele");
		btnDelele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
															
					prepareState = connect.prepareStatement("delete from teacher where id = ?");
					prepareState.setString(1, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Teacher Deleted");
					btnSave.setEnabled(true);
					
					textTeacherName.setText("");
					textTeacherNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbDepartment.setSelectedIndex(-1);
					textQualification.setText("");
					
					textTeacherName.requestFocus();
					Teacher_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelele.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelele.setBounds(347, 612, 96, 42);
		frame.getContentPane().add(btnDelele);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(593, 612, 114, 42);
		frame.getContentPane().add(btnClose);
		
		JLabel lblTeacherManagement = new JLabel("Teacher Management");
		lblTeacherManagement.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTeacherManagement.setBounds(474, 11, 267, 56);
		frame.getContentPane().add(lblTeacherManagement);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(407, 90, 746, 511);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dTableModel = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String id = dTableModel.getValueAt(selectedIndex, 0).toString();
				textTeacherName.setText(dTableModel.getValueAt(selectedIndex, 1).toString());
				textTeacherNumber.setText(dTableModel.getValueAt(selectedIndex, 2).toString());
		
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
				cbDepartment.setSelectedItem(dTableModel.getValueAt(selectedIndex, 8).toString());
				textQualification.setText(dTableModel.getValueAt(selectedIndex, 9).toString());
				
				btnSave.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Teacher Name", "Teacher Number", "Date of Birth", "Gender", "Email", "Phone", "Address", "Department", "Qualification"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
}
