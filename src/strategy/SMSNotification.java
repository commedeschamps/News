package strategy;

import model.Article;
import observer.Subscriber;

public class SMSNotification implements NotificationStrategy {
    @Override
    public void sendNotification(Article article, Subscriber subscriber) {
        validate(subscriber);
        System.out.println("  ├─ SMS   -> " + subscriber.getPhone() + " | " + article.getTitle());
    }

    @Override
    public void validate(Subscriber subscriber) {
        String phone = subscriber.getPhone();
        if (phone == null || !phone.matches("\\+?[0-9\\-]{10,15}")) {
            throw new IllegalArgumentException("Invalid phone number for subscriber: " + subscriber.getName());
        }
    }
}
