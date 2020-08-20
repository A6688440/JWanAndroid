package com.example.jwanandroid.user.register;

import com.example.jwanandroid.bean.UserBean;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/5/29.
 * Describe：
 */
public interface MvpRegister {

    interface V {

    }

    interface P {
        void Register(String username, String password, String repassword);
    }

    interface M {
        void Register(String username, String password, String repassword, Observer<UserBean> observer);
    }

}
