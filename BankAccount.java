import java.sql.*;

public class BankAccount {
    private int accountNumber;
    private String password;
    private double balance;

    // Constructor
    public BankAccount(int accountNumber, String password, double balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    // Create a new account
    public static void createAccount(int accountNumber, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO accounts (account_number, password, balance) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, accountNumber);
                stmt.setString(2, password);
                stmt.setDouble(3, 0.0);
                stmt.executeUpdate();
                System.out.println("Account created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Validate account and password
    public static BankAccount login(int accountNumber, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM accounts WHERE account_number = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, accountNumber);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    return new BankAccount(accountNumber, password, balance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Deposit money
    public void deposit(double amount) {
        this.balance += amount;
        updateBalance();
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            updateBalance();
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Balance enquiry
    public void checkBalance() {
        System.out.println("Current balance: " + this.balance);
    }

    // Update the balance in the database
    private void updateBalance() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setDouble(1, this.balance);
                stmt.setInt(2, this.accountNumber);
                stmt.executeUpdate();
                System.out.println("Balance updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
