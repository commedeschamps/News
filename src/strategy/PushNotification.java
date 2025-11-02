package strategy;

import model.Article;
import observer.Subscriber;

public class PushNotification implements NotificationStrategy {
    @Override
    public void sendNotification(Article article, Subscriber subscriber) {
        validate(subscriber);
        System.out.println("  ├─ PUSH  -> " + subscriber.getName() + " | " + article.getTitle());
    }

    @Override
    public void validate(Subscriber subscriber) {
        String name = subscriber.getName();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Subscriber name cannot be empty for push notification");
        }
    }
}
