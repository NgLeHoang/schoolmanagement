package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mark {

	public JFrame frame;
	private JTextField textStudentNumber;
	private JTextField textStudentName;
	JComboBox cbSubject = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mark window = new Mark();
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
	public Mark() {
		initialize();
		Connect();
		Load_Subject();
		Mark_Load();
	}
	
	Connection connect;
	PreparedStatement prepareState;
	ResultSet resultSet;
	DefaultTableModel dTableModel;
	private JTextField textMark;
	private JTable table;
	
	public void Connect()
	{
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmanagement", "root", "");
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
	
	public void Mark_Load() 
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from mark");
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
					vector.add(resultSet.getString("studentnumber"));
					vector.add(resultSet.getString("studentname"));
					vector.add(resultSet.getString("classname"));
					vector.add(resultSet.getString("department"));
					vector.add(resultSet.getString("subject"));
					vector.add(resultSet.getString("term"));
					vector.add(resultSet.getString("mark"));
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
		frame.setBounds(100, 100, 1101, 658);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(28, 65, 328, 465);
		frame.getContentPane().add(panel);
		
		JLabel lblStudentNumber = new JLabel("Student Number");
		lblStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudentNumber.setBounds(10, 37, 115, 33);
		panel.add(lblStudentNumber);
		
		JLabel lblTerm = new JLabel("Student Name");
		lblTerm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerm.setBounds(10, 99, 115, 33);
		panel.add(lblTerm);
		
		JLabel lblTerm_1 = new JLabel("Term");
		lblTerm_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerm_1.setBounds(10, 335, 96, 33);
		panel.add(lblTerm_1);
		
		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClassName.setBounds(10, 160, 96, 33);
		panel.add(lblClassName);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(10, 219, 96, 33);
		panel.add(lblDepartment);
		
		JLabel lblDepartment_1 = new JLabel("Subject");
		lblDepartment_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment_1.setBounds(10, 277, 96, 33);
		panel.add(lblDepartment_1);
		
		JComboBox cbTerm = new JComboBox();
		cbTerm.setModel(new DefaultComboBoxModel(new String[] {"1st Mid-term", "1st Term", "2nd Mid-term", "2nd Term", "3rd Mid-term", "3rd Term"}));
		cbTerm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTerm.setBounds(156, 335, 158, 33);
		panel.add(cbTerm);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setBounds(156, 219, 158, 33);
		panel.add(cbDepartment);
		
		JComboBox cbClassName = new JComboBox();
		cbClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbClassName.setBounds(156, 160, 158, 33);
		panel.add(cbClassName);
		
		cbSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbSubject.setBounds(156, 277, 158, 33);
		panel.add(cbSubject);
		
		textStudentNumber = new JTextField();
		textStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStudentNumber.setBounds(156, 37, 158, 33);
		panel.add(textStudentNumber);
		textStudentNumber.setColumns(10);
		
		textStudentName = new JTextField();
		textStudentName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStudentName.setColumns(10);
		textStudentName.setBounds(156, 99, 158, 33);
		panel.add(textStudentName);
		
		JLabel lblTerm_1_1 = new JLabel("Mark");
		lblTerm_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerm_1_1.setBounds(10, 397, 96, 33);
		panel.add(lblTerm_1_1);
		
		textMark = new JTextField();
		textMark.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textMark.setColumns(10);
		textMark.setBounds(156, 397, 158, 33);
		panel.add(textMark);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					String studentnum = textStudentNumber.getText();
					
					prepareState = connect.prepareStatement("select * from "
							+ "student where studentnumber = ?");
					prepareState.setString(1, studentnum);
					resultSet = prepareState.executeQuery();
					
					if (resultSet.next() == false)
					{
						JOptionPane.showMessageDialog(frame, "Student number not found");
						textStudentName.setText("");
					}
					else {
						String name = resultSet.getString("studentname");
						textStudentName.setText(name.trim());
						
						String classname = resultSet.getString("classname");
						cbClassName.addItem(classname);
						
						String department = resultSet.getString("department");
						cbDepartment.addItem(department);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(366, 89, 99, 48);
		frame.getContentPane().add(btnSearch);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String studentnumber = textStudentNumber.getText();
					String studentname = textStudentName.getText();
					String classname = cbClassName.getSelectedItem().toString();
					String department = cbDepartment.getSelectedItem().toString();
					String subject = cbSubject.getSelectedItem().toString();
					String term = cbTerm.getSelectedItem().toString();
					String mark = textMark.getText();
					
					prepareState = connect.prepareStatement("insert into mark(studentnumber,studentname,classname,department,subject,term,mark)values(?,?,?,?,?,?,?)");
					prepareState.setString(1, studentnumber);
					prepareState.setString(2, studentname);
					prepareState.setString(3, classname);
					prepareState.setString(4, department);
					prepareState.setString(5, subject);
					prepareState.setString(6, term);
					prepareState.setString(7, mark);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Mark Added");
					
					textStudentNumber.setText("");
					textStudentName.setText("");
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					cbSubject.setSelectedIndex(-1);
					cbTerm.setSelectedIndex(-1);
					textMark.setText("");
					
					textStudentNumber.requestFocus();
					Mark_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(28, 552, 96, 42);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
					String studentnumber = textStudentNumber.getText();
					String studentname = textStudentName.getText();
					String classname = cbClassName.getSelectedItem().toString();
					String department = cbDepartment.getSelectedItem().toString();
					String subject = cbSubject.getSelectedItem().toString();
					String term = cbTerm.getSelectedItem().toString();
					String mark = textMark.getText();
					
					prepareState = connect.prepareStatement("update mark set studentnumber=?,studentname=?,classname=?,department=?,subject=?,term=?,mark=? where id=?");
					prepareState.setString(1, studentnumber);
					prepareState.setString(2, studentname);
					prepareState.setString(3, classname);
					prepareState.setString(4, department);
					prepareState.setString(5, subject);
					prepareState.setString(6, term);
					prepareState.setString(7, mark);
					prepareState.setString(8, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Mark Edited");
					btnSave.setEnabled(true);
					
					textStudentNumber.setText("");
					textStudentName.setText("");
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					cbSubject.setSelectedIndex(-1);
					cbTerm.setSelectedIndex(-1);
					textMark.setText("");
					
					textStudentNumber.requestFocus();
					Mark_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(134, 552, 96, 42);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				textStudentNumber.setText("");
				textStudentName.setText("");
				cbClassName.setSelectedIndex(-1);
				cbDepartment.setSelectedIndex(-1);
				cbSubject.setSelectedIndex(-1);
				cbTerm.setSelectedIndex(-1);
				textMark.setText("");
				
				textStudentNumber.requestFocus();
				Mark_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(240, 552, 96, 42);
		frame.getContentPane().add(btnClear);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(526, 552, 114, 42);
		frame.getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(486, 65, 566, 465);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dTableModel = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String id = dTableModel.getValueAt(selectedIndex, 0).toString();
				textStudentNumber.setText(dTableModel.getValueAt(selectedIndex, 1).toString());	
				textStudentName.setText(dTableModel.getValueAt(selectedIndex, 2).toString());	
				cbClassName.setSelectedItem(dTableModel.getValueAt(selectedIndex, 3).toString());
				cbDepartment.setSelectedItem(dTableModel.getValueAt(selectedIndex, 4).toString());
				cbTerm.setSelectedItem(dTableModel.getValueAt(selectedIndex, 5).toString());
				cbSubject.setSelectedItem(dTableModel.getValueAt(selectedIndex, 6).toString());
				textMark.setText(dTableModel.getValueAt(selectedIndex, 7).toString());
				
				btnSave.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Student Number", "Student Name", "Class Name", "Department", "Subject", "Term", "Mark"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMark = new JLabel("Mark Management");
		lblMark.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblMark.setBounds(335, 11, 244, 33);
		frame.getContentPane().add(lblMark);
	}
}
