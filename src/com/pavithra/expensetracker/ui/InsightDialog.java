package com.pavithra.expensetracker.ui;

import com.pavithra.expensetracker.service.ExpenseService;
import com.pavithra.expensetracker.service.SalaryService;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InsightDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public InsightDialog(JFrame parent) {

		super(parent, "Spending Insights", true);

		ExpenseService expenseService = new ExpenseService();
		SalaryService salaryService = new SalaryService();

		setSize(700, 500);

		setLocationRelativeTo(parent);

		setLayout(new BorderLayout());

		JTextArea insightArea = new JTextArea();

		insightArea.setEditable(false);

		insightArea.setFont(new Font("Monospaced", Font.PLAIN, 15));

		JScrollPane scrollPane = new JScrollPane(insightArea);

		add(scrollPane, BorderLayout.CENTER);

		JButton generateButton = new JButton("Generate Insights");

		add(generateButton, BorderLayout.SOUTH);

		generateButton.addActionListener(e -> {

			try {

				double salary = salaryService.getMonthlySalary();

				ByteArrayOutputStream output = new ByteArrayOutputStream();

				PrintStream old = System.out;

				System.setOut(new PrintStream(output));

				expenseService.spendingInsights(salary);

				System.setOut(old);

				insightArea.setText(output.toString());

			}

			catch (Exception ex) {

				JOptionPane.showMessageDialog(

						this,

						"Unable to generate insights."

				);

			}

		});

	}

}