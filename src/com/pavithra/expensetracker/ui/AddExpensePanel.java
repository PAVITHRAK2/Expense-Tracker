package com.pavithra.expensetracker.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.pavithra.expensetracker.service.ExpenseService;

public class AddExpensePanel extends JDialog {

    private static final long serialVersionUID = 1L;

    public AddExpensePanel(JFrame parent) {

        super(parent, "Add Expense", true);

        ExpenseService service = new ExpenseService();

        setSize(400, 350);

        setLocationRelativeTo(parent);

        setLayout(null);

        JLabel descLabel = new JLabel("Description");
        descLabel.setBounds(30, 30, 100, 25);

        JTextField descField = new JTextField();
        descField.setBounds(150, 30, 180, 25);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(30, 70, 100, 25);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 70, 180, 25);

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(30, 110, 100, 25);

        JTextField categoryField = new JTextField();
        categoryField.setBounds(150, 110, 180, 25);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(30, 150, 100, 25);

        JTextField dateField = new JTextField("2026-07-02");
        dateField.setBounds(150, 150, 180, 25);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(80, 230, 100, 35);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210, 230, 100, 35);

        saveButton.addActionListener(e -> {

            try {

                service.addExpense(

                        descField.getText(),

                        Double.parseDouble(amountField.getText()),

                        categoryField.getText(),

                        dateField.getText()

                );

                JOptionPane.showMessageDialog(
                        this,
                        "Expense Added Successfully");

                dispose();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input");

            }

        });

        cancelButton.addActionListener(e -> dispose());

        add(descLabel);
        add(descField);

        add(amountLabel);
        add(amountField);

        add(categoryLabel);
        add(categoryField);

        add(dateLabel);
        add(dateField);

        add(saveButton);
        add(cancelButton);

    }

}