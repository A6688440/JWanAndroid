package com.example.jwanandroid.project;

import com.example.jwanandroid.base.BasePresenter;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class ProjectPresenter extends BasePresenter<ProjectFragment,ProjectModel> implements MvpProject.P {
    @Override
    protected ProjectModel getInstantiate() {
        return new ProjectModel();
    }

    @Override
    public void getProjectKind() {

    }

    @Override
    public void getProject(int pager, int cid) {

    }
}
