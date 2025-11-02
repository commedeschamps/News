package observer;

import model.Article;

public interface Observable {
    void addSubscriber(Observer o);
    void removeSubscriber(Observer o);
    void notifySubscribers(Article article);
}
