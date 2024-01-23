package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentMain {

	public JFrame frame;
	private JTable table;
	JLabel labelUserName = new JLabel("New label");
	JLabel labelUserType = new JLabel("New label");
	JLabel lbName = new JLabel("New label");
	JLabel lbStudentNumber = new JLabel("New label");
	JLabel lbDate = new JLabel("New label");
	JLabel lbGender = new JLabel("New label");
	JLabel lbEmail = new JLabel("New label");
	JLabel lbPhone = new JLabel("New label");
	JLabel lbAddress = new JLabel("New label");
	JLabel lbDepartment = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMain window = new StudentMain();
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
	public StudentMain() {
		initialize();
		Connect();
		Schedule_Load();
	}
	
	int ID;
	String userName;
	String userType;
	
	public StudentMain(int id, String username, String usertype, String number) {
		initialize();
		Connect();
		this.userName = username;
		labelUserName.setText(userName);
		
		this.userType = usertype;
		labelUserType.setText(usertype);
		
		this.ID = id;
		
		displayStudentInfoByNumber(number);
		Schedule_Load();
	}
	
	Connection connect;
	PreparedStatement prepareState;
	ResultSet resultSet;
	DefaultTableModel dTableModel;
	private JTextField textClassName;
	
	public void Connect()
	{
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmanagement", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void displayStudentInfoByNumber(String number) {
        try {
            String sql = "SELECT student.* FROM user INNER JOIN student ON user.number = student.studentnumber WHERE user.number = ?";
            prepareState = connect.prepareStatement(sql);
            prepareState.setString(1, number);

            resultSet = prepareState.executeQuery();

            while (resultSet.next()) {
            	String studentName = resultSet.getString("studentname");
                lbName.setText(studentName);
                
                String studentNumber = resultSet.getString("studentnumber");
                lbStudentNumber.setText(studentNumber);

                String dateOfBirth = resultSet.getString("dateofbirth");
                lbDate.setText(dateOfBirth);

                String gender = resultSet.getString("gender");
                lbGender.setText(gender);

                String email = resultSet.getString("email");
                lbEmail.setText(email);

                String phoneNumber = resultSet.getString("phone");
                lbPhone.setText(phoneNumber);

                String address = resultSet.getString("address");
                lbAddress.setText(address);

                String department = resultSet.getString("department");
                lbDepartment.setText(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void Schedule_Load() 
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from schedule");
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
					vector.add(resultSet.getString("term"));
					vector.add(resultSet.getString("datestart"));
					vector.add(resultSet.getString("periodstart"));
					vector.add(resultSet.getString("periodend"));
					vector.add(resultSet.getString("classname"));
					vector.add(resultSet.getString("department"));
					vector.add(resultSet.getString("subject"));
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
		frame.setBounds(100, 100, 1110, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(23, 402, 245, 124);
		frame.getContentPane().add(panel_1);

		labelUserType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelUserType.setBounds(133, 81, 99, 26);
		panel_1.add(labelUserType);

		labelUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelUserName.setBounds(133, 32, 69, 20);
		panel_1.add(labelUserName);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(24, 29, 99, 26);
		panel_1.add(lblNewLabel);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setForeground(new Color(255, 128, 128));
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserType.setBounds(24, 81, 99, 26);
		panel_1.add(lblUserType);
		
		JLabel lblNewLabel_1 = new JLabel("Student View");
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(462, 8, 177, 60);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(655, 83, 422, 443);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(56, 33, 84, 24);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Date of Birth");
		lblNewLabel_2_1.setForeground(Color.GREEN);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(56, 133, 100, 24);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Gender");
		lblNewLabel_2_2.setForeground(Color.GREEN);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(56, 182, 84, 24);
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Email");
		lblNewLabel_2_3.setForeground(Color.GREEN);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3.setBounds(56, 232, 84, 24);
		panel_2.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Phone");
		lblNewLabel_2_4.setForeground(Color.GREEN);
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_4.setBounds(56, 280, 84, 24);
		panel_2.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Address");
		lblNewLabel_2_5.setForeground(Color.GREEN);
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_5.setBounds(56, 325, 84, 24);
		panel_2.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("Department");
		lblNewLabel_2_6.setForeground(Color.GREEN);
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_6.setBounds(56, 367, 100, 24);
		panel_2.add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_7 = new JLabel("Student Number");
		lblNewLabel_2_7.setForeground(Color.GREEN);
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_7.setBounds(56, 79, 129, 24);
		panel_2.add(lblNewLabel_2_7);

		lbName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbName.setBounds(182, 37, 158, 17);
		panel_2.add(lbName);

		lbDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDate.setBounds(182, 137, 113, 17);
		panel_2.add(lbDate);

		lbGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbGender.setBounds(182, 186, 91, 17);
		panel_2.add(lbGender);

		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbEmail.setBounds(182, 236, 208, 17);
		panel_2.add(lbEmail);

		lbPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPhone.setBounds(182, 284, 113, 17);
		panel_2.add(lbPhone);

		lbAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbAddress.setBounds(182, 329, 199, 17);
		panel_2.add(lbAddress);

		lbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDepartment.setBounds(182, 371, 208, 17);
		panel_2.add(lbDepartment);

		lbStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbStudentNumber.setBounds(185, 84, 158, 17);
		panel_2.add(lbStudentNumber);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 83, 621, 308);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Teacher Name", "Term", "Date Start", "Period Start", "Period End", "Class Name", "Department", "Subject"
			}
		));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String className = textClassName.getText();
				dTableModel.setRowCount(0);
				try {
					String sql = "SELECT * FROM schedule WHERE classname = ?";
					prepareState = connect.prepareStatement(sql);
		            prepareState.setString(1, className);

		            resultSet = prepareState.executeQuery();
		            
		            while (resultSet.next()) {
		            	Vector vector = new Vector();
							vector.add(resultSet.getString("id"));
							vector.add(resultSet.getString("teachername"));
							vector.add(resultSet.getString("term"));
							vector.add(resultSet.getString("datestart"));
							vector.add(resultSet.getString("periodstart"));
							vector.add(resultSet.getString("periodend"));
							vector.add(resultSet.getString("classname"));
							vector.add(resultSet.getString("department"));
							vector.add(resultSet.getString("subject"));
							
							dTableModel.addRow(vector);
		            }
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(311, 410, 89, 45);
		frame.getContentPane().add(btnSearch);
		
		textClassName = new JTextField();
		textClassName.setBounds(432, 419, 130, 31);
		frame.getContentPane().add(textClassName);
		textClassName.setColumns(10);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Login login = new Login();
				login.frame.setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(542, 487, 97, 39);
		frame.getContentPane().add(btnLogout);
		
		
	}
	
}
