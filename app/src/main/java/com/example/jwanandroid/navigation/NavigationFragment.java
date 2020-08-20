package com.example.jwanandroid.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.jwanandroid.adapter.ListViewAdapterNa;
import com.example.jwanandroid.adapter.RecyclerAdapterNavigation;
import com.example.jwanandroid.base.BaseFragment;
import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.databinding.FragmentNavigationBinding;
import com.example.jwanandroid.utils.CommUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements MvpNavigation.V {


    private static final String TAG ="NavigationFragment" ;
    private FragmentNavigationBinding binding;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNavigationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getNavigation();
    }

    @Override
    protected NavigationPresenter getInstantiate() {
        return new NavigationPresenter();
    }

    @Override
    public void getMessage(String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getNavigation(List<NavigationProjectBean.DataBean> dataBeans) {

        binding.mListViewNa.setAdapter(new ListViewAdapterNa(getContext(),dataBeans));

        binding.mRecyclerMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.mRecyclerMessage.setAdapter(new RecyclerAdapterNavigation(dataBeans, getContext(), CommUtil.TYPE_RECYCLER_TITLE));

        binding.mListViewNa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                binding.mRecyclerMessage.scrollToPosition(i);
            }
        });
    }
}
