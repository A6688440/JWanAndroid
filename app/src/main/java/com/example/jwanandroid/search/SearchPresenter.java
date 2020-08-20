package com.example.jwanandroid.search;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.collect.CollectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class SearchPresenter extends BasePresenter<SearchActivity, SearchModel> implements MvpSearch.P {


    private List<ArticleBean.DataBean.DatasBean> articleList = new ArrayList<>();
    private CollectModel collectModel = new CollectModel();

    @Override
    protected SearchModel getInstantiate() {
        return new SearchModel();
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
    public void getSearch(final int pager, String searchKey) {
        if (searchKey.equals("")) {
            mView.getMessage("请输入搜索关键词");
            return;
        }
        mModel.getHomeSearch(pager, searchKey, new BaseObserver<ArticleBean>() {
            @Override
            protected void onSuccess(ArticleBean articleBean) {
                if (articleBean.getData().getDatas().size() == 0) {
                    mView.getMessage("暂无更多");
                } else {
                    if (pager == 0) {
                        articleList.clear();
                    }
                    articleList.addAll(articleBean.getData().getDatas());
                    mView.getSearch(articleList);
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
