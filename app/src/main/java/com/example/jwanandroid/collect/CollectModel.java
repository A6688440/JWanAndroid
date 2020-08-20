package com.example.jwanandroid.collect;

import com.example.jwanandroid.base.BaseModel;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.bean.CollectListBean;
import com.example.jwanandroid.http.RetrofitFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class CollectModel extends BaseModel implements MvpCollect.M {
    //收藏列表
    @Override
    public  void collectList( int pager, Observer<CollectListBean> observer) {
        RetrofitFactory.HttpAddCookie().httpCollectList(pager)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    //添加收藏
    @Override
    public void setCollectN(int cid, Observer<ArticleBean> observer) {
        RetrofitFactory.HttpAddCookie().httpCollectN(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //取消收藏
    @Override
    public void setUnCollectOne(int cid, Observer<ArticleBean> observer) {
        RetrofitFactory.HttpAddCookie().httpUnCollectOne(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
