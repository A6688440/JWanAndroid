package com.example.jwanandroid.collect;

import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.bean.CollectListBean;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public interface MvpCollect {
    interface V {

        void collectList(List<CollectListBean.DataBean.DatasBean> beans);

        void setCollectN(int cid);

        void setUnCollectOne(int cid);

    }

    interface P {
        //获取收藏列表
        void collectList(int pager);

        void setCollectN(int cid);

        void setUnCollectOne(int cid);

    }

    interface M {
        void collectList( int pager, Observer<CollectListBean> observer);

        void setCollectN(int cid,Observer<ArticleBean> observer);

        void setUnCollectOne(int cid, Observer<ArticleBean> observer);
    }
}
