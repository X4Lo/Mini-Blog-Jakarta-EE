package com.ehei.beans;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String content;
    private String banner;
    private int authorId;
    private LocalDateTime timestamp;

    public Post() {
    }

    public Post(String title, String content, String banner, int authorId, LocalDateTime timestamp) {
        this.title = title;
        this.content = content;
        this.banner = banner;
        this.authorId = authorId;
        this.timestamp = timestamp;
    }

    public Post(int id, String title, String content, String banner, int authorId, LocalDateTime timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.banner = banner;
        this.authorId = authorId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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
