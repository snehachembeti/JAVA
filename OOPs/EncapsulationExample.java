public class EncapsulationExample {
    public static void main(String[] args) {
        EncapsulatedBankAccount account = new EncapsulatedBankAccount("Sneha", 1000);

        account.deposit(500);
        account.withdraw(300);

        System.out.println("Account holder: " + account.getAccountHolder());
        System.out.println("Current balance: Rs." + account.getBalance());
    }
}

class EncapsulatedBankAccount {
    // Data is private, so outside classes cannot change balance directly.
    private String accountHolder;
    private double balance;

    public EncapsulatedBankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        // Public methods control how private data can be modified.
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance -= amount;
    }
}
