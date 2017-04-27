package ex3g;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JList employeeList;
	private JTextField hoursTextField;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private PayrollObjMapper payrollObjMapper;
	private JTextField empIdTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton addHoursButton;
	private JButton clearHoursButton;
	private JButton updateButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("JGroth 2740 Ex3G Payroll");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmpoyee = new JLabel("Select employee:");
		lblSelectEmpoyee.setBounds(21, 11, 106, 14);
		contentPane.add(lblSelectEmpoyee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 36, 298, 103);
		contentPane.add(scrollPane);
		
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "Jon Groth", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Melissa Groth", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Brittany Sera", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Christopher Losinski", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Andrew Losinski", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100):");
		lblEmployeeId.setBounds(21, 159, 147, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee name:");
		lblEmployeeName.setBounds(21, 184, 99, 14);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay rate (7.25 - 100):");
		lblPayRate.setBounds(21, 209, 147, 14);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter hours (0.1 - 20.0):");
		lblEnterHours.setBounds(21, 234, 147, 14);
		contentPane.add(lblEnterHours);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_hoursTextField_focusGained(arg0);
			}
		});
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setText("0.00");
		hoursTextField.setBounds(178, 231, 69, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_addHoursButton_actionPerformed(arg0);
			}
		});
		addHoursButton.setBounds(257, 230, 41, 23);
		contentPane.add(addHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_clearHoursButton_actionPerformed(arg0);
			}
		});
		clearHoursButton.setBounds(308, 230, 69, 23);
		contentPane.add(clearHoursButton);
		
		JLabel lblTotalHours = new JLabel("Total hours:");
		lblTotalHours.setBounds(21, 259, 99, 14);
		contentPane.add(lblTotalHours);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(178, 259, 69, 14);
		contentPane.add(totalHoursLabel);
		
		JLabel lblGrossPay = new JLabel("Gross pay:");
		lblGrossPay.setBounds(21, 284, 99, 14);
		contentPane.add(lblGrossPay);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(178, 284, 69, 14);
		contentPane.add(grossPayLabel);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClearForm_actionPerformed(e);
			}
		});
		btnClearForm.setBounds(257, 316, 106, 23);
		contentPane.add(btnClearForm);
		
		empIdTextField = new JTextField();
		empIdTextField.setText("000");
		empIdTextField.setBounds(178, 156, 68, 20);
		contentPane.add(empIdTextField);
		empIdTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.setBounds(130, 181, 117, 20);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.setText("7.25");
		payRateTextField.setBounds(178, 206, 69, 20);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(158, 316, 89, 23);
		contentPane.add(updateButton);
	}
	
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		this.empIdTextField.setText(Integer.toString(payroll.getId()));
		this.empNameTextField.setText(payroll.getName());
		DecimalFormat dollarFmt = new DecimalFormat("#,##0.00");
		this.payRateTextField.setText(dollarFmt.format(payroll.getPayRate()));
		DecimalFormat hoursFmt = new DecimalFormat("##0.00");
		this.totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
		this.grossPayLabel.setText(dollarFmt.format(payroll.calcGrossPay()));
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();	
		this.addHoursButton.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
	}
	
	protected void do_addHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		double hours = Double.parseDouble(this.hoursTextField.getText());
		
		if (payroll.addHours(hours)) {
			DecimalFormat hoursFmt = new DecimalFormat("##0.00");
			this.totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
			DecimalFormat dollarFmt = new DecimalFormat("$#,##0.00");
			this.grossPayLabel.setText(dollarFmt.format(payroll.calcGrossPay()));
			this.hoursTextField.setText("0.00");
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid hours. \nMust be in range 0.1 - 20.");
		}
		hoursTextField.requestFocus();
	}
	
	protected void do_clearHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		payroll.setHours(0.0);
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
	}
	
	protected void do_btnClearForm_actionPerformed(ActionEvent e) {
		this.empIdTextField.setText("0");
		this.empNameTextField.setText("");
		this.payRateTextField.setText("$0.00");
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection();
		this.addHoursButton.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
	}

	protected void do_hoursTextField_focusGained(FocusEvent arg0) {
		hoursTextField.selectAll();
	}
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(empIdTextField.getText());
		double rate = Double.parseDouble(payRateTextField.getText());
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		if (!payroll.setId(id)) {
			JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
			empIdTextField.setText(Integer.toString(payroll.getId()));
			empIdTextField.requestFocus();
		} 
		else {
			if (!payroll.setName(empNameTextField.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid name. \nName is required.");
				this.empNameTextField.setText(payroll.getName());
				this.payRateTextField.requestFocus();
			}
			else {
				if (!payroll.setPayRate(rate)) {
					JOptionPane.showMessageDialog(null, "Invalid pay rate. \nMust be in range 7.25 - 100.");
					DecimalFormat rateFmt = new DecimalFormat("##0.00");
					this.payRateTextField.setText(rateFmt.format(payroll.getPayRate()));
					this.payRateTextField.requestFocus();
				}
			}
		}
		employeeList.repaint();		
	}
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null)
			payrollObjMapper.writeAllPayroll(employeeListModel);
	}
}
