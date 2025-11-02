package newsagency;

import model.Article;
import observer.Observable;
import observer.Observer;
import observer.Subscriber;

import java.util.ArrayList;

public class NewsAgency implements Observable {
    private static NewsAgency instance;
    private final ArrayList<Observer> subscribers = new ArrayList<>();

    private NewsAgency() {
    }

    // Singleton getInstance method
    public static NewsAgency getInstance() {
        if (instance == null) {
            instance = new NewsAgency();
        }
        return instance;
    }

    @Override
    public void addSubscriber(Observer o) {
        if (o != null && !subscribers.contains(o)) {
            subscribers.add(o);
        }
    }

    @Override
    public void removeSubscriber(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifySubscribers(Article article) {
        java.util.List<String> notifiedNames = new java.util.ArrayList<>();

        for (Observer observer : subscribers) {
            try {
                if (observer instanceof Subscriber) {
                    Subscriber sub = (Subscriber) observer;
                    if (sub.isInterested(article)) {
                        observer.update(article);
                        notifiedNames.add(sub.getName());
                    }
                } else {
                    observer.update(article);
                }
            } catch (Exception e) {
                String subscriberName = (observer instanceof Subscriber)
                        ? ((Subscriber) observer).getName()
                        : "Unknown";
                System.out.println("  [ERROR] Failed to notify " + subscriberName + ": " + e.getMessage());
            }
        }

        System.out.println("├─────────────────────────────────────────────────────────────┤");
        if (notifiedNames.isEmpty()) {
            System.out.println("│ Notified: none");
        } else {
            System.out.println("│ Notified: " + String.join(", ", notifiedNames));
        }
        System.out.println("└─────────────────────────────────────────────────────────────┘");
    }

    public void publish(Article article) {
        System.out.println("\n┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ " + article.getTitle());
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ Category: " + article.getCategory() +
                " | Author: " + article.getAuthor() +
                " | Priority: " + article.getPriority() + "/10");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        notifySubscribers(article);
    }
}



