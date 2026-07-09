public class DependencyInversionPrinciple {
    public static void main(String[] args) {
        printHeader();

        NotificationService emailNotification =
                new NotificationService(new EmailMessageSender());
        emailNotification.notifyUser("sneha@example.com", "Your order is shipped.");

        NotificationService smsNotification =
                new NotificationService(new SmsMessageSender());
        smsNotification.notifyUser("9876543210", "Your OTP is 123456.");
    }

    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("D - Dependency Inversion Principle");
        System.out.println("==================================================");
        System.out.println("Interview line: Depend on abstractions, not concrete classes.");
        System.out.println();
    }
}

/*
 * ==================================================
 * D - Dependency Inversion Principle
 * ==================================================
 *
 * Interview definition:
 * High-level modules should depend on abstractions, not concrete classes.
 *
 * Simple meaning:
 * Business logic should depend on interfaces, not directly on specific classes.
 *
 * Example idea:
 * NotificationService is high-level business logic.
 * It depends on MessageSender interface.
 *
 * Because of that, it can work with email, SMS, WhatsApp, or any future sender
 * without changing NotificationService.
 */
interface MessageSender {
    void send(String receiver, String message);
}

class EmailMessageSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("Email sent to " + receiver + ": " + message);
    }
}

class SmsMessageSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("SMS sent to " + receiver + ": " + message);
    }
}

class NotificationService {
    private MessageSender messageSender;

    public NotificationService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void notifyUser(String receiver, String message) {
        messageSender.send(receiver, message);
    }
}
