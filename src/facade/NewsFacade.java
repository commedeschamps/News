package facade;
import model.Article;
import newsagency.NewsAgency;
import observer.Subscriber;
import strategy.NotificationStrategy;

import java.util.Objects;

public class NewsFacade {
    private final NewsAgency agency;
    private volatile NotificationStrategy defaultStrategy;

    public NewsFacade(NewsAgency agency) {
        this.agency = Objects.requireNonNull(agency, "agency");
    }

    public static NewsFacade usingSingletonAgency() {
        return new NewsFacade(NewsAgency.getInstance());
    }

    public void setDefaultStrategy(NotificationStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    public NotificationStrategy getDefaultStrategy() {
        return defaultStrategy;
    }

    public NewsAgency getAgency() {
        return agency;
    }

    public Subscriber register(String name, String email, String phone, NotificationStrategy strategy) {
        NotificationStrategy effective = (strategy != null) ? strategy : defaultStrategy;
        Subscriber subscriber = Subscriber.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .strategy(effective)
                .subject(agency)
                .build();
        subscriber.subscribe();
        return subscriber;
    }

    public void unsubscribe(Subscriber subscriber) {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }

    public void addInterest(Subscriber subscriber, String category) {
        Objects.requireNonNull(subscriber, "subscriber");
        subscriber.subscribeCategory(category);
    }

    public void removeInterest(Subscriber subscriber, String category) {
        Objects.requireNonNull(subscriber, "subscriber");
        subscriber.unsubscribeCategory(category);
    }

    public void changeStrategy(Subscriber subscriber, NotificationStrategy strategy) {
        Objects.requireNonNull(subscriber, "subscriber");
        Objects.requireNonNull(strategy, "strategy");
        subscriber.setStrategy(strategy);
    }

    public void publish(Article article) {
        Objects.requireNonNull(article, "article");
        agency.publish(article);
    }

    public Article publish(String title, String content, String category, String author, int priority) {
        Article article = Article.builder()
                .title(title)
                .content(content)
                .category(category)
                .author(author)
                .priority(priority)
                .build();
        agency.publish(article);
        return article;
    }
}
