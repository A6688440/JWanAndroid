package com.example.jwanandroid.base;

/**
 * Created by SJC on 2020/5/28.
 * Describe：
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V mView;
    protected M mModel;

    protected abstract M getInstantiate();

    public BasePresenter() {
        this.mModel = getInstantiate();
    }


    public void bingView(V mView) {
        this.mView = mView;
    }

    public void unBingView() {
        mView = null;
    }
}
