package strategy;

import model.Article;
import observer.Subscriber;

public class TeaserDecorator extends StrategyDecorator {

    public TeaserDecorator(NotificationStrategy wrappedStrategy) {
        super(wrappedStrategy);
    }

    @Override
    public void sendNotification(Article article, Subscriber subscriber) {
        System.out.println("      [TEASER MODE] Sending preview notification to " + subscriber.getName());

        String teaserContent = article.getContent();
        if (teaserContent.length() > 50) {
            teaserContent = teaserContent.substring(0, 50) + "... [Read more]";
        }

        Article teaserArticle = Article.builder()
                .title(article.getTitle())
                .content(teaserContent)
                .category(article.getCategory())
                .author(article.getAuthor())
                .priority(article.getPriority())
                .publishedDate(article.getPublishedDate())
                .build();

        wrappedStrategy.sendNotification(teaserArticle, subscriber);
    }
}
