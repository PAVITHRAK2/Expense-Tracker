package com.pavithra.expensetracker.ui;

import com.pavithra.expensetracker.service.ExpenseService;
import com.pavithra.expensetracker.service.SalaryService;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public ReportDialog(JFrame parent) {

		super(parent, "Monthly Report", true);

		ExpenseService expenseService = new ExpenseService();
		SalaryService salaryService = new SalaryService();

		setSize(700, 550);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();

		JLabel monthLabel = new JLabel("Month");

		Integer[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		JComboBox<Integer> monthBox = new JComboBox<>(months);

		JButton generateButton = new JButton("Generate");

		topPanel.add(monthLabel);
		topPanel.add(monthBox);
		topPanel.add(generateButton);

		JTextArea reportArea = new JTextArea();

		reportArea.setEditable(false);

		reportArea.setFont(new Font("Monospaced", Font.PLAIN, 15));

		JScrollPane scrollPane = new JScrollPane(reportArea);

		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

		generateButton.addActionListener(e -> {

			int month = (Integer) monthBox.getSelectedItem();

			double salary = salaryService.getMonthlySalary();

			ByteArrayOutputStream output = new ByteArrayOutputStream();

			PrintStream old = System.out;

			System.setOut(new PrintStream(output));

			expenseService.monthlyReport(month, salary);

			System.setOut(old);

			reportArea.setText(output.toString());

		});

	}

}
