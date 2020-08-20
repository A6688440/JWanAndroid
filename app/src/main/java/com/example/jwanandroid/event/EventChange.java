package com.example.jwanandroid.event;

/**
 * Created by SJC on 2020/6/17.
 * Describe：
 */
public class EventChange {
    private int Code;
    private String Message;

    public EventChange(int code, String message) {
        Code = code;
        Message = message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
