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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Class {

	public JFrame frame;
	private JTextField textClassName;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class window = new Class();
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
	public Class() {
		initialize();
		Connect();
		Class_Load();
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
	
	public void Class_Load ()
	{
		int colums;
		try {
			prepareState = connect.prepareStatement("select * from class");
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
					vector.add(resultSet.getString("classname"));
					vector.add(resultSet.getString("department"));
					vector.add(resultSet.getString("academicyear"));
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
		frame.setBounds(100, 100, 760, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Class Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(235, 35, 250, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(36, 119, 315, 315);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Class Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 46, 101, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Department");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 133, 101, 36);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Academic Year");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(10, 220, 115, 36);
		panel.add(lblNewLabel_1_2);
		
		textClassName = new JTextField();
		textClassName.setBounds(121, 46, 158, 29);
		panel.add(textClassName);
		textClassName.setColumns(10);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"Mathematics - Computer Science", "Physical - Technical physics", "Chemistry", "Biology - Biotechnology", "Electronics - Telecommunication", "Technology", "Geological", "Materials Science - Technology", "Environment"}));
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setBounds(121, 133, 158, 29);
		panel.add(cbDepartment);
		
		JComboBox cbAcademicYear = new JComboBox();
		cbAcademicYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbAcademicYear.setModel(new DefaultComboBoxModel(new String[] {"2017", "2018", "2019", "2020", "2021", "2022", "2023"}));
		cbAcademicYear.setBounds(121, 220, 158, 29);
		panel.add(cbAcademicYear);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String classname = textClassName.getText();
					String department = cbDepartment.getSelectedItem().toString();
					String academicyear = cbAcademicYear.getSelectedItem().toString();
					
					prepareState = connect.prepareStatement("insert into class(classname,department,academicyear)values(?,?,?)");
					prepareState.setString(1, classname);
					prepareState.setString(2, department);
					prepareState.setString(3, academicyear);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Class Add");
					
					textClassName.setText("");
					cbDepartment.setSelectedIndex(-1);
					cbAcademicYear.setSelectedIndex(-1);
					textClassName.requestFocus();
					Class_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(36, 462, 108, 45);
		frame.getContentPane().add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
															
					prepareState = connect.prepareStatement("delete from class where id = ?");
					prepareState.setString(1, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Class Deleted");
					btnSave.setEnabled(true);
					
					textClassName.setText("");
					cbDepartment.setSelectedIndex(-1);
					cbAcademicYear.setSelectedIndex(-1);
					
					textClassName.requestFocus();
					Class_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(296, 462, 108, 45);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(425, 462, 108, 45);
		frame.getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 119, 327, 315);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Class Name", "Department", "Academic Year"
			}
		));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				textClassName.setText("");
				cbDepartment.setSelectedIndex(-1);
				cbAcademicYear.setSelectedIndex(-1);
				
				textClassName.requestFocus();
				Class_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(165, 462, 108, 45);
		frame.getContentPane().add(btnClear);
	}
}
