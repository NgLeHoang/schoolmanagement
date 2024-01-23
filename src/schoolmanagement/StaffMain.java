package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StaffMain {

	public JFrame frame;
	JLabel labelUserName = new JLabel("New label");
	JLabel labelUserType = new JLabel("New label");
	JLabel lbName = new JLabel("New label");
	JLabel lbDate = new JLabel("New label");
	JLabel lbGender = new JLabel("New label");
	JLabel lbEmail = new JLabel("New label");
	JLabel lbPhone = new JLabel("New label");
	JLabel lbAddress = new JLabel("New label");
	JLabel lbDepartment = new JLabel("New label");
	JLabel lbPosition = new JLabel("New label");
	JLabel lbStaffNumber = new JLabel("New label");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffMain window = new StaffMain();
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
	public StaffMain() {
		initialize();
		Connect();
	}
	
	int ID;
	String userName;
	String userType;
	
	public StaffMain(int id, String username, String usertype, String number) {
		initialize();
		Connect();
		this.userName = username;
		labelUserName.setText(userName);
		
		this.userType = usertype;
		labelUserType.setText(usertype);
		
		this.ID = id;
		
		displayStaffInfoByNumber(number);
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
	
	private void displayStaffInfoByNumber(String number) {
        try {
            String sql = "SELECT staff.* FROM user INNER JOIN "
            		+ "staff ON user.number = staff.staffnumber WHERE user.number = ?";
            prepareState = connect.prepareStatement(sql);
            prepareState.setString(1, number);

            resultSet = prepareState.executeQuery();

            while (resultSet.next()) {
            	String staffName = resultSet.getString("staffname");
                lbName.setText(staffName);

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

                String position = resultSet.getString("position");
                lbPosition.setText(position);
                
                String staffnumber = resultSet.getString("staffnumber");
                lbStaffNumber.setText(staffnumber);
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
		frame.setBounds(100, 100, 1151, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel_1 = new JLabel("School Management System Of Staff");
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(324, 11, 442, 60);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(36, 104, 373, 459);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnTeacher = new JButton("Teacher");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherRegistration teacherRegistration = new TeacherRegistration();
				teacherRegistration.frame.setVisible(true);
			}
		});
		btnTeacher.setForeground(new Color(255, 128, 0));
		btnTeacher.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTeacher.setBounds(41, 85, 120, 54);
		panel.add(btnTeacher);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRegistration studentRegistration = new StudentRegistration();
				studentRegistration.frame.setVisible(true);
			}
		});
		btnStudent.setForeground(new Color(255, 128, 0));
		btnStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStudent.setBounds(41, 242, 120, 54);
		panel.add(btnStudent);
		
		JButton btnClass = new JButton("Class");
		btnClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Class classForm = new Class();
				classForm.frame.setVisible(true);
			}
		});
		btnClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClass.setBounds(221, 43, 120, 54);
		panel.add(btnClass);
		
		JButton btnSubject = new JButton("Subject");
		btnSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subject subject = new Subject();
				subject.frame.setVisible(true);
			}
		});
		btnSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSubject.setBounds(221, 123, 120, 54);
		panel.add(btnSubject);
		
		JButton btnExam = new JButton("Exam");
		btnExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exam exam = new Exam();
				exam.frame.setVisible(true);
			}
		});
		btnExam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExam.setBounds(221, 284, 120, 54);
		panel.add(btnExam);
		
		JButton btnSchedule = new JButton("Schedule");
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Schedule schedule = new Schedule();
				schedule.frame.setVisible(true);
			}
		});
		btnSchedule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSchedule.setBounds(221, 203, 120, 54);
		panel.add(btnSchedule);
		
		JButton btnMark = new JButton("Mark");
		btnMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mark mark = new Mark();
				mark.frame.setVisible(true);
			}
		});
		btnMark.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMark.setBounds(221, 363, 120, 54);
		panel.add(btnMark);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(437, 220, 245, 158);
		frame.getContentPane().add(panel_1);
	
		labelUserType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelUserType.setBounds(133, 102, 99, 26);
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
		lblUserType.setBounds(24, 102, 99, 26);
		panel_1.add(lblUserType);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Login login = new Login();
				login.frame.setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(506, 493, 120, 54);
		frame.getContentPane().add(btnLogout);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(713, 104, 391, 459);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(56, 33, 84, 24);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Date of Birth");
		lblNewLabel_2_1.setForeground(Color.GREEN);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(56, 79, 100, 24);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Gender");
		lblNewLabel_2_2.setForeground(Color.GREEN);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(56, 125, 84, 24);
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Email");
		lblNewLabel_2_3.setForeground(Color.GREEN);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3.setBounds(56, 173, 84, 24);
		panel_2.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Phone");
		lblNewLabel_2_4.setForeground(Color.GREEN);
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_4.setBounds(56, 220, 84, 24);
		panel_2.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Address");
		lblNewLabel_2_5.setForeground(Color.GREEN);
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_5.setBounds(56, 275, 84, 24);
		panel_2.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("Department");
		lblNewLabel_2_6.setForeground(Color.GREEN);
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_6.setBounds(56, 321, 100, 24);
		panel_2.add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_7 = new JLabel("Position");
		lblNewLabel_2_7.setForeground(Color.GREEN);
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_7.setBounds(56, 371, 84, 24);
		panel_2.add(lblNewLabel_2_7);
		
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbName.setBounds(182, 37, 158, 17);
		panel_2.add(lbName);
		

		lbDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDate.setBounds(182, 83, 113, 17);
		panel_2.add(lbDate);
		

		lbGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbGender.setBounds(182, 129, 91, 17);
		panel_2.add(lbGender);
		

		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbEmail.setBounds(182, 177, 208, 17);
		panel_2.add(lbEmail);
		

		lbPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPhone.setBounds(182, 224, 113, 17);
		panel_2.add(lbPhone);
		

		lbAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbAddress.setBounds(182, 279, 199, 17);
		panel_2.add(lbAddress);
		

		lbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDepartment.setBounds(182, 321, 199, 17);
		panel_2.add(lbDepartment);
		

		lbPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPosition.setBounds(182, 371, 158, 17);
		panel_2.add(lbPosition);
		
		JLabel lblNewLabel_2_7_1 = new JLabel("Staff Number");
		lblNewLabel_2_7_1.setForeground(Color.GREEN);
		lblNewLabel_2_7_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_7_1.setBounds(56, 411, 113, 24);
		panel_2.add(lblNewLabel_2_7_1);
		
		lbStaffNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbStaffNumber.setBounds(182, 415, 158, 17);
		panel_2.add(lbStaffNumber);
		
		JLabel lblNewLabel_1_1 = new JLabel("Staff Information");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(821, 44, 198, 60);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Management");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(146, 44, 154, 60);
		frame.getContentPane().add(lblNewLabel_1_1_1);
	}
}
