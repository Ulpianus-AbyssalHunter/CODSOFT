//Task 3 - ATM Interface

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    private JFrame frame;
    private JTextField amountField;
    private JLabel balanceLabel;
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
        frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        balanceLabel = new JLabel("Balance: " + account.getBalance());
        frame.add(balanceLabel);

        frame.add(new JLabel("Amount:"));
        amountField = new JTextField();
        frame.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new DepositListener());
        frame.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new WithdrawListener());
        frame.add(withdrawButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new CheckBalanceListener());
        frame.add(checkBalanceButton);

        frame.setVisible(true);
    }

    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    account.deposit(amount);
                    balanceLabel.setText("Balance: " + account.getBalance());
                    JOptionPane.showMessageDialog(frame, "Deposit successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a positive number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
            }
        }
    }

    private class WithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    if (account.withdraw(amount)) {
                        balanceLabel.setText("Balance: " + account.getBalance());
                        JOptionPane.showMessageDialog(frame, "Withdrawal successful!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a positive number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
            }
        }
    }

    private class CheckBalanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            balanceLabel.setText("Balance: " + account.getBalance());
            JOptionPane.showMessageDialog(frame, "Your current balance is: " + account.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Initial balance
        new ATM(account);
    }
}