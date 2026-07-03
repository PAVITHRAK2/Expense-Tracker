package com.pavithra.expensetracker.repository;

import com.pavithra.expensetracker.model.Expense;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ExpenseRepository {
	private static final String FILE_PATH = "data/expenses.csv";

	public void saveExpense(Expense expense) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

			writer.write(expense.getId() + "," + expense.getDescription() + "," + expense.getAmount() + ","
					+ expense.getCategory() + "," + expense.getDate());

			writer.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Expense> getAllExpenses() {

		List<Expense> expenses = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

			String line;

			while ((line = reader.readLine()) != null) {

				String[] data = line.split(",");

				Expense expense = new Expense(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]), data[3],
						LocalDate.parse(data[4]));

				expenses.add(expense);
			}

		} catch (IOException e) {

		}

		return expenses;
	}

	public boolean updateExpense(int id, String description, double amount, String category) {

		List<Expense> expenses = getAllExpenses();

		boolean found = false;

		for (Expense expense : expenses) {

			if (expense.getId() == id) {

				expense.setDescription(description);
				expense.setAmount(amount);
				expense.setCategory(category);

				found = true;
				break;
			}
		}

		if (found) {
			rewriteFile(expenses);
		}

		return found;
	}

	public boolean deleteExpense(int id) {

		List<Expense> expenses = getAllExpenses();

		boolean removed = false;

		for (int i = 0; i < expenses.size(); i++) {

			if (expenses.get(i).getId() == id) {
				expenses.remove(i);
				removed = true;
				break;
			}
		}

		if (removed) {
			rewriteFile(expenses);
		}

		return removed;
	}

	public double getMonthlySummary(int month) {

		List<Expense> expenses = getAllExpenses();

		double total = 0;

		for (Expense expense : expenses) {

			if (expense.getDate().getMonthValue() == month) {

				total += expense.getAmount();
			}
		}

		return total;
	}

	public double getCurrentMonthExpense() {

		List<Expense> expenses = getAllExpenses();
		double total = 0;

		int currentMonth = LocalDate.now().getMonthValue();

		for (Expense expense : expenses) {
			if (expense.getDate().getMonthValue() == currentMonth) {
				total += expense.getAmount();
			}
		}

		return total;
	}

	public Map<String, Double> getCategorySummary(int month) {

		List<Expense> expenses = getAllExpenses();

		Map<String, Double> categorySummary = new HashMap<>();

		for (Expense expense : expenses) {

			if (expense.getDate().getMonthValue() == month) {

				String category = expense.getCategory();

				double amount = expense.getAmount();

				if (categorySummary.containsKey(category)) {

					categorySummary.put(category, categorySummary.get(category) + amount);

				} else {

					categorySummary.put(category, amount);
				}
			}
		}

		return categorySummary;
	}

	private void rewriteFile(List<Expense> expenses) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

			for (Expense expense : expenses) {

				writer.write(expense.getId() + "," + expense.getDescription() + "," + expense.getAmount() + ","
						+ expense.getCategory() + "," + expense.getDate());

				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
