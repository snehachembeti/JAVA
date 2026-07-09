import java.util.ArrayList;
import java.util.List;

public class SOLIDPrinciples {
    public static void main(String[] args) {
        System.out.println("S - Single Responsibility Principle");
        SolidInvoice invoice = new SolidInvoice("Laptop", 50000, 2);
        SolidInvoicePrinter invoicePrinter = new SolidInvoicePrinter();
        SolidInvoiceRepository invoiceRepository = new SolidInvoiceRepository();
        invoicePrinter.print(invoice);
        invoiceRepository.save(invoice);

        System.out.println();
        System.out.println("O - Open/Closed Principle");
        SolidBillingService billingService = new SolidBillingService();
        billingService.printFinalAmount(1000, new RegularCustomerDiscount());
        billingService.printFinalAmount(1000, new PremiumCustomerDiscount());

        System.out.println();
        System.out.println("L - Liskov Substitution Principle");
        List<SolidShape> shapes = new ArrayList<>();
        shapes.add(new SolidRectangle(10, 5));
        shapes.add(new SolidCircle(7));
        SolidAreaCalculator areaCalculator = new SolidAreaCalculator();
        areaCalculator.printAreas(shapes);

        System.out.println();
        System.out.println("I - Interface Segregation Principle");
        SolidPrintableReport simplePrinter = new SolidSimplePrinter();
        simplePrinter.print("Monthly sales report");

        SolidMultiFunctionPrinter multiFunctionPrinter = new SolidMultiFunctionPrinter();
        multiFunctionPrinter.print("Employee report");
        multiFunctionPrinter.scan("Signed offer letter");

        System.out.println();
        System.out.println("D - Dependency Inversion Principle");
        SolidNotificationService emailNotification = new SolidNotificationService(new SolidEmailMessageService());
        emailNotification.notifyUser("Your order is shipped.");

        SolidNotificationService smsNotification = new SolidNotificationService(new SolidSmsMessageService());
        smsNotification.notifyUser("Your OTP is 123456.");
    }
}

/*
 * S - Single Responsibility Principle
 *
 * Interview definition:
 * A class should have only one reason to change.
 *
 * Meaning:
 * One class should focus on one responsibility only.
 *
 * In this example:
 * SolidInvoice only handles invoice data and calculation.
 * SolidInvoicePrinter only handles printing.
 * SolidInvoiceRepository only handles saving.
 */
class SolidInvoice {
    private String itemName;
    private double itemPrice;
    private int quantity;

    public SolidInvoice(String itemName, double itemPrice, int quantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return itemPrice * quantity;
    }

    public String getItemName() {
        return itemName;
    }
}

class SolidInvoicePrinter {
    public void print(SolidInvoice invoice) {
        System.out.println("Invoice item: " + invoice.getItemName());
        System.out.println("Invoice total: Rs." + invoice.calculateTotal());
    }
}

class SolidInvoiceRepository {
    public void save(SolidInvoice invoice) {
        System.out.println("Saving invoice for: " + invoice.getItemName());
    }
}

/*
 * O - Open/Closed Principle
 *
 * Interview definition:
 * Software entities should be open for extension but closed for modification.
 *
 * Meaning:
 * You should be able to add new behavior without changing old working code.
 *
 * In this example:
 * SolidBillingService does not change when a new discount type is added.
 * We only create another implementation of SolidDiscountPolicy.
 */
interface SolidDiscountPolicy {
    double applyDiscount(double amount);
}

class RegularCustomerDiscount implements SolidDiscountPolicy {
    @Override
    public double applyDiscount(double amount) {
        return amount - 50;
    }
}

class PremiumCustomerDiscount implements SolidDiscountPolicy {
    @Override
    public double applyDiscount(double amount) {
        return amount - 200;
    }
}

class SolidBillingService {
    public void printFinalAmount(double amount, SolidDiscountPolicy discountPolicy) {
        double finalAmount = discountPolicy.applyDiscount(amount);
        System.out.println("Final bill amount: Rs." + finalAmount);
    }
}

/*
 * L - Liskov Substitution Principle
 *
 * Interview definition:
 * Child classes should be replaceable wherever the parent type is expected,
 * without breaking the program.
 *
 * Meaning:
 * If code works with a parent type, it should also work with any child type.
 *
 * In this example:
 * SolidAreaCalculator works with SolidShape.
 * Rectangle and Circle can both be used safely as SolidShape.
 */
interface SolidShape {
    double area();
}

class SolidRectangle implements SolidShape {
    private double length;
    private double width;

    public SolidRectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}

class SolidCircle implements SolidShape {
    private double radius;

    public SolidCircle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class SolidAreaCalculator {
    public void printAreas(List<SolidShape> shapes) {
        for (SolidShape shape : shapes) {
            System.out.println("Area: " + shape.area());
        }
    }
}

/*
 * I - Interface Segregation Principle
 *
 * Interview definition:
 * A class should not be forced to implement methods it does not use.
 *
 * Meaning:
 * Prefer small, focused interfaces instead of one large interface.
 *
 * In this example:
 * SolidSimplePrinter only implements printing.
 * It is not forced to implement scan or fax methods.
 */
interface SolidPrintableReport {
    void print(String content);
}

interface SolidScannableDocument {
    void scan(String content);
}

class SolidSimplePrinter implements SolidPrintableReport {
    @Override
    public void print(String content) {
        System.out.println("Printing: " + content);
    }
}

class SolidMultiFunctionPrinter implements SolidPrintableReport, SolidScannableDocument {
    @Override
    public void print(String content) {
        System.out.println("Printing: " + content);
    }

    @Override
    public void scan(String content) {
        System.out.println("Scanning: " + content);
    }
}

/*
 * D - Dependency Inversion Principle
 *
 * Interview definition:
 * High-level modules should depend on abstractions, not concrete classes.
 *
 * Meaning:
 * Business logic should depend on interfaces, not directly on specific classes.
 *
 * In this example:
 * SolidNotificationService depends on SolidMessageService interface.
 * It can work with email, SMS, or any future message service.
 */
interface SolidMessageService {
    void sendMessage(String message);
}

class SolidEmailMessageService implements SolidMessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SolidSmsMessageService implements SolidMessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class SolidNotificationService {
    private SolidMessageService messageService;

    public SolidNotificationService(SolidMessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message) {
        messageService.sendMessage(message);
    }
}
