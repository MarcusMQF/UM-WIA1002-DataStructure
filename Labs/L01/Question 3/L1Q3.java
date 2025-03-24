import java.util.Date;

class Account {
    private int id = 0;
    private double balance = 0;
    private double annualInterestRate = 0;
    private Date dateCreated;

    // No-arg constructor
    public Account() {
        dateCreated = new Date();
    }

    // Constructor with specified id and balance
    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    // Accessor and mutator methods
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

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    // Method to get monthly interest rate
    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    // Method to get monthly interest
    public double getMonthlyInterest() {
        return balance * (getMonthlyInterestRate() / 100);
    }

    // Method to withdraw amount
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }

    // Method to deposit amount
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

public class L1Q3 {
    public static void main(String[] args) {
        // Create account with ID 1122, balance $20,000
        Account account = new Account(1122, 20000);
        
        // Set annual interest rate to 4.5%
        account.setAnnualInterestRate(4.5);
        
        // Withdraw $2,500
        account.withdraw(2500);
        
        // Deposit $3,000
        account.deposit(3000);
        
        // Display results
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("Monthly Interest: $" + account.getMonthlyInterest());
        System.out.println("Date Created: " + account.getDateCreated());
    }
}