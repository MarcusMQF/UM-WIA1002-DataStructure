public class T1Q5 {
    // Interface Account with deposit and withdraw methods
    interface Account {
        int deposit(int amount);
        boolean withdraw(int amount);
    }
    
    // BankAccount class implementing the Account interface
    static class BankAccount implements Account {
        // Instance variable for balance
        private int balance;
        
        // Constructor that initializes balance
        public BankAccount(int initialBalance) {
            this.balance = initialBalance;
        }
        
        // Implementation of deposit method
        @Override
        public int deposit(int amount) {
            balance += amount;
            return balance;
        }
        
        // Implementation of withdraw method
        @Override
        public boolean withdraw(int amount) {
            if (amount <= balance) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
        // Example usage of BankAccount
        BankAccount account = new BankAccount(1000);
        System.out.println("Initial balance: " + account.deposit(0));
        System.out.println("Depositing 500, new balance: " + account.deposit(500));
        System.out.println("Withdrawing 200: " + account.withdraw(200));
        System.out.println("Balance after withdrawal: " + account.deposit(0));
        System.out.println("Withdrawing 2000: " + account.withdraw(2000));
    }
}
