package com.example.jwanandroid.home;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.HomeHeadBean;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.collect.CollectModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SJC on 2020/6/1.
 * Describe：
 */
public class HomePresenter extends BasePresenter<HomePagerFragment, HomeModel> implements MvpHome.P {
    private static final String TAG = "HomePresenter";
    private List<ArticleBean.DataBean.DatasBean> articleList = new ArrayList<>();
    private List<String> imageUrls = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> links = new ArrayList<>();
    private CollectModel collectModel = new CollectModel();


    @Override
    protected HomeModel getInstantiate() {
        return new HomeModel();
    }


    @Override
    public void setLove(int cid, boolean isLove) {


        if (isLove) {

            collectModel.setUnCollectOne(cid, new BaseObserver<ArticleBean>() {
                @Override
                protected void onSuccess(ArticleBean articleBean) {
                    if (articleBean.getErrorCode() == 0) {
                        mView.getMessage("取消成功");
                    } else {
                        mView.getMessage("取消失败");
                    }
                }

                @Override
                protected void onFail(Throwable e) {
                    mView.getMessage("网络异常");
                }
            });
        } else {
            collectModel.setCollectN(cid, new BaseObserver<ArticleBean>() {
                @Override
                protected void onSuccess(ArticleBean articleBean) {
                    if (articleBean.getErrorCode() == 0) {
                        mView.getMessage("收藏成功");
                    } else {
                        mView.getMessage("收藏失败");
                    }
                }

                @Override
                protected void onFail(Throwable e) {
                    mView.getMessage("网络异常");
                }
            });
        }
    }


    @Override
    public void getHomeHead() {
        mModel.getHomeHead(new BaseObserver<HomeHeadBean>() {
            @Override
            protected void onSuccess(HomeHeadBean homeHeadBean) {
                for (HomeHeadBean.DataBean datum : homeHeadBean.getData()) {
                    imageUrls.add(datum.getImagePath());
                    titles.add(datum.getTitle());
                    links.add(datum.getUrl());
                }
                mView.getHomeHead(imageUrls, titles, links);
            }


            @Override
            protected void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getPager(final int pager) {

        mModel.getHome(pager, new BaseObserver<ArticleBean>() {
            @Override
            protected void onSuccess(final ArticleBean homeBean) {

                if (homeBean.getData().getDatas() == null) {
                    mView.getMessage("没有数据");
                } else {
                    if (pager == 0) {
                        articleList.clear();
                    }
                    articleList.addAll(homeBean.getData().getDatas());
                    mView.getHome(articleList);
                }

            }

            @Override
            protected void onFail(Throwable e) {
                mView.getMessage("网络错误");
            }
        });
    }

    @Override
    public void unBingView() {
        super.unBingView();
        collectModel = null;
    }
}
