package strategy;

public abstract class StrategyDecorator implements NotificationStrategy{
    protected final NotificationStrategy wrappedStrategy;
    public StrategyDecorator(NotificationStrategy wrappedStrategy) {
        this.wrappedStrategy = wrappedStrategy;
    }
    @Override
    public void sendNotification(model.Article article, observer.Subscriber subscriber) {
        wrappedStrategy.sendNotification(article, subscriber);
    }
}
