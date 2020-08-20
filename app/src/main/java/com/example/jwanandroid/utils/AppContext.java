package com.example.jwanandroid.utils;

import android.app.Application;

/**
 * Created by SJC on 2020/6/14.
 * Describe：
 */
public class AppContext extends Application {

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = AppContext.this;
    }
}
