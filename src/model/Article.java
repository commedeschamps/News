package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Article {
    private final String id;
    private final String title;
    private final String content;
    private final String category;
    private final LocalDateTime publishedDate;
    private final String author;
    private final int priority;

    private Article(Builder builder) {
        this.id = builder.id;
        this.title = builder.title.trim();
        this.content = builder.content.trim();
        this.category = builder.category.trim();
        this.publishedDate = builder.publishedDate;
        this.author = builder.author;
        this.priority = builder.priority;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCategory() { return category; }
    public LocalDateTime getPublishedDate() { return publishedDate; }
    public String getAuthor() { return author; }
    public int getPriority() { return priority; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id = UUID.randomUUID().toString();
        private String title;
        private String content;
        private String category = "GENERAL";
        private LocalDateTime publishedDate = LocalDateTime.now();
        private String author = "Anonymous";
        private int priority = 0;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = Math.max(0, Math.min(10, priority));
            return this;
        }
        public Builder publishedDate(LocalDateTime publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Article build() {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("Content cannot be empty");
            }
            if (content.trim().length() < 10) {
                throw new IllegalArgumentException("Content must be at least 10 characters");
            }
            if (title.trim().length() > 200) {
                throw new IllegalArgumentException("Title must be less than 200 characters");
            }
            return new Article(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Article{id='%s', title='%s', category='%s', priority=%d}",
                id, title, category, priority);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article)) return false;
        return this.id.equals(((Article) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
