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
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JPanel;
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

public class Exam {

	public JFrame frame;
	private JTextField textExamName;
	private JTable table;
	JComboBox cbClassName = new JComboBox();
	JComboBox cbSubject = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exam window = new Exam();
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
	public Exam() {
		initialize();
		Connect();
		Load_Class();
		Load_Subject();
		Exam_Load();
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
	
	public void Load_Subject()
	{
		try {
		    prepareState = connect.prepareStatement("SELECT DISTINCT subjectname FROM subject");
		    resultSet = prepareState.executeQuery();

		    cbSubject.removeAllItems();

		    while (resultSet.next()) {
		    	cbSubject.addItem(resultSet.getString("subjectname"));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public void Exam_Load() 
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from exam");
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
					vector.add(resultSet.getString("examname"));
					vector.add(resultSet.getString("term"));
					vector.add(resultSet.getString("date"));
					vector.add(resultSet.getString("time"));
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
		frame.setBounds(100, 100, 1279, 724);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblExamManagement = new JLabel("Exam Management");
		lblExamManagement.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblExamManagement.setBounds(520, 11, 244, 56);
		frame.getContentPane().add(lblExamManagement);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(43, 78, 309, 514);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Exam Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 37, 96, 33);
		panel.add(lblNewLabel);
		
		JLabel lblTerm = new JLabel("Term");
		lblTerm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerm.setBounds(10, 99, 96, 33);
		panel.add(lblTerm);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(10, 171, 96, 33);
		panel.add(lblDate);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTime.setBounds(10, 241, 96, 33);
		panel.add(lblTime);
		
		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClassName.setBounds(10, 309, 96, 33);
		panel.add(lblClassName);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(10, 378, 96, 33);
		panel.add(lblDepartment);
		
		textExamName = new JTextField();
		textExamName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textExamName.setBounds(116, 39, 179, 33);
		panel.add(textExamName);
		textExamName.setColumns(10);
		
		JLabel lblDepartment_1 = new JLabel("Subject");
		lblDepartment_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment_1.setBounds(10, 447, 96, 33);
		panel.add(lblDepartment_1);
		
		JDateChooser Date = new JDateChooser();
		Date.setBounds(116, 171, 179, 33);
		panel.add(Date);
		
		JComboBox cbTerm = new JComboBox();
		cbTerm.setModel(new DefaultComboBoxModel(new String[] {"1st Mid-term", "1st Term", "2nd Mid-term", "2nd Term", "3rd Mid-term", "3rd Term"}));
		cbTerm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTerm.setBounds(116, 101, 179, 33);
		panel.add(cbTerm);
		
		JComboBox cbTime = new JComboBox();
		cbTime.setModel(new DefaultComboBoxModel(new String[] {"7h45 - 8h45", "9h55 - 10h55", "13h30 - 15h", "15h40 - 16h40"}));
		cbTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTime.setBounds(116, 241, 179, 33);
		panel.add(cbTime);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"Mathematics - Computer Science", "Physical - Technical physics", "Chemistry", "Biology - Biotechnology", "Electronics - Telecommunication", "Technology", "Geological", "Materials Science - Technology", "Environment"}));
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setBounds(116, 378, 179, 33);
		panel.add(cbDepartment);
		
		cbClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbClassName.setBounds(116, 309, 179, 33);
		panel.add(cbClassName);
		
		cbSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbSubject.setBounds(116, 447, 179, 33);
		panel.add(cbSubject);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String examname = textExamName.getText();
					String term = cbTerm.getSelectedItem().toString();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String date = dateFormat.format(Date.getDate());
					
					String time = cbTime.getSelectedItem().toString();
					String classname = cbClassName.getSelectedItem().toString();
					String department = cbDepartment.getSelectedItem().toString();
					String subject = cbSubject.getSelectedItem().toString();
					
					
					prepareState = connect.prepareStatement("insert into exam(examname,term,date,time,classname,department,subject)values(?,?,?,?,?,?,?)");
					prepareState.setString(1, examname);
					prepareState.setString(2, term);
					prepareState.setString(3, date);
					prepareState.setString(4, time);
					prepareState.setString(5, classname);
					prepareState.setString(6, department);
					prepareState.setString(7, subject);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Exam Add");
					
					textExamName.setText("");
					cbTerm.setSelectedIndex(-1);
					Date.setCalendar(null);
					cbTime.setSelectedIndex(-1);
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					cbSubject.setSelectedIndex(-1);
					
					textExamName.requestFocus();
					Exam_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(43, 618, 108, 45);
		frame.getContentPane().add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				textExamName.setText("");
				cbTerm.setSelectedIndex(-1);
				Date.setCalendar(null);
				cbTime.setSelectedIndex(-1);
				cbClassName.setSelectedIndex(-1);
				cbDepartment.setSelectedIndex(-1);
				cbSubject.setSelectedIndex(-1);
				
				textExamName.requestFocus();
				Exam_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(161, 618, 108, 45);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
															
					prepareState = connect.prepareStatement("delete from exam where id = ?");
					prepareState.setString(1, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Class Deleted");
					btnSave.setEnabled(true);
					
					textExamName.setText("");
					cbTerm.setSelectedIndex(-1);
					Date.setCalendar(null);
					cbTime.setSelectedIndex(-1);
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					cbSubject.setSelectedIndex(-1);
					
					textExamName.requestFocus();
					Exam_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(279, 618, 108, 45);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(397, 618, 108, 45);
		frame.getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(389, 78, 836, 514);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Exam Name", "Term", "Date", "Time", "Class Name", "Department", "Subject"
			}
		) {
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
	}
}
