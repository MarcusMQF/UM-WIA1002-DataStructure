import java.util.ArrayList;
import java.util.Date;

class Transaction {
    private Date date;
    private char type;
    private double amount;
    private double balance;
    private String description;

    public Transaction(char type, double amount, double balance, String description) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Date: %s | Type: %c | Amount: $%.2f | Balance: $%.2f | Description: %s",
                date, type, amount, balance, description);
    }
}

class Account1 {
    private String name;
    private int id;
    private double balance;
    private static double annualInterestRate;
    private Date dateCreated;
    private ArrayList<Transaction> transactions;

    // Constructor
    public Account1(String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
        this.transactions = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(double rate) {
        annualInterestRate = rate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    // Calculate monthly interest rate
    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    // Calculate monthly interest
    public double getMonthlyInterest() {
        return balance * (getMonthlyInterestRate() / 100);
    }

    // Withdraw method with transaction recording
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction('W', amount, balance, "Withdrawal"));
        }
    }

    // Deposit method with transaction recording
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction('D', amount, balance, "Deposit"));
        }
    }

    // Print all transactions
    public void printTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

public class L1Q4 {
    public static void main(String[] args) {
        // Create account
        Account1 account = new Account1("George", 1122, 1000);
        
        // Set annual interest rate
        Account1.setAnnualInterestRate(1.5);
        
        // Perform deposits
        account.deposit(30);
        account.deposit(40);
        account.deposit(50);
        
        // Perform withdrawals
        account.withdraw(5);
        account.withdraw(4);
        account.withdraw(2);
        
        // Print account summary
        System.out.println("Account Summary for " + account.getName());
        System.out.println("------------------------");
        System.out.println("Account holder: " + account.getName());
        System.out.println("Interest rate: " + Account1.getAnnualInterestRate() + "%");
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("Monthly Interest: $" + account.getMonthlyInterest());
        System.out.println("\nTransaction History:");
        account.printTransactions();
    }
}