package com.example.jwanandroid.project;

import android.database.Observable;

import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.bean.ProjectKindBean;

import java.util.List;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public interface MvpProject {
    interface V{
        void  getProjectKind(List<ProjectKindBean.DataBean> dataBeans);

        void getProject(List<NavigationProjectBean.DataBean.ArticlesBean> articlesBeans);
    }
    interface P{
        void  getProjectKind();

        void getProject(int pager,int cid);
    }
    interface M{
        void  getProjectKind(io.reactivex.Observable<ProjectKindBean> observable);

        void getProject(int pager, int cid, Observable<NavigationProjectBean> observable);
    }
}
