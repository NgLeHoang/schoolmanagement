package schoolmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Schedule {

	public JFrame frame;
	private JTable table;
	JComboBox cbTeacherName = new JComboBox();
	JComboBox cbClassName = new JComboBox();
	JComboBox cbSubject = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Schedule window = new Schedule();
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
	public Schedule() {
		initialize();
		Connect();
		Load_Teacher_Name();
		Load_Class();
		Load_Subject();
		Schedule_Load();
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
	
	public void Load_Teacher_Name()
	{
		try {
		    prepareState = connect.prepareStatement("SELECT DISTINCT teachername FROM teacher");
		    resultSet = prepareState.executeQuery();

		    cbTeacherName.removeAllItems();

		    while (resultSet.next()) {
		    	cbTeacherName.addItem(resultSet.getString("teachername"));
		    }
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
		frame.setBounds(100, 100, 1157, 626);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(24, 64, 309, 462);
		frame.getContentPane().add(panel);
		
		JLabel lblTeacherName = new JLabel("Teacher Name");
		lblTeacherName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeacherName.setBounds(10, 37, 104, 33);
		panel.add(lblTeacherName);
		
		JLabel lblTerm = new JLabel("Term");
		lblTerm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerm.setBounds(11, 86, 96, 33);
		panel.add(lblTerm);
		
		JLabel lblDateStart = new JLabel("Date Start");
		lblDateStart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateStart.setBounds(10, 141, 96, 33);
		panel.add(lblDateStart);
		
		JLabel lblPeriodStart = new JLabel("Period Start");
		lblPeriodStart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPeriodStart.setBounds(10, 193, 96, 33);
		panel.add(lblPeriodStart);
		
		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClassName.setBounds(11, 296, 96, 33);
		panel.add(lblClassName);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(11, 345, 96, 33);
		panel.add(lblDepartment);
		
		JLabel lblDepartment_1 = new JLabel("Subject");
		lblDepartment_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment_1.setBounds(12, 393, 96, 33);
		panel.add(lblDepartment_1);
		
		JDateChooser Date = new JDateChooser();
		Date.setBounds(116, 141, 179, 33);
		panel.add(Date);
		
		JComboBox cbTerm = new JComboBox();
		cbTerm.setModel(new DefaultComboBoxModel(new String[] {"1st Term", "2nd Term", "3rd Term"}));
		cbTerm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTerm.setBounds(116, 87, 179, 33);
		panel.add(cbTerm);
		
		JComboBox cbPeriodStart = new JComboBox();
		cbPeriodStart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "2.5", "3", "4", "5", "6", "7", "7.5", "8", "9"}));
		cbPeriodStart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbPeriodStart.setBounds(116, 193, 179, 33);
		panel.add(cbPeriodStart);
		
		cbClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbClassName.setBounds(116, 296, 179, 33);
		panel.add(cbClassName);
		
		JComboBox cbPeriodEnd = new JComboBox();
		cbPeriodEnd.setModel(new DefaultComboBoxModel(new String[] {"2", "2.5", "3", "4", "5", "6", "7", "7.5", "8", "9", "10"}));
		cbPeriodEnd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbPeriodEnd.setBounds(116, 246, 179, 33);
		panel.add(cbPeriodEnd);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"Mathematics - Computer Science", "Physical - Technical physics", "Chemistry", "Biology - Biotechnology", "Electronics - Telecommunication", "Technology", "Geological", "Materials Science - Technology", "Environment"}));
		cbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDepartment.setBounds(116, 345, 179, 33);
		panel.add(cbDepartment);
		
		cbTeacherName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTeacherName.setBounds(116, 37, 179, 33);
		panel.add(cbTeacherName);
		
		JLabel lblPeriodEnd = new JLabel("Period End");
		lblPeriodEnd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPeriodEnd.setBounds(10, 246, 96, 33);
		panel.add(lblPeriodEnd);
		
		cbSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbSubject.setBounds(115, 393, 179, 33);
		panel.add(cbSubject);
		
		JLabel lblScheduleManagement = new JLabel("Schedule Management");
		lblScheduleManagement.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblScheduleManagement.setBounds(528, 4, 286, 56);
		frame.getContentPane().add(lblScheduleManagement);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String teachername = cbTeacherName.getSelectedItem().toString();
					String term = cbTerm.getSelectedItem().toString();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String datestart = dateFormat.format(Date.getDate());
					
					String periodstart = cbPeriodStart.getSelectedItem().toString();
					String periodend = cbPeriodEnd.getSelectedItem().toString();
					String classname = cbClassName.getSelectedItem().toString();
					String department = cbDepartment.getSelectedItem().toString();
					String subject = cbSubject.getSelectedItem().toString();
					
					prepareState = connect.prepareStatement("insert into schedule(teachername,term,datestart,periodstart,periodend,classname,department,subject)values(?,?,?,?,?,?,?,?)");
					prepareState.setString(1, teachername);
					prepareState.setString(2, term);
					prepareState.setString(3, datestart);
					prepareState.setString(4, periodstart);
					prepareState.setString(5, periodend);
					prepareState.setString(6, classname);
					prepareState.setString(7, department);
					prepareState.setString(8, subject);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Schedule Added");
					
					cbTeacherName.setSelectedIndex(-1);
					cbTerm.setSelectedIndex(-1);
					Date.setCalendar(null);
					cbPeriodStart.setSelectedIndex(-1);
					cbPeriodEnd.setSelectedIndex(-1);
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					cbSubject.setSelectedIndex(-1);
					
					Schedule_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(25, 534, 96, 42);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
					String teachername = cbTeacherName.getSelectedItem().toString();
					String term = cbTerm.getSelectedItem().toString();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String datestart = dateFormat.format(Date.getDate());
					
					String periodstart = cbPeriodStart.getSelectedItem().toString();
					String periodend = cbPeriodEnd.getSelectedItem().toString();
					String classname = cbClassName.getSelectedItem().toString();
					String department = cbDepartment.getSelectedItem().toString();
					String subject = cbSubject.getSelectedItem().toString();
					
					prepareState = connect.prepareStatement("update schedule set teachername=?,term=?,datestart=?,periodstart=?,periodend=?,classname=?,department=?,subject=? where id=?");
					prepareState.setString(1, teachername);
					prepareState.setString(2, term);
					prepareState.setString(3, datestart);
					prepareState.setString(4, periodstart);
					prepareState.setString(5, periodend);
					prepareState.setString(6, classname);
					prepareState.setString(7, department);
					prepareState.setString(8, subject);
					prepareState.setString(9, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Schedule Edited");
					btnSave.setEnabled(true);
					
					cbTeacherName.setSelectedIndex(-1);
					cbTerm.setSelectedIndex(-1);
					Date.setCalendar(null);
					cbPeriodStart.setSelectedIndex(-1);
					cbPeriodEnd.setSelectedIndex(-1);
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					cbSubject.setSelectedIndex(-1);
					
					Schedule_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(140, 534, 96, 42);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				
				cbTeacherName.setSelectedIndex(-1);
				cbTerm.setSelectedIndex(-1);
				Date.setCalendar(null);
				cbPeriodStart.setSelectedIndex(-1);
				cbPeriodEnd.setSelectedIndex(-1);
				cbClassName.setSelectedIndex(-1);
				cbDepartment.setSelectedIndex(-1);
				cbSubject.setSelectedIndex(-1);
				
				Schedule_Load();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(254, 532, 96, 42);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelele = new JButton("Delele");
		btnDelele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dTableModel = (DefaultTableModel)table.getModel();
					int selectedIndex = table.getSelectedRow();
					
					String id = dTableModel.getValueAt(selectedIndex, 0).toString();
															
					prepareState = connect.prepareStatement("delete from schedule where id = ?");
					prepareState.setString(1, id);
					
					prepareState.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Schedule Deleted");
					btnSave.setEnabled(true);
					
					cbTeacherName.setSelectedIndex(-1);
					cbTerm.setSelectedIndex(-1);
					Date.setCalendar(null);
					cbPeriodStart.setSelectedIndex(-1);
					cbPeriodEnd.setSelectedIndex(-1);
					cbClassName.setSelectedIndex(-1);
					cbDepartment.setSelectedIndex(-1);
					cbSubject.setSelectedIndex(-1);
					
					Schedule_Load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelele.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelele.setBounds(367, 532, 96, 42);
		frame.getContentPane().add(btnDelele);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(526, 530, 114, 42);
		frame.getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(362, 66, 760, 455);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dTableModel = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				String id = dTableModel.getValueAt(selectedIndex, 0).toString();
				cbTeacherName.setSelectedItem(dTableModel.getValueAt(selectedIndex, 1).toString());
				cbTerm.setSelectedItem(dTableModel.getValueAt(selectedIndex, 2).toString());
				
				try {
					Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String)dTableModel.getValueAt(selectedIndex, 3));
					Date.setDate(date);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				cbPeriodStart.setSelectedItem(dTableModel.getValueAt(selectedIndex, 4).toString());
				cbPeriodEnd.setSelectedItem(dTableModel.getValueAt(selectedIndex, 5).toString());
				cbClassName.setSelectedItem(dTableModel.getValueAt(selectedIndex, 6).toString());
				cbDepartment.setSelectedItem(dTableModel.getValueAt(selectedIndex, 7).toString());
				cbSubject.setSelectedItem(dTableModel.getValueAt(selectedIndex, 8).toString());
				
				btnSave.setEnabled(false);
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Teacher Name", "Term", "Date Start", "Period Start", "Period End", "Class Name", "Department", "Subject"
			}
		));
	}

}
