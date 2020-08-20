package com.example.jwanandroid.systematic;

import android.util.Log;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.collect.CollectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJC on 2020/6/6.
 * Describe：
 */
public class SystematicPresenter extends BasePresenter<SystematicFragment,SystematicModel> implements MvpSystematic.P {
    private String TAG="SystematicPresenter";
    private List<ArticleBean.DataBean.DatasBean> articleList=new ArrayList<>();
    private CollectModel collectModel = new CollectModel();

    @Override
    protected SystematicModel getInstantiate() {
        return new SystematicModel();
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
    public void getKind() {
        mModel.getKind(new BaseObserver<SystematicKindBean>() {
            @Override
            protected void onSuccess(SystematicKindBean systematicKindBean) {
                mView.getKind(systematicKindBean.getData());
                Log.e(TAG, "onSuccess: "+systematicKindBean.getData().size());
            }

            @Override
            protected void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getArticle(final int pager, int cid) {
        mModel.getArticle(pager, cid, new BaseObserver<ArticleBean>() {
            @Override
            protected void onSuccess(ArticleBean articleBean) {

                if (articleBean.getData().getDatas() == null) {
                   // mView.getMessage("没有数据");


                } else {
                    if (pager == 0) {
                        articleList.clear();
                    }
                    articleList.addAll(articleBean.getData().getDatas());
                    mView.getArticle(articleList);
                }
            }

            @Override
            protected void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void unBingView() {
        super.unBingView();
        collectModel=null;
    }
}
