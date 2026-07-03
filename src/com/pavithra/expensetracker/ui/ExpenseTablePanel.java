package com.pavithra.expensetracker.ui;

import com.pavithra.expensetracker.model.Expense;
import com.pavithra.expensetracker.service.ExpenseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExpenseTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;
	private ExpenseService service;

	public ExpenseTablePanel() {

		service = new ExpenseService();

		setLayout(new BorderLayout());

		String[] columns = { "ID", "Description", "Amount", "Category", "Date" };

		model = new DefaultTableModel(columns, 0) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		table = new JTable(model);

		table.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, BorderLayout.CENTER);

		loadExpenses();

	}

	public void loadExpenses() {

		model.setRowCount(0);

		List<Expense> expenses = service.getAllExpenses();

		for (Expense expense : expenses) {

			model.addRow(new Object[] {

					expense.getId(), expense.getDescription(), expense.getAmount(), expense.getCategory(),
					expense.getDate()

			});

		}

	}

	public JTable getTable() {
		return table;
	}

	public int getSelectedExpenseId() {

		int row = table.getSelectedRow();

		if (row == -1) {

			return -1;

		}

		return Integer.parseInt(table.getValueAt(row, 0).toString());

	}

	public String getDescription() {

		int row = table.getSelectedRow();

		return table.getValueAt(row, 1).toString();

	}

	public double getAmount() {

		int row = table.getSelectedRow();

		return Double.parseDouble(table.getValueAt(row, 2).toString());

	}

	public String getCategory() {

		int row = table.getSelectedRow();

		return table.getValueAt(row, 3).toString();

	}

}