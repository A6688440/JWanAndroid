package com.example.jwanandroid.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.jwanandroid.R;
import com.example.jwanandroid.adapter.ListViewAdapterProjectKind;
import com.example.jwanandroid.adapter.RecyclerAdapterArticle;
import com.example.jwanandroid.base.BaseFragment;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.bean.ProjectKindBean;
import com.example.jwanandroid.databinding.FragmentProjectBinding;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements MvpProject.V {

    private FragmentProjectBinding binding;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapterArticle adapterArticle;
    private int pager;
    private int cid;
    private List<NavigationProjectBean.DataBean.ArticlesBean> list;

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProjectBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getProjectKind();
    }

    @Override
    protected ProjectPresenter getInstantiate() {
        return new ProjectPresenter();
    }

    @Override
    public void getMessage(String Message) {

    }


    @Override
    public void getProjectKind(final List<ProjectKindBean.DataBean> dataBeans) {
        ListViewAdapterProjectKind adapterProjectKind = new ListViewAdapterProjectKind(getContext(), dataBeans);
        binding.mListViewProjectKind.setAdapter(adapterProjectKind);
        binding.mListViewProjectKind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cid = dataBeans.get(i).getId();
                mPresenter.getProject(0, dataBeans.get(i).getId());
            }
        });
    }

    @Override
    public void getProject(List<NavigationProjectBean.DataBean.ArticlesBean> articlesBeans) {

        list = articlesBeans;

        binding.mRecycleProject.refreshComplete();
        binding.mRecycleProject.loadMoreComplete();

        //list.addAll(bean);

        if (adapterArticle == null) {

            linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.mRecycleProject.setLayoutManager(linearLayoutManager);

            //设置是否允许下拉刷新
            binding.mRecycleProject.setPullRefreshEnabled(true);
            //设置是否允许上拉加载
            binding.mRecycleProject.setLoadingMoreEnabled(true);

            binding.mRecycleProject.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            binding.mRecycleProject.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);


            binding.mRecycleProject.getDefaultFootView().setLoadingDoneHint("加载中。。。");


//            adapterArticle = new RecyclerAdapterArticle(getContext(), list, new RecyclerAdapterArticle.MyCall() {
//                @Override
//                public void setLove(int position, int cid, boolean isLove) {
//                    //  mPresenter.setLove(cid, isLove);
//                    //mLovePosition = position;
//                }
//            });
            binding.mRecycleProject.setAdapter(adapterArticle);
        } else {
            adapterArticle.notifyDataSetChanged();
        }

        if (adapterArticle != null) {
            binding.mRecycleProject.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                //下拉刷新
                public void onRefresh() {
                    //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                    pager = 0; //当前页码
                    /**加载数据处理**/
                    mPresenter.getProject(pager, cid);
                }

                @Override
                //上拉加载
                public void onLoadMore() {
                    // Log.e(TAG, "onLoadMore: "+pager );
                    pager++;//当前页码
                    /**加载数据处理**/
                    mPresenter.getProject(pager,cid);
                }
            });
        }
    }
}
