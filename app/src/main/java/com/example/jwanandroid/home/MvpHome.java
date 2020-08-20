package com.example.jwanandroid.home;

import com.example.jwanandroid.bean.HomeHeadBean;
import com.example.jwanandroid.bean.ArticleBean;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/6/1.
 * Describe：
 */
public interface MvpHome {
    interface V {

        void setLove();

        void getHomeHead(List<String> imageUrl, List<String> title, List<String> link);

        void getHome(List<ArticleBean.DataBean.DatasBean> bean);

    }

    interface P {

        void setLove(int cid,boolean isLove);


        void getHomeHead();

        void getPager(int pager);
    }

    interface M {

        void getHomeHead(Observer<HomeHeadBean> observer);

        void getHome(int pager, Observer<ArticleBean> observer);
    }

}
