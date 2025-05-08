import java.util.Scanner;

public class BankingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Banking System!");
        
        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Start Banking");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                System.out.print("Enter account number: ");
                int accountNumber = scanner.nextInt();
                System.out.print("Enter password: ");
                String password = scanner.next();
                BankAccount.createAccount(accountNumber, password);
            } else if (choice == 2) {
                System.out.print("Enter account number: ");
                int accountNumber = scanner.nextInt();
                System.out.print("Enter password: ");
                String password = scanner.next();
                
                BankAccount account = BankAccount.login(accountNumber, password);
                if (account != null) {
                    System.out.println("Login successful!");
                    while (true) {
                        System.out.println("\n1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Balance Enquiry");
                        System.out.println("4. Logout");
                        System.out.print("Choose an option: ");
                        int action = scanner.nextInt();

                        if (action == 1) {
                            System.out.print("Enter amount to deposit: ");
                            double amount = scanner.nextDouble();
                            account.deposit(amount);
                        } else if (action == 2) {
                            System.out.print("Enter amount to withdraw: ");
                            double amount = scanner.nextDouble();
                            account.withdraw(amount);
                        } else if (action == 3) {
                            account.checkBalance();
                        } else if (action == 4) {
                            System.out.println("Logging out...");
                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid account number or password.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
