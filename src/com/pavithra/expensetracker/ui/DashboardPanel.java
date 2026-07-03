package com.pavithra.expensetracker.ui;

import com.pavithra.expensetracker.service.ExpenseService;
import com.pavithra.expensetracker.service.SalaryService;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JDialog {

	private static final long serialVersionUID = 1L;

	public DashboardPanel(JFrame parent) {

		super(parent, "Dashboard", true);

		ExpenseService expenseService = new ExpenseService();
		SalaryService salaryService = new SalaryService();

		double salary = salaryService.getMonthlySalary();
		double expense = expenseService.getCurrentMonthExpense();
		double savings = salary - expense;

		setSize(600, 300);
		setLocationRelativeTo(parent);
		setLayout(new GridLayout(1, 3, 15, 15));

		add(createCard("Monthly Salary", "₹ " + salary, new Color(52, 152, 219)));
		add(createCard("Monthly Expense", "₹ " + expense, new Color(231, 76, 60)));
		add(createCard("Savings", "₹ " + savings, new Color(46, 204, 113)));

		setVisible(true);
	}

	private JPanel createCard(String title, String value, Color color) {

		JPanel panel = new JPanel();

		panel.setBackground(color);

		panel.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

		JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
		valueLabel.setForeground(Color.WHITE);
		valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

		panel.add(titleLabel, BorderLayout.NORTH);
		panel.add(valueLabel, BorderLayout.CENTER);

		return panel;
	}
}
