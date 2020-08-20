package com.example.jwanandroid.project;

import com.example.jwanandroid.base.BaseModel;
import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.bean.ProjectKindBean;

import io.reactivex.Observable;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class ProjectModel extends BaseModel implements MvpProject.M {
    @Override
    public void getProjectKind(Observable<ProjectKindBean> observable) {

    }

    @Override
    public void getProject(int pager, int cid, android.database.Observable<NavigationProjectBean> observable) {

    }
}
