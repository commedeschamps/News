package observer;

import model.Article;
import strategy.NotificationStrategy;

import java.util.HashSet;
import java.util.Set;

public class Subscriber implements Observer {
    private final String name;
    private final String email;
    private final String phone;
    private NotificationStrategy strategy;
    private final Observable subject;
    private final Set<String> interests = new HashSet<>();

    private Subscriber(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.strategy = builder.strategy;
        this.subject = builder.subject;
    }

    public void subscribe() {
        subject.addSubscriber(this);
    }

    public void unsubscribe() {
        subject.removeSubscriber(this);
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
        System.out.println("   - Strategy changed to: " + strategy.getClass().getSimpleName());
    }
    public void subscribeCategory(String category) {
        if (category != null && !category.isBlank()) {
            String normalized = category.trim().toLowerCase();
            interests.add(normalized);
            System.out.println("      → " + name + " added interest: '" + normalized + "'");
        }
    }

    public void unsubscribeCategory(String category) {
        if (category != null)
            interests.remove(category.trim().toLowerCase());
    }

    public Set<String> getInterests() {
        return Set.copyOf(interests);
    }

    public boolean isInterested(Article article) {
        if (interests.isEmpty()) {
            return true;
        }
        return interests.contains(article.getCategory().toLowerCase());
    }

    @Override
    public void update(Article article) {
        String articleCategory = article.getCategory().toLowerCase();
        if (!interests.isEmpty() && !interests.contains(articleCategory)) {
            return;
        }

        if (strategy != null) {
            strategy.sendNotification(article, this);
        } else {
            System.out.println("      [WARNING] " + name + " has no notification strategy!");
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String email;
        private String phone;
        private NotificationStrategy strategy;
        private Observable subject;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder strategy(NotificationStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public Builder subject(Observable subject) {
            this.subject = subject;
            return this;
        }

        public Subscriber build() {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
            if (phone == null || phone.isEmpty()) {
                throw new IllegalArgumentException("Phone cannot be null or empty");
            }
            if (subject == null) {
                throw new IllegalArgumentException("Subject (Observable) cannot be null");
            }
            return new Subscriber(this);
        }
    }
}