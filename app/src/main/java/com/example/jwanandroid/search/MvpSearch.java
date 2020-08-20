package com.example.jwanandroid.search;

import com.example.jwanandroid.bean.ArticleBean;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public interface MvpSearch {
    interface V {

        void getSearch(List<ArticleBean.DataBean.DatasBean> list);

    }

    interface P {

        void setLove(int cid,boolean isLove);

        void getSearch(int pager, String searchKey);

    }

    interface M {

        void getHomeSearch(int pager, String searchKey, Observer<ArticleBean> observer);

    }

}
