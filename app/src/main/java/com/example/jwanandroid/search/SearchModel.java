package com.example.jwanandroid.search;

import com.example.jwanandroid.base.BaseModel;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class SearchModel extends BaseModel implements MvpSearch.M {
    @Override
    public void getHomeSearch(int pager, String searchKey, Observer<ArticleBean> observer) {
        RetrofitFactory.HttpAddCookie().HttpHomeSearch(pager, searchKey)
                .subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
