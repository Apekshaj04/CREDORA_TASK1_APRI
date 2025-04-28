import java.util.Scanner;

public class ATMApp {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean login(BankAccount account) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (account.getUserId().equals(userId) && account.getPin().equals(pin)) {
            System.out.println("Login Successful!\n");
            return true;
        } else {
            System.out.println("Invalid Credentials!");
            return false;
        }
    }

    public static void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }
}
