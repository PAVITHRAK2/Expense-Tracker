package com.pavithra.expensetracker.service;

import com.pavithra.expensetracker.model.Expense;
import com.pavithra.expensetracker.repository.ExpenseRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.time.Month;

public class ExpenseService {
	private ExpenseRepository repository =
            new ExpenseRepository();

    public void addExpense(
            String description,
            double amount,
            String category,
            String date) {

        List<Expense> expenses =
                repository.getAllExpenses();

        int id = expenses.size() + 1;

        Expense expense =
                new Expense(
                        id,
                        description,
                        amount,
                        category,
                        LocalDate.parse(date)
                );

        repository.saveExpense(expense);

        System.out.println(
                "Expense Added Successfully!");
    }

    public void viewExpenses() {

        List<Expense> expenses =
                repository.getAllExpenses();

        if (expenses.isEmpty()) {
            System.out.println(
                    "No expenses found.");
            return;
        }

        System.out.println(
                "ID | Description | Amount | Category | Date");

        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
    public void deleteExpense(int id) {

        boolean deleted =
                repository.deleteExpense(id);

        if (deleted) {
            System.out.println(
                    "Expense Deleted Successfully!");
        } else {
            System.out.println(
                    "Expense Not Found!");
        }
    }
    public void updateExpense(
            int id,
            String description,
            double amount,
            String category) {

        boolean updated =
                repository.updateExpense(
                        id,
                        description,
                        amount,
                        category);

        if (updated) {
            System.out.println(
                    "Expense Updated Successfully!");
        } else {
            System.out.println(
                    "Expense Not Found!");
        }
    }
    public void monthlySummary(int month) {

        double total =
                repository.getMonthlySummary(month);

        System.out.println(
                "Total Expenses for Month "
                + month + " : ₹" + total);
    }
    public double getCurrentMonthExpense() {
        return repository.getCurrentMonthExpense();
    }
    public void monthlyReport(int month, double monthlySalary) {

        double totalExpense = repository.getMonthlySummary(month);

        double savings = monthlySalary - totalExpense;

        Map<String, Double> categorySummary =
                repository.getCategorySummary(month);

        System.out.println("\n============== Monthly Report ==============");

        System.out.println("\nMonth            : " +
                Month.of(month));

        System.out.println("\nMonthly Salary   : ₹" +
                monthlySalary);

        System.out.println("\nTotal Expenses   : ₹" +
                totalExpense);

        System.out.println("\nTotal Savings    : ₹" +
                savings);

        System.out.println("\n--------------------------------------------");

        System.out.println("\nExpenses by Category\n");

        for (Map.Entry<String, Double> entry :
                categorySummary.entrySet()) {

            System.out.println(
                    entry.getKey() +
                    " : ₹" +
                    entry.getValue());
        }

        System.out.println(
                "\n============================================");
    }
    public void spendingInsights(double monthlySalary) {

        double totalExpense =
                repository.getCurrentMonthExpense();

        double savings =
                monthlySalary - totalExpense;

        double percentage =
                (totalExpense / monthlySalary) * 100;

        Map<String, Double> categorySummary =
                repository.getCategorySummary(
                        LocalDate.now().getMonthValue());

        String highestCategory = "";

        double highestAmount = 0;

        for (Map.Entry<String, Double> entry :
                categorySummary.entrySet()) {

            if (entry.getValue() > highestAmount) {

                highestAmount =
                        entry.getValue();

                highestCategory =
                        entry.getKey();
            }
        }

        System.out.println(
                "\n============== Spending Insights ==============");

        System.out.println(
                "\n✔ You spent ₹" +
                        totalExpense +
                        " this month.");

        System.out.println(
                "\n✔ You saved ₹" +
                        savings +
                        " this month.");

        System.out.println(
                "\n✔ " +
                        highestCategory +
                        " was your highest spending category.");

        System.out.println(
                "\n✔ You spent " +
                        String.format("%.2f", percentage) +
                        "% of your salary.");

        if (totalExpense <= monthlySalary) {

            System.out.println(
                    "\n✔ Great! You stayed within your monthly salary.");

        } else {

            System.out.println(
                    "\n⚠ Warning! You exceeded your monthly salary.");
        }

        System.out.println(
                "\n===============================================");
    }
    public List<Expense> getAllExpenses() {

        return repository.getAllExpenses();

    }

}
