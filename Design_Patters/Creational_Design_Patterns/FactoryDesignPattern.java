public class FactoryDesignPattern {
    public static void main(String[] args) {
        NotificationFactory smsFactory = new SMSNotificationFactory();
        Notification smsNotification = smsFactory.createNotification();
        smsNotification.notifyUser();

        NotificationFactory emailFactory = new EmailNotificationFactory();
        Notification emailNotification = emailFactory.createNotification();
        emailNotification.notifyUser();

        smsFactory.sendNotification();
        emailFactory.sendNotification();
    }
}

interface Notification {
    void notifyUser();
}

class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending SMS notification.");
    }
}

class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending Email notification.");
    }
}

abstract class NotificationFactory {
    public abstract Notification createNotification();

    public void sendNotification() {
        Notification notification = createNotification();
        notification.notifyUser();
    }
}

class SMSNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

class EmailNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
