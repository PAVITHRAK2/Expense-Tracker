package com.pavithra.expensetracker.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.pavithra.expensetracker.service.ExpenseService;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private ExpenseTablePanel tablePanel;

	public MainFrame() {

		setTitle("Expense Tracker");
		setSize(1100, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(220, 650));
		leftPanel.setBackground(new Color(45, 45, 45));
		leftPanel.setLayout(null);

		JLabel title = new JLabel("Expense Tracker");
		title.setBounds(15, 30, 200, 35);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 22));
		leftPanel.add(title);

		JButton dashboardBtn = new JButton("Dashboard");
		dashboardBtn.setBounds(20, 100, 170, 35);
		leftPanel.add(dashboardBtn);

		JButton addExpenseBtn = new JButton("Add Expense");
		addExpenseBtn.setBounds(20, 150, 170, 35);
		leftPanel.add(addExpenseBtn);

		JButton salaryBtn = new JButton("Set Salary");
		salaryBtn.setBounds(20, 200, 170, 35);
		leftPanel.add(salaryBtn);

		JButton reportBtn = new JButton("Monthly Report");
		reportBtn.setBounds(20, 250, 170, 35);
		leftPanel.add(reportBtn);

		JButton insightBtn = new JButton("Insights");
		insightBtn.setBounds(20, 300, 170, 35);
		leftPanel.add(insightBtn);

		JButton updateBtn = new JButton("Update Expense");
		updateBtn.setBounds(20, 350, 170, 35);
		leftPanel.add(updateBtn);

		JButton deleteBtn = new JButton("Delete Expense");
		deleteBtn.setBounds(20, 400, 170, 35);
		leftPanel.add(deleteBtn);

		tablePanel = new ExpenseTablePanel();

		add(leftPanel, BorderLayout.WEST);
		add(tablePanel, BorderLayout.CENTER);

		// ---------------- Add Expense ----------------

		addExpenseBtn.addActionListener(e -> {

			AddExpensePanel dialog = new AddExpensePanel(this);

			dialog.setVisible(true);

			tablePanel.loadExpenses();

		});

		// ---------------- Dashboard ----------------

		dashboardBtn.addActionListener(e -> {

			DashboardPanel dashboard = new DashboardPanel(this);

			dashboard.setVisible(true);

		});

		// ---------------- Salary ----------------

		salaryBtn.addActionListener(e -> {

			SalaryDialog salary = new SalaryDialog(this);

			salary.setVisible(true);

		});

		// ---------------- Report ----------------

		reportBtn.addActionListener(e -> {

			ReportDialog report = new ReportDialog(this);

			report.setVisible(true);

		});

		// ---------------- Insights ----------------

		insightBtn.addActionListener(e -> {

			InsightDialog insight = new InsightDialog(this);

			insight.setVisible(true);

		});

		// ---------------- Update ----------------

		updateBtn.addActionListener(e -> {

			int id = tablePanel.getSelectedExpenseId();

			if (id == -1) {

				JOptionPane.showMessageDialog(this, "Please select an expense.");

				return;
			}

			String description = JOptionPane.showInputDialog(this, "Description", tablePanel.getDescription());

			if (description == null)
				return;

			String amountText = JOptionPane.showInputDialog(this, "Amount", tablePanel.getAmount());

			if (amountText == null)
				return;

			String category = JOptionPane.showInputDialog(this, "Category", tablePanel.getCategory());

			if (category == null)
				return;

			ExpenseService service = new ExpenseService();

			service.updateExpense(id, description, Double.parseDouble(amountText), category);

			tablePanel.loadExpenses();

		});

		// ---------------- Delete ----------------

		deleteBtn.addActionListener(e -> {

			int id = tablePanel.getSelectedExpenseId();

			if (id == -1) {

				JOptionPane.showMessageDialog(this, "Please select an expense.");

				return;
			}

			int option = JOptionPane.showConfirmDialog(this, "Delete selected expense?", "Confirm",
					JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {

				ExpenseService service = new ExpenseService();

				service.deleteExpense(id);

				tablePanel.loadExpenses();
			}

		});

		setVisible(true);

	}

}