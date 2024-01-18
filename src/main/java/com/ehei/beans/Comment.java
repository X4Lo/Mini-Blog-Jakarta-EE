package com.ehei.beans;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int postId;
    private int authorId;
    private String content;
    private LocalDateTime timestamp;

    public Comment() {
    }

    public Comment(int postId, int authorId, String content, LocalDateTime timestamp) {
        this.postId = postId;
        this.authorId = authorId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Comment(int id, int postId, int authorId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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
