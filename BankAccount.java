import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String userId;
    private String pin;
    private double balance;
    private List<Transaction> transactions;

    // Constructor to initialize bank account with user ID, PIN, and initial balance
    public BankAccount(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        addTransaction(new Transaction("Account Creation", balance));  // Record the initial deposit as a transaction
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
