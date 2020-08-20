package com.example.jwanandroid.main;

import com.example.jwanandroid.bean.UserBean;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public interface MvpMain {
    interface V {
        void getUserName(String username);

        void getExitLogin();
    }

    interface P {
        void getUserName();

        void exitLogin();
    }

    interface M {
        void exitLogin(Observer<UserBean> observer);
    }
}
