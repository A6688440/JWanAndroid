package com.example.jwanandroid.systematic;

import com.example.jwanandroid.base.BaseModel;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.http.RetrofitFactory;

import org.greenrobot.eventbus.Subscribe;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SJC on 2020/6/6.
 * Describe：
 */
public class SystematicModel extends BaseModel implements MvpSystematic.M {

    @Override
    public void getKind(Observer<SystematicKindBean> observer) {
        RetrofitFactory
                .HttpAddCookie()
                .httpSystematicKind()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getArticle(int pager, int cid, Observer<ArticleBean> observer) {
        RetrofitFactory
                .HttpAddCookie()
                .httpSystematicArticle(pager, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
