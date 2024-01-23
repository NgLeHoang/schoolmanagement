package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	public JFrame frame;
	private JLabel labelName = new JLabel("New label");
	private JLabel labelUserType = new JLabel("New label");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}
	
	int ID;
	String userName;
	String userType;

	public Main(int id, String username, String usertype) {
		initialize();
		this.userName = username;
		labelName.setText(userName);
		
		this.userType = usertype;
		labelUserType.setText(usertype);
		
		this.ID = id;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 863, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(488, 39, 322, 159);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		labelUserType.setBounds(198, 102, 99, 26);
		panel.add(labelUserType);
		
		labelUserType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelName.setBounds(199, 32, 69, 20);
		panel.add(labelName);
		
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(24, 29, 99, 26);
		panel.add(lblNewLabel);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setForeground(new Color(255, 128, 128));
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserType.setBounds(24, 102, 99, 26);
		panel.add(lblUserType);
		
		JLabel lblNewLabel_1 = new JLabel("School Management System Main");
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(26, 37, 442, 60);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(26, 108, 429, 423);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnClass = new JButton("Class");
		btnClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Class classForm = new Class();
				classForm.frame.setVisible(true);
			}
		});
		btnClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClass.setBounds(256, 32, 120, 54);
		panel_1.add(btnClass);
		
		JButton btnStaff = new JButton("Staff");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff staffForm = new Staff();
				staffForm.frame.setVisible(true);
			}
		});
		btnStaff.setForeground(new Color(255, 128, 0));
		btnStaff.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStaff.setBounds(55, 32, 120, 54);
		panel_1.add(btnStaff);
		
		JButton btnSubject = new JButton("Subject");
		btnSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subject subjectForm = new Subject();
				subjectForm.frame.setVisible(true);
			}
		});
		btnSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSubject.setBounds(256, 104, 120, 54);
		panel_1.add(btnSubject);
		
		JButton btnSchedule = new JButton("Schedule");
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Schedule scheduleForm = new Schedule();
				scheduleForm.frame.setVisible(true);
			}
		});
		btnSchedule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSchedule.setBounds(256, 179, 120, 54);
		panel_1.add(btnSchedule);
		
		JButton btnTeacher = new JButton("Teacher");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherRegistration teacherRegistration = new TeacherRegistration();
				teacherRegistration.frame.setVisible(true);
			}
		});
		btnTeacher.setForeground(new Color(255, 128, 0));
		btnTeacher.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTeacher.setBounds(55, 128, 120, 54);
		panel_1.add(btnTeacher);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRegistration studentForm = new StudentRegistration();
				studentForm.frame.setVisible(true);
			}
		});
		btnStudent.setForeground(new Color(255, 128, 0));
		btnStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStudent.setBounds(55, 227, 120, 54);
		panel_1.add(btnStudent);
		
		JButton btnUserCreation = new JButton("User Creation");
		btnUserCreation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User userForm = new User();
				userForm.frame.setVisible(true);
			}
		});
		btnUserCreation.setForeground(new Color(255, 128, 0));
		btnUserCreation.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUserCreation.setBounds(43, 322, 147, 54);
		panel_1.add(btnUserCreation);
		
		JButton btnMark = new JButton("Mark");
		btnMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mark markForm = new Mark();
				markForm.frame.setVisible(true);
			}
		});
		btnMark.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMark.setBounds(256, 332, 120, 54);
		panel_1.add(btnMark);
		
		JButton btnExam = new JButton("Exam");
		btnExam.setBounds(256, 256, 120, 54);
		panel_1.add(btnExam);
		btnExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exam examForm = new Exam();
				examForm.frame.setVisible(true);
			}
		});
		btnExam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Login loginForm = new Login();
				loginForm.frame.setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(701, 477, 120, 54);
		frame.getContentPane().add(btnLogout);
	}
}
