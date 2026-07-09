public class StrategyDesignPattern {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.setPaymentStrategy(new CreditCardPaymentStrategy("Sneha", "1234"));
        processor.checkout(1500.00);

        processor.setPaymentStrategy(new UpiPaymentStrategy("sneha@upi"));
        processor.checkout(750.00);

        processor.setPaymentStrategy(new CashOnDeliveryPaymentStrategy());
        processor.checkout(499.00);
    }
}

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPaymentStrategy implements PaymentStrategy {
    private String name;
    private String lastFourDigits;

    public CreditCardPaymentStrategy(String name, String lastFourDigits) {
        this.name = name;
        this.lastFourDigits = lastFourDigits;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using credit card ending with " + lastFourDigits + " by " + name);
    }
}

class UpiPaymentStrategy implements PaymentStrategy {
    private String upiId;

    public UpiPaymentStrategy(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using UPI ID " + upiId);
    }
}

class CashOnDeliveryPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Order placed with cash on delivery for Rs." + amount);
    }
}

class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method.");
            return;
        }

        paymentStrategy.pay(amount);
    }
}
