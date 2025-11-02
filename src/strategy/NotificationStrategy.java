package strategy;

import model.Article;
import observer.Subscriber;

public interface NotificationStrategy {
    void sendNotification(Article article, Subscriber subscriber);

    default void validate(Subscriber subscriber) {
        // Default validation implementation - can be overridden
    }
}
