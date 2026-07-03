package com.pavithra.expensetracker.service;

import com.pavithra.expensetracker.repository.SalaryRepository;
public class SalaryService {
	 
	 private SalaryRepository repository =
	            new SalaryRepository();

	   
	    public void setMonthlySalary(double salary) {
	    	repository.saveSalary(salary);
	    }

	    public double getMonthlySalary() {
	    	return repository.getSalary();
	    }

	    public double getSavings(double monthlyExpenses) {
	        return getMonthlySalary() - monthlyExpenses;
	    }
}
