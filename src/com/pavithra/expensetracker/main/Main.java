package com.pavithra.expensetracker.main;
import com.pavithra.expensetracker.service.ExpenseService;
import com.pavithra.expensetracker.service.SalaryService;

import java.util.Scanner;

public class Main {
	  public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        ExpenseService service =
	                new ExpenseService();
	        SalaryService salaryService =
	                new SalaryService();

	        int choice;

	        do {

	            System.out.println("\n===== Expense Tracker =====");
	            System.out.println("1. Add Expense");
	            System.out.println("2. View Expenses");
	            System.out.println("3. Delete Expense");
	            System.out.println("4. Update Expense");
	            System.out.println("5. Monthly Summary");
	            System.out.println("6. Set Salary");
	            System.out.println("7. Dashboard");
	            System.out.println("8. Monthly Report");
	            System.out.println("9. Spending Insights");
	            System.out.println("10. Exit");

	            System.out.print(
	                    "Enter Choice: ");

	            choice = sc.nextInt();
	            sc.nextLine();

	            switch (choice) {

	                case 1:

	                    System.out.print(
	                            "Description: ");
	                    String desc =
	                            sc.nextLine();

	                    System.out.print(
	                            "Amount: ");
	                    double amount =
	                            sc.nextDouble();
	                    sc.nextLine();

	                    System.out.print(
	                            "Category: ");
	                    String category =
	                            sc.nextLine();
	                    
	                    System.out.print("Date (yyyy-mm-dd): ");
	                    String date = sc.nextLine();

	                    service.addExpense(
	                            desc,
	                            amount,
	                            category,
	                            date);

	                    break;

	                case 2:

	                    service.viewExpenses();
	                    break;

	                case 3:

	                    System.out.print(
	                            "Enter Expense ID: ");

	                    int deleteId =
	                            sc.nextInt();

	                    service.deleteExpense(
	                            deleteId);

	                    break;
	                case 4:

	                    System.out.print(
	                            "Enter Expense ID: ");

	                    int updateId =
	                            sc.nextInt();
	                    sc.nextLine();

	                    System.out.print(
	                            "New Description: ");

	                    String newDesc =
	                            sc.nextLine();

	                    System.out.print(
	                            "New Amount: ");

	                    double newAmount =
	                            sc.nextDouble();
	                    sc.nextLine();

	                    System.out.print(
	                            "New Category: ");

	                    String newCategory =
	                            sc.nextLine();

	                    service.updateExpense(
	                            updateId,
	                            newDesc,
	                            newAmount,
	                            newCategory);

	                    break;
	                case 5:

	                    System.out.print(
	                            "Enter Month (1-12): ");

	                    int month = sc.nextInt();

	                    service.monthlySummary(month);

	                    break;
	                    
	                case 6:

	                    System.out.print(
	                            "Enter Monthly Salary: ");

	                    double salary = sc.nextDouble();

	                    salaryService.setMonthlySalary(
	                            salary);

	                    System.out.println(
	                            "Salary Saved!");

	                    break;
	              

	                
	                case 7:

	                    double dashboardSalary =
	                            salaryService.getMonthlySalary();

	                    double dashboardExpense =
	                            service.getCurrentMonthExpense();

	                    double dashboardSavings =
	                            dashboardSalary - dashboardExpense;

	                    System.out.println(
	                            "\n========== PERSONAL FINANCE DASHBOARD ==========");

	                    System.out.println(
	                            "Monthly Salary      : ₹" + dashboardSalary);

	                    System.out.println(
	                            "This Month Expense  : ₹" + dashboardExpense);

	                    System.out.println(
	                            "Saved This Month    : ₹" + dashboardSavings);

	                    System.out.println(
	                            "===============================================");

	                    break;
	                case 8:

	                    System.out.print(
	                            "Enter Month (1-12): ");

	                    int reportMonth =
	                            sc.nextInt();

	                    double reportSalary =
	                            salaryService.getMonthlySalary();

	                    service.monthlyReport(
	                            reportMonth,
	                            reportSalary);

	                    break;
	                case 9:

	                    double currentsalary =
	                            salaryService.getMonthlySalary();

	                    service.spendingInsights(
	                            currentsalary);

	                    break;
	               
	                case 10:

	                    System.out.println(
	                            "Thank you!");

	                    break;  
	                default:

	                    System.out.println(
	                            "Invalid Choice");
	            }

	        } while (choice != 10);

	        sc.close();
	    }
	}
	


