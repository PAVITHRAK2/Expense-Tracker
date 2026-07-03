package com.pavithra.expensetracker.repository;
import java.io.*;
public class SalaryRepository {
	private static final String FILE_PATH =
            "data/salary.txt";

    public void saveSalary(double salary) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(FILE_PATH))) {

            writer.write(String.valueOf(salary));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getSalary() {

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(FILE_PATH))) {

            String salary = reader.readLine();

            if (salary != null) {
                return Double.parseDouble(salary);
            }

        } catch (IOException e) {

        }

        return 0;
    }
}
