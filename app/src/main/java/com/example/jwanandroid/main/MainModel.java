package com.example.jwanandroid.main;

import com.example.jwanandroid.base.BaseModel;
import com.example.jwanandroid.bean.UserBean;
import com.example.jwanandroid.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/6/15.
 * Describe：
 */
public class MainModel extends BaseModel implements MvpMain.M {

    @Override
    public void exitLogin(Observer<UserBean> observer) {
        RetrofitFactory.HttpAddCookie().HttpExitLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
