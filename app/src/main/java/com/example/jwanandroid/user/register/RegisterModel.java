package com.example.jwanandroid.user.register;

import com.example.jwanandroid.bean.UserBean;
import com.example.jwanandroid.http.RetrofitFactory;
import com.example.jwanandroid.base.BaseModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/5/29.
 * Describe：
 */
public class RegisterModel extends BaseModel implements MvpRegister.M {
    @Override
    public void Register(String username, String password, String repassword, Observer<UserBean> observer) {
        RetrofitFactory
                .Http()
                .HttpRegister(username, password, repassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
