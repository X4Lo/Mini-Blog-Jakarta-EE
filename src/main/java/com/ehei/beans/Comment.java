package com.ehei.beans;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int postId;
    private String content;
    private LocalDateTime timestamp;

    public Comment() {
    }

    public Comment(int postId, String content, LocalDateTime timestamp) {
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Comment(int id, int postId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
