package com.ehei.beans;

public class User {
    private int id;
    private String username;
    private String password;
    private String picture;
    private int attempts;
    private boolean locked;

    public User() {
    }

    public User(String username, String password, String picture, int attempts, boolean locked) {
        this.username = username;
        this.password = password;
        this.picture = picture;
        this.attempts = attempts;
        this.locked = locked;
    }

    public User(int id, String username, String password, String picture, int attempts, boolean locked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.picture = picture;
        this.attempts = attempts;
        this.locked = locked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean is_locked) {
        this.locked = is_locked;
    }
}
