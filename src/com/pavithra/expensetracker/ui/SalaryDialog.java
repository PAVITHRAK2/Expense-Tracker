package com.pavithra.expensetracker.ui;

import com.pavithra.expensetracker.service.SalaryService;

import javax.swing.*;
import java.awt.*;

public class SalaryDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public SalaryDialog(JFrame parent) {

        super(parent, "Set Monthly Salary", true);

        SalaryService salaryService = new SalaryService();

        setSize(400,220);

        setLocationRelativeTo(parent);

        setLayout(null);

        JLabel title = new JLabel("Monthly Salary");

        title.setFont(new Font("Arial", Font.BOLD, 18));

        title.setBounds(30,20,200,30);

        add(title);

        JLabel salaryLabel = new JLabel("Salary (₹)");

        salaryLabel.setBounds(30,70,100,25);

        add(salaryLabel);

        JTextField salaryField = new JTextField();

        salaryField.setBounds(140,70,180,30);

        salaryField.setText(
                String.valueOf(
                        salaryService.getMonthlySalary()));

        add(salaryField);

        JButton saveButton = new JButton("Save");

        saveButton.setBounds(70,130,100,35);

        add(saveButton);

        JButton cancelButton = new JButton("Cancel");

        cancelButton.setBounds(200,130,100,35);

        add(cancelButton);

        saveButton.addActionListener(e -> {

            try {

                double salary =
                        Double.parseDouble(
                                salaryField.getText());

                salaryService.setMonthlySalary(salary);

                JOptionPane.showMessageDialog(

                        this,

                        "Salary Saved Successfully!"

                );

                dispose();

            }

            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(

                        this,

                        "Please enter a valid salary."

                );

            }

        });

        cancelButton.addActionListener(e -> dispose());

    }

}