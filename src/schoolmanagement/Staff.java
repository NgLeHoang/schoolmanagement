package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Staff {

	public JFrame frame;
	private JTextField textStaffName;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textAddress;
	private JTextField textPosition;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff window = new Staff();
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
	public Staff() {
		initialize();
		Connect();
		Staff_Load();
	}
	
	Connection connect;
	PreparedStatement prepareState;
	ResultSet resultSet;
	DefaultTableModel dTableModel;
	private JTextField textStaffNumber;
	
	public void Connect()
	{
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmanagement", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Staff_Load() 
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from staff");
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
					vector.add(resultSet.getString("staffname"));
					vector.add(resultSet.getString("staffnumber"));
					vector.add(resultSet.getString("dateofbirth"));
					vector.add(resultSet.getString("gender"));
					vector.add(resultSet.getString("email"));
					vector.add(resultSet.getString("phone"));
					vector.add(resultSet.getString("address"));
					vector.add(resultSet.getString("department"));
					vector.add(resultSet.getString("position"));
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
		frame.setBounds(100, 100, 1130, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblStaffManagement = new JLabel("Staff Management");
		lblStaffManagement.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblStaffManagement.setBounds(361, 11, 241, 56);
		frame.getContentPane().add(lblStaffManagement);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(45, 78, 342, 460);
		frame.getContentPane().add(panel);
		
		JLabel lblStaffName = new JLabel("Staff Name");
		lblStaffName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStaffName.setBounds(18, 32, 107, 24);
		panel.add(lblStaffName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateOfBirth.setBounds(18, 75, 99, 24);
		panel.add(lblDateOfBirth);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGender.setBounds(15, 122, 99, 24);
		panel.add(lblGender);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(17, 171, 99, 24);
		panel.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(17, 217, 99, 24);
		panel.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(18, 262, 99, 24);
		panel.add(lblAddress);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(18, 307, 99, 24);
		panel.add(lblDepartment);
		
		textStaffName = new JTextField();
		textStaffName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStaffName.setColumns(10);
		textStaffName.setBounds(146, 29, 180, 31);
		panel.add(textStaffName);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(146, 166, 180, 31);
		panel.add(textEmail);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPhone.setColumns(10);
		textPhone.setBounds(146, 214, 180, 31);
		panel.add(textPhone);
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAddress.setColumns(10);
		textAddress.setBounds(148, 259, 180, 31);
		panel.add(textAddress);
		
		JDateChooser dateOfBirth = new JDateChooser();
		dateOfBirth.setBounds(146, 75, 180, 31);
		panel.add(dateOfBirth);
		
		JComboBox cbGender = new JComboBox();
		cbGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cbGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbGender.setBounds(146, 121, 180, 31);
		panel.add(cbGender);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"Student Affairs", "Tranning", "Financial - Planning", "Science - Technology", "Information - Communication", "Testing - Quality Assurance"}));
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setBounds(148, 304, 180, 31);
		panel.add(cbDepartment);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPosition.setBounds(17, 356, 107, 24);
		panel.add(lblPosition);
		
		textPosition = new JTextField();
		textPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPosition.setColumns(10);
		textPosition.setBounds(148, 349, 180, 31);
		panel.add(textPosition);
		
		JLabel lblStaffnumber = new JLabel("StaffNumber");
		lblStaffnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStaffnumber.setBounds(18, 403, 107, 24);
		panel.add(lblStaffnumber);
		
		textStaffNumber = new JTextField();
		textStaffNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStaffNumber.setColumns(10);
		textStaffNumber.setBounds(146, 396, 180, 31);
		panel.add(textStaffNumber);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(412, 78, 664, 460);
		frame.getContentPane().add(scrollPane);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String staffname = textStaffName.getText();
					String staffnumber = textStaffNumber.getText();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dateofbirth = dateFormat.format(dateOfBirth.getDate());
					
					String gender = cbGender.getSelectedItem().toString();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					String address = textAddress.getText();
					String department = cbDepartment.getSelectedItem().toString();
					String position = textPosition.getText();
					
					prepareState = connect.prepareStatement("insert into staff(staffname,staffnumber,dateofbirth,gender,email,phone,address,department,position)values(?,?,?,?,?,?,?,?,?)");
					prepareState.setString(1, staffname);
					prepareState.setString(2, staffnumber);
					prepareState.setString(3, dateofbirth);
					prepareState.setString(4, gender);
					prepareState.setString(5, email);
					prepareState.setString(6, phone);
					prepareState.setString(7, address);
					prepareState.setString(8, department);
					prepareState.setString(9, position);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Staff Added");
					
					textStaffName.setText("");
					textStaffNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbDepartment.setSelectedIndex(-1);
					textPosition.setText("");
					
					textStaffName.requestFocus();
					Staff_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(45, 571, 96, 42);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
					String staffname = textStaffName.getText();
					String staffnumber = textStaffNumber.getText();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String dateofbirth = dateFormat.format(dateOfBirth.getDate());
					
					String gender = cbGender.getSelectedItem().toString();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					String address = textAddress.getText();
					String department = cbDepartment.getSelectedItem().toString();
					String position = textPosition.getText();
					
					prepareState = connect.prepareStatement("update staff set staffname=?,staffnumber=?,dateofbirth=?,gender=?,email=?,phone=?,address=?,department=?,position=? where id=?");
					prepareState.setString(1, staffname);
					prepareState.setString(2, staffnumber);
					prepareState.setString(3, dateofbirth);
					prepareState.setString(4, gender);
					prepareState.setString(5, email);
					prepareState.setString(6, phone);
					prepareState.setString(7, address);
					prepareState.setString(8, department);
					prepareState.setString(9, position);
					prepareState.setString(10, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Staff Edited");
					btnSave.setEnabled(true);
					
					textStaffName.setText("");
					textStaffNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbDepartment.setSelectedIndex(-1);
					textPosition.setText("");
					
					textStaffName.requestFocus();
					Staff_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(159, 571, 96, 42);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				textStaffName.setText("");
				textStaffNumber.setText("");
				dateOfBirth.setCalendar(null);
				cbGender.setSelectedIndex(-1);
				textEmail.setText("");
				textPhone.setText("");
				textAddress.setText("");
				cbDepartment.setSelectedIndex(-1);
				textPosition.setText("");
				
				textStaffName.requestFocus();
				Staff_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(277, 571, 96, 42);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelele = new JButton("Delele");
		btnDelele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
															
					prepareState = connect.prepareStatement("delete from staff where id = ?");
					prepareState.setString(1, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Staff Deleted");
					btnSave.setEnabled(true);
					
					textStaffName.setText("");
					textStaffNumber.setText("");
					dateOfBirth.setCalendar(null);
					cbGender.setSelectedIndex(-1);
					textEmail.setText("");
					textPhone.setText("");
					textAddress.setText("");
					cbDepartment.setSelectedIndex(-1);
					textPosition.setText("");
					
					textStaffName.requestFocus();
					Staff_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelele.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelele.setBounds(395, 571, 96, 42);
		frame.getContentPane().add(btnDelele);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(568, 571, 114, 42);
		frame.getContentPane().add(btnClose);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dTableModel = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String id = dTableModel.getValueAt(selectedIndex, 0).toString();
				textStaffName.setText(dTableModel.getValueAt(selectedIndex, 1).toString());
				textStaffNumber.setText(dTableModel.getValueAt(selectedIndex, 2).toString());
		
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
				textPosition.setText(dTableModel.getValueAt(selectedIndex, 9).toString());
				
				btnSave.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Staff Name", "Staff Number", "Date of Birth", "Gender", "Email", "Phone", "Address", "Department", "Position"
			}
		));
		
	}

}
