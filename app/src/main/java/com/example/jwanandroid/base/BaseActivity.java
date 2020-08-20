package com.example.jwanandroid.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by SJC on 2020/5/28.
 * Describe：
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P mPresenter;

    protected abstract P getInstantiate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter = getInstantiate();
        mPresenter.bingView(this);
    }
}
