package com.example.jwanandroid.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jwanandroid.adapter.RecyclerAdapterArticle;
import com.example.jwanandroid.adapter.RecyclerAdapterHome;
import com.example.jwanandroid.base.BaseActivity;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.databinding.ActivitySearchBinding;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;


public class SearchActivity extends BaseActivity<SearchPresenter> implements MvpSearch.V {

    private ActivitySearchBinding binding;
    private RecyclerAdapterHome adapterSearch;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapterArticle adapterArticle;

    private List<ArticleBean.DataBean.DatasBean> list = new ArrayList<>();
    private int pager = 0;
    private String SearchKey;

    private int mLovePosition=-1;

    @Override
    protected SearchPresenter getInstantiate() {
        return new SearchPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        SearchKey = intent.getStringExtra("SearchKey");

        mPresenter.getSearch(0, SearchKey);

        binding.mSearchView.setIconifiedByDefault(true);
        binding.mSearchView.setIconified(false);
        binding.mSearchView.clearFocus();
        binding.mSearchView.setQueryHint("搜索");
        binding.mSearchView.setQuery(SearchKey, false);
        binding.mTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.mSearchView.clearFocus();
                pager = 0;
                list.clear();
                SearchKey = binding.mSearchView.getQuery().toString();
                mPresenter.getSearch(0, SearchKey);
            }
        });
    }

    @Override
    public void getSearch(List<ArticleBean.DataBean.DatasBean> bean) {


        binding.mRecyclerViewHome.refreshComplete();
        binding.mRecyclerViewHome.loadMoreComplete();

        list=bean;

        if (adapterArticle == null) {

            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.mRecyclerViewHome.setLayoutManager(linearLayoutManager);

            //设置是否允许下拉刷新
            binding.mRecyclerViewHome.setPullRefreshEnabled(true);
            //设置是否允许上拉加载
            binding.mRecyclerViewHome.setLoadingMoreEnabled(true);

            binding.mRecyclerViewHome.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            binding.mRecyclerViewHome.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

            //设置上下拉的文字提示语
            //binding.mRecyclerViewHome.getDefaultFootView().setLoadingHint("自定义加载中提示");
            //设置XRecyclerView加载到最后一页再上拉的提示信息
            //xRecyclerView.getDefaultFootView().setNoMoreHint("加载完毕");
            //设置XRecyclerView下拉刷新时间提示
            //xRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(false);
//            binding.mRecyclerViewHome.addHeaderView();
            binding.mRecyclerViewHome.getDefaultFootView().setLoadingDoneHint("加载中。。。");


            adapterArticle = new RecyclerAdapterArticle(this, list, new RecyclerAdapterArticle.MyCall() {
                @Override
                public void setLove(int position,int cid, boolean isLove) {
                    mLovePosition = position;
                    mPresenter.setLove(cid, isLove);

                }
            });
            binding.mRecyclerViewHome.setAdapter(adapterArticle);
        } else {
            adapterArticle.notifyDataSetChanged();
        }

        if (adapterArticle != null) {
            binding.mRecyclerViewHome.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                //下拉刷新
                public void onRefresh() {
                    //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                    pager = 0; //当前页码
                    /**加载数据处理**/
                    mPresenter.getSearch(pager++, SearchKey);
                }

                @Override
                //上拉加载
                public void onLoadMore() {
                    // Log.e(TAG, "onLoadMore: "+pager );
                    pager++;//当前页码
                    /**加载数据处理**/
                    mPresenter.getSearch(pager++, SearchKey);
                }
            });
        }

    }



    @Override
    public void getMessage(String Message) {
        switch (Message){
            case "收藏成功":
                list.get(mLovePosition).setCollect(true);
                break;
            case "取消成功":
                list.get(mLovePosition).setCollect(false);
                break;

        }

        adapterArticle.notifyDataSetChanged();
        Toast.makeText(SearchActivity.this, Message, Toast.LENGTH_SHORT).show();
    }
}
