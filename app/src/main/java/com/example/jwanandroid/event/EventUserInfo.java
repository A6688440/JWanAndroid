package com.example.jwanandroid.event;

/**
 * Created by SJC on 2020/5/29.
 * Describe：
 */
public class EventUserInfo{
    private String username;

    public EventUserInfo(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
