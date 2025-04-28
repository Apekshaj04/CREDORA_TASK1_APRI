import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Welcome to Java ATM -----");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create New Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (mainChoice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    BankAccount userAccount = login(scanner);
                    if (userAccount != null) {
                        operateATM(scanner, userAccount);
                    }
                    break;
                case 3:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter New User ID: ");
        String userId = scanner.nextLine();

        // Check if userId already exists
        for (BankAccount acc : accounts) {
            if (acc.getUserId().equals(userId)) {
                System.out.println("User ID already exists. Please try a different one.");
                return;
            }
        }

        System.out.print("Enter New PIN: ");
        String pin = scanner.nextLine();

        System.out.print("Enter Initial Deposit Amount: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        BankAccount newAccount = new BankAccount(userId, pin, balance);
        accounts.add(newAccount);

        System.out.println("Account created successfully! You can now login.");
    }

    private static BankAccount login(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        for (BankAccount acc : accounts) {
            if (acc.getUserId().equals(userId) && acc.getPin().equals(pin)) {
                System.out.println("Login Successful!\n");
                return acc;
            }
        }

        System.out.println("Invalid Credentials! Please try again.");
        return null;
    }

    private static void operateATM(Scanner scanner, BankAccount userAccount) {
        ATMOperations atm = new ATMOperations(userAccount);
        boolean quit = false;

        while (!quit) {
            ATMApp.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    atm.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter receiver's User ID: ");
                    scanner.nextLine(); // clear buffer
                    String receiverId = scanner.nextLine();

                    BankAccount receiverAccount = findAccount(receiverId);
                    if (receiverAccount == null) {
                        System.out.println("Receiver account not found!");
                        break;
                    }

                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    atm.transfer(receiverAccount, transferAmount);
                    break;
                case 5:
                    quit = true;
                    System.out.println("Logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static BankAccount findAccount(String userId) {
        for (BankAccount acc : accounts) {
            if (acc.getUserId().equals(userId)) {
                return acc;
            }
        }
        return null;
    }
}
