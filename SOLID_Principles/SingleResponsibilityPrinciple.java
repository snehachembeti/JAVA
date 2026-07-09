public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {
        printHeader();

        Invoice invoice = new Invoice("Laptop", 50000, 2);

        InvoicePrinter printer = new InvoicePrinter();
        printer.print(invoice);

        InvoiceRepository repository = new InvoiceRepository();
        repository.save(invoice);
    }

    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("S - Single Responsibility Principle");
        System.out.println("==================================================");
        System.out.println("Interview line: A class should have only one reason to change.");
        System.out.println();
    }
}

/*
 * ==================================================
 * S - Single Responsibility Principle
 * ==================================================
 *
 * Interview definition:
 * A class should have only one reason to change.
 *
 * Simple meaning:
 * One class should do one main job.
 *
 * Example idea:
 * Invoice should calculate invoice details.
 * InvoicePrinter should print invoice details.
 * InvoiceRepository should save invoice details.
 *
 * If printing logic changes, only InvoicePrinter changes.
 * If saving logic changes, only InvoiceRepository changes.
 * If invoice calculation changes, only Invoice changes.
 */
class Invoice {
    private String itemName;
    private double itemPrice;
    private int quantity;

    public Invoice(String itemName, double itemPrice, int quantity) {
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

class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Printing invoice");
        System.out.println("Item  : " + invoice.getItemName());
        System.out.println("Total : Rs." + invoice.calculateTotal());
    }
}

class InvoiceRepository {
    public void save(Invoice invoice) {
        System.out.println("Saving invoice for item: " + invoice.getItemName());
    }
}
