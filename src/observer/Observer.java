package observer;

import model.Article;

public interface Observer {
    void update(Article article);
}