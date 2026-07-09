public class OpenClosedPrinciple {
    public static void main(String[] args) {
        printHeader();

        PaymentProcessor paymentProcessor = new PaymentProcessor();

        paymentProcessor.checkout(1500, new CreditCardPayment("Sneha", "1234"));
        paymentProcessor.checkout(800, new UpiPayment("sneha@upi"));
        paymentProcessor.checkout(500, new WalletPayment("Paytm Wallet"));
    }

    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("O - Open/Closed Principle");
        System.out.println("==================================================");
        System.out.println("Interview line: Open for extension, closed for modification.");
        System.out.println();
    }
}

/*
 * ==================================================
 * O - Open/Closed Principle
 * ==================================================
 *
 * Interview definition:
 * Software entities should be open for extension but closed for modification.
 *
 * Simple meaning:
 * Add new behavior by creating new code, not by changing old tested code.
 *
 * Payment example:
 * PaymentProcessor depends on PaymentMethod interface.
 * To add UPI, wallet, net banking, etc., create a new class implementing
 * PaymentMethod. No need to modify PaymentProcessor.
 */
interface PaymentMethod {
    void pay(double amount);
}

class PaymentProcessor {
    public void checkout(double amount, PaymentMethod paymentMethod) {
        System.out.println("Checkout started for Rs." + amount);
        paymentMethod.pay(amount);
        System.out.println("Checkout completed.");
        System.out.println();
    }
}

class CreditCardPayment implements PaymentMethod {
    private String name;
    private String lastFourDigits;

    public CreditCardPayment(String name, String lastFourDigits) {
        this.name = name;
        this.lastFourDigits = lastFourDigits;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using credit card " + lastFourDigits + " by " + name);
    }
}

class UpiPayment implements PaymentMethod {
    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using UPI ID " + upiId);
    }
}

class WalletPayment implements PaymentMethod {
    private String walletName;

    public WalletPayment(String walletName) {
        this.walletName = walletName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using " + walletName);
    }
}
