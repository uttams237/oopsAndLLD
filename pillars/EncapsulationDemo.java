/**
 * =====================================================
 * 1. ENCAPSULATION (First Pillar of OOP)
 * =====================================================
 * 
 * DEFINITION:
 * Encapsulation is the mechanism of wrapping the data (variables) and 
 * code acting on the data (methods) together as a single unit.
 * 
 * KEY CONCEPTS:
 * - Data Hiding: Variables are hidden from other classes using 'private'.
 * - Controlled Access: Public getter/setter methods control how data is accessed.
 * 
 * HOW TO ACHIEVE ENCAPSULATION:
 * 1. Declare the variables of a class as private.
 * 2. Provide public setter and getter methods to modify and view the values.
 * 
 * BENEFITS:
 * - Data protection: Prevents invalid data from being set.
 * - Flexibility: Internal implementation can change without affecting users.
 * - Maintainability: Easier to debug and modify.
 * 
 * LLD RELEVANCE:
 * - Encapsulation is fundamental to designing robust classes.
 * - It helps enforce invariants (rules about valid states).
 * - Used heavily in design patterns like Builder, Factory, etc.
 * =====================================================
 */

class BankAccount {
    
    // =========================================
    // PRIVATE FIELDS (Data Hiding)
    // These cannot be accessed directly from outside the class.
    // This protects the data from invalid modifications.
    // =========================================
    private String accountHolderName;
    private double balance;

    // =========================================
    // CONSTRUCTOR
    // Initializes the object with valid starting state.
    // Note: We validate initialBalance before setting.
    // =========================================
    public BankAccount(String name, double initialBalance) {
        this.accountHolderName = name;
        // Validation: Only set balance if it's positive
        if (initialBalance > 0) {
            this.balance = initialBalance;
        }
    }

    // =========================================
    // GETTER: Provides read-only access to private field.
    // =========================================
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // =========================================
    // SETTER: Provides controlled write access.
    // You can add validation here if needed.
    // =========================================
    public void setAccountHolderName(String accountHolderName) {
        // Example: You could add validation like checking for null/empty
        this.accountHolderName = accountHolderName;
    }

    // =========================================
    // GETTER: Read-only access to balance.
    // Notice there's no setBalance() - we don't allow direct modification!
    // =========================================
    public double getBalance() {
        return balance;
    }

    // =========================================
    // BUSINESS METHOD: Controlled way to modify balance.
    // Deposit includes validation to prevent invalid operations.
    // =========================================
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    // =========================================
    // BUSINESS METHOD: Another controlled modification.
    // Withdrawal checks for sufficient funds.
    // =========================================
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        System.out.println("=== Encapsulation Demo ===\n");
        
        // Create an account with initial balance
        BankAccount account = new BankAccount("John Doe", 1000);

        // ❌ This would cause a compile error because 'balance' is private:
        // account.balance = 5000; 

        // ✅ We must use public getter methods to access data
        System.out.println("Name: " + account.getAccountHolderName());
        System.out.println("Balance: " + account.getBalance());

        System.out.println();

        // ✅ Modifying state through controlled methods (with validation)
        account.deposit(500);   // Valid deposit
        account.deposit(-100);  // Invalid - will be rejected
        account.withdraw(200);  // Valid withdrawal
        account.withdraw(5000); // Invalid - insufficient funds

        System.out.println();
        System.out.println("Final Balance: " + account.getBalance());
        
        System.out.println("\n=== KEY TAKEAWAY ===");
        System.out.println("Encapsulation = Private data + Public methods for controlled access.");
    }
}
