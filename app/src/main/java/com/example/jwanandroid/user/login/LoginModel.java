package com.example.jwanandroid.user.login;

import com.example.jwanandroid.bean.UserBean;
import com.example.jwanandroid.http.RetrofitFactory;
import com.example.jwanandroid.base.BaseModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/5/28.
 * Describe：
 */
public class LoginModel extends BaseModel implements MvpLogin.M {

    @Override
    public void loginM(String username, String password, Observer<UserBean> observer) {
        RetrofitFactory
                .HttpReadCookie()
                .HttpLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
