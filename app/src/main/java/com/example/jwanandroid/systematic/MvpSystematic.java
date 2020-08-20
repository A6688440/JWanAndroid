package com.example.jwanandroid.systematic;

import com.example.jwanandroid.bean.ArticleBean;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by SJC on 2020/6/6.
 * Describe：
 */
public interface MvpSystematic {
    interface V{

        void getKind(List<SystematicKindBean.DataBean> dataBeans);

        void getArticle(List<ArticleBean.DataBean.DatasBean>  beans);
    }

    interface P{
        void setLove(int cid,boolean isLove);

        void getKind();

        void getArticle(int pager,int cid);
    }

    interface M{

        void getKind(Observer<SystematicKindBean> observer);

        void getArticle(int pager,int cid,Observer<ArticleBean> observer);
    }
}
