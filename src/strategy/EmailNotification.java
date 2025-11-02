package strategy;

import model.Article;
import observer.Subscriber;

public class EmailNotification implements NotificationStrategy {
    @Override
    public void sendNotification(Article article, Subscriber subscriber) {
        validate(subscriber);
        System.out.println("  ├─ EMAIL -> " + subscriber.getEmail() + " | " + article.getTitle());
    }

    @Override
    public void validate(Subscriber subscriber) {
        String email = subscriber.getEmail();
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address for subscriber: " + subscriber.getName());
        }
    }
}
