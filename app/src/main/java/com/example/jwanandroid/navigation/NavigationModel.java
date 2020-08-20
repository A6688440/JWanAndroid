package com.example.jwanandroid.navigation;

import com.example.jwanandroid.base.BaseModel;
import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class NavigationModel extends BaseModel implements MvpNavigation.M {
    @Override
    public void getNavigation(Observer<NavigationProjectBean> observer) {
        RetrofitFactory.HttpAddCookie().httpNavigation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
