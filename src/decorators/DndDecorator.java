package decorators;

import java.time.LocalTime;

import model.Article;
import observer.Subscriber;
import strategy.NotificationStrategy;

public class DndDecorator extends StrategyDecorator {
    private final LocalTime dndStartHour;
    private final LocalTime dndEndHour;

    public DndDecorator(NotificationStrategy wrappedStrategy, LocalTime dndStartHour, LocalTime dndEndHour) {
        super(wrappedStrategy);
        this.dndStartHour = dndStartHour;
        this.dndEndHour = dndEndHour;
    }

    @Override
    public void sendNotification(Article article, Subscriber subscriber) {
        LocalTime now = LocalTime.now();
        if (now.isAfter(dndStartHour) && now.isBefore(dndEndHour)) {
            System.out.println("      [INFO] Notification to " + subscriber.getName() + " is deferred due to DND hours.");
            return;
        }
        super.sendNotification(article, subscriber);
    }
}
