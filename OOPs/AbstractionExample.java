public class AbstractionExample {
    public static void main(String[] args) {
        AbstractPayment creditCardPayment = new AbstractCreditCardPayment("1234");
        AbstractPayment upiPayment = new AbstractUpiPayment("sneha@upi");

        creditCardPayment.pay(1500);
        upiPayment.pay(750);
    }
}

abstract class AbstractPayment {
    public void printReceipt(double amount) {
        System.out.println("Receipt generated for Rs." + amount);
    }

    // Child classes must decide their own payment process.
    public abstract void pay(double amount);
}

class AbstractCreditCardPayment extends AbstractPayment {
    private String lastFourDigits;

    public AbstractCreditCardPayment(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using credit card ending with " + lastFourDigits);
        printReceipt(amount);
    }
}

class AbstractUpiPayment extends AbstractPayment {
    private String upiId;

    public AbstractUpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using UPI ID " + upiId);
        printReceipt(amount);
    }
}
