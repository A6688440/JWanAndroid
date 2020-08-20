package com.example.jwanandroid.collect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jwanandroid.base.BaseFragment;
import com.example.jwanandroid.bean.CollectListBean;
import com.example.jwanandroid.databinding.FragmentCollectBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends BaseFragment<CollectPresenter> implements MvpCollect.V {

    private FragmentCollectBinding binding;

    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentCollectBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected CollectPresenter getInstantiate() {
        return new CollectPresenter();
    }


    @Override
    public void collectList(List<CollectListBean.DataBean.DatasBean> beans) {

    }

    @Override
    public void setCollectN(int cid) {

    }

    @Override
    public void setUnCollectOne(int cid) {

    }


    @Override
    public void getMessage(String Message) {

    }
}
