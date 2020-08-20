package com.example.jwanandroid.home;

import com.example.jwanandroid.base.BaseModel;
import com.example.jwanandroid.bean.HomeHeadBean;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/6/1.
 * Describe：
 */
public class HomeModel extends BaseModel implements MvpHome.M {


    @Override
    public void getHomeHead(Observer<HomeHeadBean> observer) {
        RetrofitFactory.HttpAddCookie().HttpHomeHead()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getHome(int pager, Observer<ArticleBean> observer) {
        RetrofitFactory.HttpAddCookie().HttpHome(pager)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
