public class ATMOperations {
    private BankAccount account;

    public ATMOperations(BankAccount account) {
        this.account = account;
    }

    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        for (Transaction t : account.getTransactions()) {
            System.out.println(t);
        }
        System.out.println("-----------------------------");
        System.out.printf("💰 Current Balance: ₹%.2f%n\n", account.getBalance());
    }

    public void withdraw(double amount) {
        if (amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            account.addTransaction(new Transaction("Withdraw", amount));
            System.out.println("✅ Withdrawal Successful!");
        } else {
            System.out.println("❌ Insufficient Balance or Invalid Amount!");
        }
        System.out.printf("💰 Current Balance: ₹%.2f%n\n", account.getBalance());
    }

    public void deposit(double amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            account.addTransaction(new Transaction("Deposit", amount));
            System.out.println("✅ Deposit Successful!");
        } else {
            System.out.println("❌ Invalid Deposit Amount!");
        }
        System.out.printf("💰 Current Balance: ₹%.2f%n\n", account.getBalance());
    }

    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            account.addTransaction(new Transaction("Transfer to " + receiver.getUserId(), amount));
            receiver.addTransaction(new Transaction("Transfer from " + account.getUserId(), amount));

            System.out.println("✅ Transfer Successful!");
        } else {
            System.out.println("❌ Insufficient Balance or Invalid Amount!");
        }
        System.out.printf("💰 Current Balance: ₹%.2f%n\n", account.getBalance());
    }
}
