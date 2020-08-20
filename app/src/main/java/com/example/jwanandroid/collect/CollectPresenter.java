package com.example.jwanandroid.collect;

import com.example.jwanandroid.base.BaseObserver;
import com.example.jwanandroid.base.BasePresenter;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.bean.CollectListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class CollectPresenter extends BasePresenter<CollectFragment, CollectModel> implements MvpCollect.P {

    private List<Integer> integers = new ArrayList<>();
    private MyCollectCall myCollectCall;

    public CollectPresenter() {
    }

    public CollectPresenter(MyCollectCall myCollectCall) {
        this.myCollectCall = myCollectCall;
    }

    @Override
    protected CollectModel getInstantiate() {
        return new CollectModel();
    }

    @Override
    public void collectList(int pager) {
        mModel.collectList(pager, new BaseObserver<CollectListBean>() {
            @Override
            protected void onSuccess(CollectListBean collectListBean) {
                mView.collectList(collectListBean.getData().getDatas());
            }

            @Override
            protected void onFail(Throwable e) {
                mView.getMessage("网络异常");
            }
        });
    }

    @Override
    public void setCollectN(int cid) {
        mModel.setCollectN(cid, new BaseObserver<ArticleBean>() {
            @Override
            protected void onSuccess(ArticleBean articleBean) {

                if (articleBean.getErrorCode() == 0) {
                    //  mView.getMessage("收藏成功");
                    myCollectCall.CollectState("收藏成功");
                }
            }

            @Override
            protected void onFail(Throwable e) {
                myCollectCall.CollectState("网络异常");
            }
        });
    }

    @Override
    public void setUnCollectOne(int cid) {
        mModel.setUnCollectOne(cid, new BaseObserver<ArticleBean>() {
            @Override
            protected void onSuccess(ArticleBean articleBean) {
                if (articleBean.getErrorCode() == 0) {
                    //  mView.getMessage("取消成功");
                    myCollectCall.CollectState("取消成功");
                }
            }

            @Override
            protected void onFail(Throwable e) {
                myCollectCall.CollectState("网络异常");
            }
        });
    }

    public interface MyCollectCall {
        void CollectState(String Message);
    }
}
