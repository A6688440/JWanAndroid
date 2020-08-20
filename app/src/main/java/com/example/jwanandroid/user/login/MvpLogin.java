package com.example.jwanandroid.user.login;

import com.example.jwanandroid.bean.UserBean;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/5/28.
 * Describe：
 */
public interface MvpLogin {
    interface V{

    }

    interface P {
        void Login(String username, String password);
    }

    interface M {
        void loginM(String username,String password,Observer<UserBean> observer);
    }
}
