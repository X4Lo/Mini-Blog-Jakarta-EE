package com.ehei.beans;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String content;
    private int authorId;
    private LocalDateTime timestamp;

    public Post() {
    }

    public Post(String content, int authorId, LocalDateTime timestamp) {
        this.content = content;
        this.authorId = authorId;
        this.timestamp = timestamp;
    }

    public Post(int id, String content, int authorId, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
